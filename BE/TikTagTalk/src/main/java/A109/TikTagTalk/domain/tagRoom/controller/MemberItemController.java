package A109.TikTagTalk.domain.tagRoom.controller;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.InitMemberItemRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.UpdateMemberItemRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.response.InitMemberItemResponseDto;
import A109.TikTagTalk.domain.tagRoom.service.MemberItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memberitem")
@Slf4j
public class MemberItemController {
    private final MemberItemService memberItemService;
    @GetMapping("/initmemberitem") //tagid가 13인 애들 (기본 방) INIT
    public void initMemberItem(@RequestBody InitMemberItemRequestDto requestDto){
        memberItemService.memberItemInit(requestDto);
    }
    @GetMapping("")//accountID가 1인 애의 마이룸 조회
    public List<InitMemberItemResponseDto> findMemberItems(@RequestParam Long accountId){
        return memberItemService.findMemberItems(accountId);
    }
    @PutMapping("")
    public ResponseDto updateMemberItem(@RequestBody UpdateMemberItemRequestDto requestDto){
        return memberItemService.updateMemberItem(requestDto);
    }

}
