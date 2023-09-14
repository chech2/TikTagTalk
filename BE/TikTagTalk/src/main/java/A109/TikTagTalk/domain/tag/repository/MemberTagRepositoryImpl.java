package A109.TikTagTalk.domain.tag.repository;

import A109.TikTagTalk.domain.tag.entity.QMemberTag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberTagRepositoryImpl implements MemberTagRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private QMemberTag memberTag=new QMemberTag("memberTag");
    @Override
    public Long checkMemberTagExist(Long accountId, Long tagId) {
        Long flag=queryFactory
                .select(memberTag.count())
                .from(memberTag)
                .where(memberTag.account.id.eq(accountId),memberTag.tag.id.eq(tagId))
                .fetchOne();
        return flag;
    }
}
