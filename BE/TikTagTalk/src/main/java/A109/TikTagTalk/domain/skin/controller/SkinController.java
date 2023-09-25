package A109.TikTagTalk.domain.skin.controller;

import A109.TikTagTalk.domain.skin.dto.response.AllSkinResponse;
import A109.TikTagTalk.domain.skin.dto.response.BuyResponse;
import A109.TikTagTalk.domain.skin.service.SkinService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class SkinController {

    @Autowired
    private final SkinService skinService;

    //모든 스킨 조회(GET)
    @GetMapping
    public ResponseEntity<List<AllSkinResponse>> list() {
        List<AllSkinResponse> skinList = skinService.selectAllSkins();
        return ResponseEntity.status(HttpStatus.OK).body(skinList);
    }

    //스킨 구매(POST)
    @PostMapping("/{skinId}")
    public ResponseEntity<BuyResponse> buySkin(@RequestHeader(required = true, name = "Authorization") String token, @PathVariable("skinId") Long skinId) {
        BuyResponse buyResponse = skinService.insertSkinItem(LocalDateTime.now(), "sun", 1L);
        return new ResponseEntity<>(buyResponse, HttpStatus.OK);
    }
}
