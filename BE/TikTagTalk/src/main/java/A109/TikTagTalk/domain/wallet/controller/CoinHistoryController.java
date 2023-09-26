package A109.TikTagTalk.domain.wallet.controller;

import A109.TikTagTalk.domain.wallet.dto.response.CoinListResponse;
import A109.TikTagTalk.domain.wallet.service.CoinHistoryService;
import A109.TikTagTalk.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/exchange/coin")
public class CoinHistoryController {
    //코인 전체 조회
    private final CoinHistoryService coinHistoryService;

    @GetMapping
    public ResponseEntity<List<CoinListResponse>> getAllCoins(@RequestHeader(required = true, name = "Authorization") String token) throws NullPointerException {

        String userId = SecurityUtil.getCurrentLoginMember().getUserId();
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        LocalDateTime now = LocalDateTime.now();
        Timestamp day = Timestamp.valueOf(now);

        List<CoinListResponse> list = coinHistoryService.selectCoinAll(userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //코인 내역 조회
    @GetMapping("/list")
    public ResponseEntity<List<CoinListResponse>> coinList(@RequestHeader(required = true, name = "Authorization") String token, @RequestParam String start, @RequestParam String end) {
        String userId = SecurityUtil.getCurrentLoginMember().getUserId();
        if(userId == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Timestamp endtime = Timestamp.valueOf(end + " 23:59:59.9");

        List<CoinListResponse> coins = coinHistoryService.selectCoinListByUserIdAndDate(userId, start, end);

        return ResponseEntity.status(HttpStatus.OK).body(coins);
    }

}
