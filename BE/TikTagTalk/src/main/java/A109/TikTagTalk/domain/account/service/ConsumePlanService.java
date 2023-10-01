package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.request.AllConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.response.AllConsumePlanResonseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.user.entity.Member;

public interface ConsumePlanService {

    ResponseDto insertConsumePlan(ConsumePlanRequestDto requestDto, Member member);
    AllConsumePlanResonseDto allConsumePlan(AllConsumePlanRequestDto requestDto,Member member);
    ResponseDto modifyConsumePlan(ConsumePlanRequestDto requestDto,Long planId,Member member);
}
