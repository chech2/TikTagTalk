package A109.TikTagTalk.domain.user.service;

import A109.TikTagTalk.domain.user.dto.request.MemberOAuthSignUpDto;
import A109.TikTagTalk.domain.user.dto.request.MemberSignUpDto;
import A109.TikTagTalk.domain.user.dto.response.MemberLoginResponseDTO;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.entity.Role;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import A109.TikTagTalk.global.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; // SecurityCongfig에서 Bean 등록해줌
    private final JwtService jwtService;

    public void singUp(MemberSignUpDto memberSignUpDto) throws Exception{

        if(memberRepository.findByUserId(memberSignUpDto.getUserId()).isPresent()) {
            throw new Exception("이미 존재하는 회원 아이디입니다.");
        }

        /*
        * 자체 로그인이기 때문에 클라이언트 요청에서 추가 정보까지 다 받아와서
        * Builder로 추가 정보를 포함한 User Entity 생성 후 DB에 저장
        */
        Member member = Member.builder()
                .userId(memberSignUpDto.getUserId())
                .password(passwordEncoder.encode(memberSignUpDto.getPassword()))
                .name(memberSignUpDto.getName())
                .introduction(memberSignUpDto.getIntroduction())
                .avatarType(memberSignUpDto.getAvatarType())
                .role(Role.USER)
                .attendance(1)
                .coin(0)
                .point(0)
                .build();

        memberRepository.save(member);
    }

    public MemberLoginResponseDTO oauthSignUp(HttpServletResponse response, Member member, MemberOAuthSignUpDto memberOAuthSignUpDto) throws Exception {

        // 받은 정보로 수정
        member.setUserId(memberOAuthSignUpDto.getUserId());
        member.setName(memberOAuthSignUpDto.getName());
        member.setIntroduction(memberOAuthSignUpDto.getIntroduction());
        member.setAvatarType(memberOAuthSignUpDto.getAvatarType());
        member.setRole(Role.USER);
        memberRepository.save(member);

        // accessToken과 refreshToken 재발급 후 보내주기
        String accessToken = jwtService.createAccessToken(member.getUserId());
        String refreshToken = jwtService.createRefreshToken();

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(member.getUserId(), refreshToken);

        return MemberLoginResponseDTO.toDTO(member);
    }

    public MemberLoginResponseDTO oauthLoginSuccess(HttpServletResponse response, Member member) {

        // accessToken과 refreshToken 재발급 후 보내주기
        String accessToken = jwtService.createAccessToken(member.getUserId());
        String refreshToken = jwtService.createRefreshToken();

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(member.getUserId(), refreshToken);

        return MemberLoginResponseDTO.toDTO(member);
    }
}
