package A109.TikTagTalk.domain.tag.controller;

import A109.TikTagTalk.domain.tag.dto.request.CheckMemberTagRequestDto;
import A109.TikTagTalk.domain.tag.dto.response.CheckMemberTagResponseDto;
import A109.TikTagTalk.domain.tag.service.MemberTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/membertag")
@Slf4j
public class MemberTagController {
    private final MemberTagService memberTagService;
    @GetMapping("")
    public List<CheckMemberTagResponseDto> checkMemberTagList(@RequestBody CheckMemberTagRequestDto requestDto){

        return memberTagService.checkMemberTagList(requestDto);
    }

}
