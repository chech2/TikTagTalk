package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseUtil;
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

    @Override
    public ResponseDto modifyConsumePlan(ConsumePlan consumePlanRequest) {
        queryFactory.update(consumePlan)
                .set(consumePlan.totalAmount,consumePlanRequest.getTotalAmount())
                .set(consumePlan.eatAmount,consumePlanRequest.getEatAmount())
                .set(consumePlan.groceryAmount,consumePlanRequest.getGroceryAmount())
                .set(consumePlan.rideAmount,consumePlanRequest.getRideAmount())
                .set(consumePlan.shoppingAmount,consumePlanRequest.getShoppingAmount())
                .set(consumePlan.snackAmount,consumePlanRequest.getSnackAmount())
                .set(consumePlan.insuranceAmount,consumePlanRequest.getInsuranceAmount())
                .set(consumePlan.hobbyAmount,consumePlanRequest.getHobbyAmount())
                .set(consumePlan.hairAmount,consumePlanRequest.getHairAmount())
                .set(consumePlan.healthAmount,consumePlanRequest.getHealthAmount())
                .set(consumePlan.ottAmount,consumePlanRequest.getOttAmount())
                .set(consumePlan.petAmount,consumePlanRequest.getPetAmount())
                .set(consumePlan.travelAmount,consumePlanRequest.getTravelAmount())
                .where(consumePlan.id.eq(consumePlanRequest.getId()))
                .execute();
        return ResponseUtil.Success("consumePlan 수정 성공");
    }
}
