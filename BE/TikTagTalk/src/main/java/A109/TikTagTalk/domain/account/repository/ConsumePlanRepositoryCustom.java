package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.entity.ConsumePlan;

public interface ConsumePlanRepositoryCustom {
    ConsumePlan findByMemberIdAndYearAndMonth(Long memberId,String yearAndMonth);
}
