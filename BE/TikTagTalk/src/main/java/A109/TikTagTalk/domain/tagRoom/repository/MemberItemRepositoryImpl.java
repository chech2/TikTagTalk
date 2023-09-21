package A109.TikTagTalk.domain.tagRoom.repository;

import A109.TikTagTalk.domain.account.entity.Account;
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
}
