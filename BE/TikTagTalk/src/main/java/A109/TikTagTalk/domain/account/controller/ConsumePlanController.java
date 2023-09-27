package A109.TikTagTalk.domain.account.controller;

import A109.TikTagTalk.domain.account.dto.request.AllConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.request.InsertConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.response.AllConsumePlanResonseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.service.ConsumePlanService;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
@Slf4j
public class ConsumePlanController {
    private final ConsumePlanService consumePlanService;

    @PostMapping("")
    public ResponseDto insertConsumePlan(@RequestBody InsertConsumePlanRequestDto requestDto){
        Member member= SecurityUtil.getCurrentLoginMember();
        return consumePlanService.insertConsumePlan(requestDto,member);
    }

    @GetMapping("")
    public AllConsumePlanResonseDto allConsumePlan(@RequestBody AllConsumePlanRequestDto requestDto){
        Member member=SecurityUtil.getCurrentLoginMember();
        return consumePlanService.allConsumePlan(requestDto,member);
    }

}
