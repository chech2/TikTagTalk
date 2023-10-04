package A109.TikTagTalk.domain.account.controller;

import A109.TikTagTalk.domain.account.dto.request.AddConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ModifyConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.response.AllConsumeHistoryResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.service.ConsumeHistoryService;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/consume")
@Slf4j
public class ConsumeHistoryController {
    private final ConsumeHistoryService consumeHistoryService;

    @PostMapping("") //최근순 소비 내역 조회
    public List<AllConsumeHistoryResponseDto> allConsumeHistory(@RequestBody ConsumeHistoryRequestDto requestDto){
        Member member= SecurityUtil.getCurrentLoginMember();
        return consumeHistoryService.allConsumeHistoryRecently(requestDto,member);
    }
    @PostMapping("/highest") //고액순 소비 내역 조회
    public List<AllConsumeHistoryResponseDto> allConsumeHistoryHighest(@RequestBody ConsumeHistoryRequestDto requestDto){
        Member member= SecurityUtil.getCurrentLoginMember();
        return consumeHistoryService.allConsumeHistoryHighest(requestDto,member);
    }

    @PostMapping("/checkaccount") //총 소비 금액, 카테고리 별 금액 및 비중 조회
    public CheckAccountResponseDto checkAccount(@RequestBody ConsumeHistoryRequestDto requestDto){
        Member member= SecurityUtil.getCurrentLoginMember();
        return consumeHistoryService.checkAccountTotalAccount(requestDto ,member);
    }

    @PostMapping("/register") //수동 소비 내역 등록
    public ResponseDto AddConsumeHistory(@RequestBody AddConsumeHistoryRequestDto requestDto){
        Member member=SecurityUtil.getCurrentLoginMember();
        return consumeHistoryService.addConsumeHistory(requestDto,member);
    }
    @DeleteMapping("/{consumeHistoryId}") //수동 소비 내역 삭제
    public ResponseDto deleteConsumeHistory(@PathVariable Long consumeHistoryId){
        Member member=SecurityUtil.getCurrentLoginMember();
        return consumeHistoryService.deleteConsumeHistory(consumeHistoryId,member);
    }

    @PutMapping("/{consumeHistoryId}") //수동 소비 내역 수정
    public ResponseDto modifyConsumeHistory(@PathVariable Long consumeHistoryId, @RequestBody ModifyConsumeHistoryRequestDto requestDto){
        Member member=SecurityUtil.getCurrentLoginMember();
        return consumeHistoryService.modifyConsumeHistory(requestDto,consumeHistoryId,member);
    }

    @GetMapping("/makemembertags") //더미데이터에서 memberTag받기
    public ResponseDto makeMemberTags(@RequestBody ConsumeHistoryRequestDto requestDto){
        Member member=SecurityUtil.getCurrentLoginMember();
        return consumeHistoryService.makeMemberTags(requestDto,member);
    }



}
