package A109.TikTagTalk.domain.tagRoom.repository;

import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.tagRoom.dto.request.UpdateMemberItemRequestDto;
import A109.TikTagTalk.domain.tagRoom.entity.MemberItem;
import A109.TikTagTalk.domain.tagRoom.entity.QMemberItem;
import A109.TikTagTalk.domain.user.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberItemRepositoryImpl implements MemberItemRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private QMemberItem memberItem=new QMemberItem("memberItem");

    @Override
    public List<MemberItem> findMemberItem(Member member) {
        return queryFactory.selectFrom(memberItem)
                .where(memberItem.member.eq(member))
                .fetch();
    }

    @Override
    public MemberItem findByMemberItemName(Member member, String itemName) {
        return queryFactory.selectFrom(memberItem)
                .where(memberItem.member.eq(member),memberItem.item.name.eq(itemName))
                .fetchOne();
    }

    @Override
    public void updateMemberItem(MemberItem memberitem, UpdateMemberItemRequestDto.UpdateInfoDto requestDto) {
        queryFactory.update(memberItem)
                .set(memberItem.positionX, requestDto.getPositionX())
                .set(memberItem.positionY, requestDto.getPositionY())
                .set(memberItem.positoinZ, requestDto.getPositionZ())
                .set(memberItem.rotation,requestDto.getRotation())
                .where(memberItem.id.eq(memberitem.getId()))
                .execute();
    }


}
