package A109.TikTagTalk.domain.skin.controller;

import A109.TikTagTalk.domain.skin.entity.MemberSkin;
import A109.TikTagTalk.domain.skin.service.MemberSkinService;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memberskin")
@Slf4j
public class MemberSkinController {
    private final MemberSkinService memberSkinService;

    @GetMapping("")
    public List<MemberSkin> list() {
        Member member = SecurityUtil.getCurrentLoginMember();
        return memberSkinService.memberSkinList(member);
    }

}
