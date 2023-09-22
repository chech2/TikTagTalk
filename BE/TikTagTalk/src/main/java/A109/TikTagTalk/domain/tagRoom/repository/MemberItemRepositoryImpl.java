package A109.TikTagTalk.domain.tagRoom.repository;

import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.tagRoom.dto.request.UpdateMemberItemRequestDto;
import A109.TikTagTalk.domain.tagRoom.entity.MemberItem;
import A109.TikTagTalk.domain.tagRoom.entity.QMemberItem;
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
    public List<MemberItem> findMemberItem(Account account) {
        return queryFactory.selectFrom(memberItem)
                .where(memberItem.account.eq(account))
                .fetch();
    }

    @Override
    public MemberItem findByAccountItemName(Account account, String itemName) {
        return queryFactory.selectFrom(memberItem)
                .where(memberItem.account.eq(account),memberItem.item.name.eq(itemName))
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
