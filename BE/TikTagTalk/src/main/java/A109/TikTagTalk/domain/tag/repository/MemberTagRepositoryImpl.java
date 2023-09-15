package A109.TikTagTalk.domain.tag.repository;

import A109.TikTagTalk.domain.tag.entity.MemberTag;
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
    public Boolean checkMemberTagExist(Long accountId, Long tagId) {
        MemberTag membertag =queryFactory
                .selectFrom(memberTag)
                .where(memberTag.account.id.eq(accountId),memberTag.tag.id.eq(tagId))
                .fetchOne();
        if(membertag==null){
            return false;
        }
        return true;
    }
}
