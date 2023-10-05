package A109.TikTagTalk.domain.tagRoom.controller;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.InitMemberItemRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.UpdateMemberItemRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.response.InitMemberItemResponseDto;
import A109.TikTagTalk.domain.tagRoom.service.MemberItemService;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.global.util.SecurityUtil;
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


    @GetMapping("/getroom")//회원가입할 때, getroom해서 room 생성 & 더미
    public void getRoom(){
        Member member=SecurityUtil.getCurrentLoginMember();
        memberItemService.getRoom(member);
    }
    @PostMapping("/initmemberitem")
    public void initMemberItem(@RequestBody InitMemberItemRequestDto requestDto){
        Member member= SecurityUtil.getCurrentLoginMember();

        memberItemService.memberItemInit(requestDto,member);
    }
    @GetMapping("")//memberitem 조회
    public List<InitMemberItemResponseDto> findMemberItems(@RequestParam Long memberId){
        return memberItemService.findMemberItems(memberId);
    }
    @PutMapping("")
    public ResponseDto updateMemberItem(@RequestBody UpdateMemberItemRequestDto requestDto){
        Member member=SecurityUtil.getCurrentLoginMember();
        return memberItemService.updateMemberItem(requestDto,member);
    }

}
