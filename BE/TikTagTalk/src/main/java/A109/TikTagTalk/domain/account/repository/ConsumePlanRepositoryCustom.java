package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.dto.request.InsertConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.response.InsertConsumePlanReponseDto;

public interface ConsumePlanRepositoryCustom {
    InsertConsumePlanReponseDto insertConsumePlan(InsertConsumePlanRequestDto requestDto);
}
