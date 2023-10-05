package A109.TikTagTalk.domain.account.controller;

import A109.TikTagTalk.domain.account.dto.request.AllConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.response.AllConsumePlanResonseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseUtil;
import A109.TikTagTalk.domain.account.exception.InvalidException;
import A109.TikTagTalk.domain.account.exception.NotExistException;
import A109.TikTagTalk.domain.account.service.ConsumePlanService;
import A109.TikTagTalk.domain.tagRoom.exception.CustomAccessDeniedException;
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

    @PostMapping("/register")
    public ResponseDto insertConsumePlan(@RequestBody ConsumePlanRequestDto requestDto){
        Member member= SecurityUtil.getCurrentLoginMember();
        try {
            return consumePlanService.insertConsumePlan(requestDto, member);
        }catch (InvalidException ie){
            return ResponseUtil.Failure("percent의 합이 100이 되어야 합니다.");
        }
    }

    @PostMapping("")
    public AllConsumePlanResonseDto allConsumePlan(@RequestBody AllConsumePlanRequestDto requestDto){
        Member member=SecurityUtil.getCurrentLoginMember();
        try{
            return consumePlanService.allConsumePlan(requestDto,member);
        }catch (NotExistException ne){
            return AllConsumePlanResonseDto.builder()
                    .statusCode(ne.getStatusCode())
                    .errorMessage(ne.getMessage())
                    .build();
        }

    }

    @PutMapping("/{planId}")
    public ResponseDto modifyConsumePlan(@RequestBody ConsumePlanRequestDto requestDto,@PathVariable Long planId) {
        Member member = SecurityUtil.getCurrentLoginMember();
        try {
            return consumePlanService.modifyConsumePlan(requestDto, planId, member);
        }catch (NotExistException ne){
            return ResponseUtil.Failure("해당 consumePlan이 없습니다.");
        }catch(CustomAccessDeniedException ae){
            return ResponseUtil.Failure("권한이 없습니다.");
        }
    }

}
