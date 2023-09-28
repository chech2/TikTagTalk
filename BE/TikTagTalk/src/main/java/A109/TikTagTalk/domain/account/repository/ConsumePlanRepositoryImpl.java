package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.entity.ConsumePlan;
import A109.TikTagTalk.domain.account.entity.QConsumePlan;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConsumePlanRepositoryImpl implements ConsumePlanRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private QConsumePlan consumePlan=new QConsumePlan("consumePlan");

    @Override
    public ConsumePlan findByMemberIdAndYearAndMonth(Long memberId, String yearAndMonth) {
        return queryFactory.selectFrom(consumePlan)
                .where(consumePlan.member.id.eq(memberId),consumePlan.yearAndMonth.eq(yearAndMonth))
                .fetchOne();
    }
}
