package A109.TikTagTalk.domain.skin.controller;

import A109.TikTagTalk.domain.skin.dto.response.AllSkinResponse;
import A109.TikTagTalk.domain.skin.dto.response.BuyResponse;
import A109.TikTagTalk.domain.skin.entity.Skin;
import A109.TikTagTalk.domain.skin.service.SkinService;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.global.util.SecurityUtil;
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
@RequestMapping("/api/skin")
public class SkinController {

    @Autowired
    private final SkinService skinService;

    @GetMapping("/dummyskin") //아이템 다 돌아서 isSkin이 true인 애들 다 skin에 save
    public void createDummySkin(){
        skinService.createDummySkin();
    }

    //모든 스킨 조회(GET)
    @GetMapping("")
    public List<AllSkinResponse> allSkinResponseList() {
        return skinService.selectAllSkins();
    }

    //스킨 구매(POST)
    @PostMapping("/{skinId}")
    public ResponseEntity<BuyResponse> buySkin(@RequestHeader(required = true, name = "Authorization") String token, @PathVariable("skinId") Long skinId) {
        Member member = SecurityUtil.getCurrentLoginMember();
        BuyResponse buyResponse = skinService.insertSkinItem(LocalDateTime.now(), member.getId(), skinId);
        return new ResponseEntity<>(buyResponse, HttpStatus.OK);
    }
}
