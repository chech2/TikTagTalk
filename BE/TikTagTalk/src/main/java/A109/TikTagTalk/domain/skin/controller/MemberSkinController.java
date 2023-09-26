package A109.TikTagTalk.domain.skin.controller;

import A109.TikTagTalk.domain.tagRoom.service.MemberItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memberskin")
@Slf4j
public class MemberSkinController {
    private final MemberItemService memberItemService;

    @GetMapping("")
    public List<>

}
