package A109.TikTagTalk.domain.account.controller;

import A109.TikTagTalk.domain.account.dto.request.AddConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ModifyConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.response.AllConsumeHistoryResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckMemberTagResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.service.ConsumeHistoryService;
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

    @GetMapping("")
    public List<AllConsumeHistoryResponseDto> allConsumeHistory(@RequestParam Long accountId){
        return consumeHistoryService.allConsumeHistoryRecently(accountId);
    }
    @GetMapping("/highest")
    public List<AllConsumeHistoryResponseDto> allConsumeHistoryHighest(@RequestParam Long accountId){
        return consumeHistoryService.allConsumeHistoryHighest(accountId);
    }

    @GetMapping("/checkaccount")
    public CheckAccountResponseDto checkAccount(@RequestParam Long accountId){
        return consumeHistoryService.checkAccountTotalAccount(accountId);
    }

    @PostMapping("")
    public ResponseDto AddConsumeHistory(@RequestBody AddConsumeHistoryRequestDto requestDto){
        return consumeHistoryService.addConsumeHistory(requestDto);
    }
    @DeleteMapping("/{consumeHistoryId}")
    public ResponseDto deleteConsumeHistory(@PathVariable Long consumeHistoryId){
        return consumeHistoryService.deleteConsumeHistory(consumeHistoryId);
    }

    @PutMapping("/{consumeHistoryId}")
    public ResponseDto modifyConsumeHistory(@PathVariable Long consumeHistoryId, @RequestBody ModifyConsumeHistoryRequestDto requestDto){
        return null;
    }

    @GetMapping("/makemembertags") //더미데이터에서 memberTag받기
    public void makeMemberTags(@RequestParam Long accountId){
        consumeHistoryService.makeMemberTags(accountId);
    }

}
