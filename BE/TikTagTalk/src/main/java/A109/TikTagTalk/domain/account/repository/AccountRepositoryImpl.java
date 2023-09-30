package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.entity.QAccount;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private QMember Qmember=new QMember("member");
    @Override
    public Account findAccountByMemberId(Member member) {
        return queryFactory.select(Qmember.account)
                .from(Qmember)
                .where(Qmember.id.eq(member.getId()))
                .fetchOne();
    }
}
