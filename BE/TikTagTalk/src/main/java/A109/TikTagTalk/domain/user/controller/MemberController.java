package A109.TikTagTalk.domain.user.controller;

import A109.TikTagTalk.domain.user.dto.request.MemberSignUpDto;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.service.MemberService;
import A109.TikTagTalk.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up") // 자체 회원 가입
    public ResponseEntity<String> signUp(@RequestBody MemberSignUpDto memberSignUpDto) throws Exception {
        memberService.singUp(memberSignUpDto);
        return new ResponseEntity<>("로그인에 성공했습니다.", HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() throws Exception {

        Member member = SecurityUtil.getCurrentLoginMember();

        return new ResponseEntity<>(member.getUserId(), HttpStatus.OK);
    }
}

