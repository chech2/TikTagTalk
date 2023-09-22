package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.request.InsertConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.response.InsertConsumePlanReponseDto;

public interface ConsumePlanService {

    InsertConsumePlanReponseDto insertConsumePlan(InsertConsumePlanRequestDto requestDto);
}
