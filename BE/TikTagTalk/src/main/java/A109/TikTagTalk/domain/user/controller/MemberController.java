package A109.TikTagTalk.domain.user.controller;

import A109.TikTagTalk.domain.user.dto.request.FindMemberRequestDto;
import A109.TikTagTalk.domain.user.dto.request.MemberOAuthSignUpDto;
import A109.TikTagTalk.domain.user.dto.request.MemberSignUpDto;
import A109.TikTagTalk.domain.user.dto.response.FindMemberResponseDto;
import A109.TikTagTalk.domain.user.dto.response.MemberLoginResponseDTO;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.service.MemberService;
import A109.TikTagTalk.global.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/members")
@Tag(name="Member Controller", description = "멤버 API")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "sign up", description = "자체 회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED")
    })
    @PostMapping("/sign-up") // 자체 회원 가입
    public ResponseEntity<String> signUp(@RequestBody MemberSignUpDto memberSignUpDto) throws Exception {
        memberService.singUp(memberSignUpDto);
        return new ResponseEntity<>("로그인에 성공했습니다.", HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() throws Exception {

        Member member = SecurityUtil.getCurrentLoginMember();
        log.info("memberId={}", member.getId());

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

    @GetMapping("/search")
    @Operation(summary = "find member by userId", description = "아이디(userId)로 멤버 찾기 : 톡톡 친구 찾기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "userId를 앞에 포함하는 모든 멤버 리스트 반환(like userId%)")
    })
    public ResponseEntity<List<FindMemberResponseDto>> findMember(@RequestBody FindMemberRequestDto requestDto) {

        Member loginMember = SecurityUtil.getCurrentLoginMember();

        List<FindMemberResponseDto> responseDtoList = memberService.findMemberByUserId(loginMember.getId(), requestDto.getUserId());

        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/recommendation")
    @Operation(summary = "recommed member", description = "추천 친구 리스트(아직 추천 알고리즘 없음. 모든 멤버 반환됨)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "추천 멤버 리스트 반환")
    })
    public ResponseEntity<List<FindMemberResponseDto>> recommendMemberList() {

        Member loginMember = SecurityUtil.getCurrentLoginMember();

        List<FindMemberResponseDto> findMemberResponseDtos = memberService.recommendMemberList(loginMember);
        return new ResponseEntity<>(findMemberResponseDtos, HttpStatus.OK);
    }
}

