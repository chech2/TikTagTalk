package A109.TikTagTalk.domain.user.controller;

import A109.TikTagTalk.domain.user.dto.request.MemberOAuthSignUpDto;
import A109.TikTagTalk.domain.user.dto.request.MemberSignUpDto;
import A109.TikTagTalk.domain.user.dto.response.MemberLoginResponseDTO;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.service.MemberService;
import A109.TikTagTalk.global.util.SecurityUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
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

    @PostMapping("/oauth/sign-up")
    public ResponseEntity<MemberLoginResponseDTO> oauthSignUp(HttpServletResponse response, @RequestBody MemberOAuthSignUpDto memberOAuthSignUpDto) throws Exception {

        Member member = SecurityUtil.getCurrentLoginMember();

        MemberLoginResponseDTO memberLoginResponseDTO = memberService.oauthSignUp(response, member, memberOAuthSignUpDto);

        return new ResponseEntity<>(memberLoginResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/oauth/success")
    public ResponseEntity<MemberLoginResponseDTO> oauthLoginSuccess(HttpServletResponse response) throws Exception {

        Member member = SecurityUtil.getCurrentLoginMember();

        MemberLoginResponseDTO memberLoginResponseDTO = memberService.oauthLoginSuccess(response, member);

        return new ResponseEntity<>(memberLoginResponseDTO, HttpStatus.OK);
    }
}

