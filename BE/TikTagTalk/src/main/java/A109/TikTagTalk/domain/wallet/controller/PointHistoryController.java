package A109.TikTagTalk.domain.wallet.controller;

import A109.TikTagTalk.domain.wallet.dto.response.PointListResponse;
import A109.TikTagTalk.domain.wallet.service.PointHistoryService;
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
@RequestMapping("/api/exchange/points")
public class PointHistoryController {
    //포인트 전체 조회
    private final PointHistoryService pointHistoryService;

    @GetMapping
    public ResponseEntity<List<PointListResponse>> getAllPoints(@RequestHeader(required = true, name = "Authorization") String token) throws NullPointerException{

        String userId = SecurityUtil.getCurrentLoginMember().getUserId();
        if(userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        LocalDateTime now = LocalDateTime.now();
        Timestamp day = Timestamp.valueOf(now);

        List<PointListResponse> list = pointHistoryService.selectPointAll(userId);

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    //포인트 내역 조회
    @GetMapping("/list")
    public ResponseEntity<List<PointListResponse>> pointList(@RequestHeader(required = true, name = "Authorization") String token, @RequestParam String start, @RequestParam String end){
        String userId = SecurityUtil.getCurrentLoginMember().getUserId();
        if(userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Timestamp endtime = Timestamp.valueOf(end + " 23:59:59.9");

        List<PointListResponse> points = pointHistoryService.selectPointListByUserIdAndDate(userId, start, end);

        return ResponseEntity.status(HttpStatus.OK).body(points);
    }

}
