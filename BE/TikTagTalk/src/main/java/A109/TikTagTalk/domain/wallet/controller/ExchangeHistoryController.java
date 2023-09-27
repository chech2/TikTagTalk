package A109.TikTagTalk.domain.wallet.controller;

import A109.TikTagTalk.domain.wallet.dto.response.ExchangeResponse;
import A109.TikTagTalk.domain.wallet.service.ExchangeHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class ExchangeHistoryController {

    @Autowired
    private final ExchangeHistoryService exchangeHistoryService;


//    @PostMapping("/{cnt}")
//    public ResponseEntity<ExchangeResponse> exchange(@RequestHeader(required = true, name = "Authorization") String token, @PathVariable("itemId")Long itemId) {
//        LocalDateTime now = LocalDateTime.now();
//
//        ExchangeResponse exchangeResponse = exchangeHistoryService.exchangeCoin()
//
//    }

}
