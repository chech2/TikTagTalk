package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.entity.ConsumePlan;

public interface ConsumePlanRepositoryCustom {
    ConsumePlan findByMemberIdAndYearAndMonth(Long memberId,String yearAndMonth);
    ResponseDto modifyConsumePlan(ConsumePlan consumePlan);
}
