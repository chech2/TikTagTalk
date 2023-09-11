package A109.TikTagTalk.domain.account.controller;

import A109.TikTagTalk.domain.account.dto.AllConsumeHistoryResponseDto;
import A109.TikTagTalk.domain.account.service.ConsumeHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/consume")
@Slf4j
public class ConsumeHistoryController {
    private final ConsumeHistoryService consumeHistoryService;

    @GetMapping("")
    public AllConsumeHistoryResponseDto allConsumeHistory(){
        return consumeHistoryService.allConsumeHistory();
    }

}
