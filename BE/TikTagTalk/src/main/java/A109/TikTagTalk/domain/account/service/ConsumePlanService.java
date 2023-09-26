package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.request.InsertConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.user.entity.Member;

public interface ConsumePlanService {

    ResponseDto insertConsumePlan(InsertConsumePlanRequestDto requestDto, Member member);
}
