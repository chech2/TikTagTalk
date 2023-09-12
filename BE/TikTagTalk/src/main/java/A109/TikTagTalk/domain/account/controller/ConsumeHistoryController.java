package A109.TikTagTalk.domain.account.controller;

import A109.TikTagTalk.domain.account.dto.AllConsumeHistoryResponseDto;
import A109.TikTagTalk.domain.account.dto.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.service.ConsumeHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/consume")
@Slf4j
public class ConsumeHistoryController {
    private final ConsumeHistoryService consumeHistoryService;

    @GetMapping("")
    public AllConsumeHistoryResponseDto allConsumeHistory(@RequestParam Long accountId){
        return consumeHistoryService.allConsumeHistoryRecently(accountId);
    }
    @GetMapping("/highest")
    public AllConsumeHistoryResponseDto allConsumeHistoryHighest(@RequestParam Long accountId){
        return consumeHistoryService.allConsumeHistoryHighest(accountId);
    }

    @GetMapping("/checkaccount")
    public CheckAccountResponseDto checkAccount(@RequestParam Long accountId){
        return consumeHistoryService.checkAccountTotalAccount(accountId);
    }


}
