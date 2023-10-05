package A109.TikTagTalk.global.login.handler;

import A109.TikTagTalk.domain.user.dto.response.MemberLoginResponseDTO;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.exception.custom.NoSuchUserException;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import A109.TikTagTalk.domain.wallet.entity.PointHistory;
import A109.TikTagTalk.domain.wallet.repository.PointHistoryRepository;
import A109.TikTagTalk.global.jwt.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PointHistoryRepository pointHistoryRepository;

    @Value("${jwt.access.expiration}")
    private String accessTokenExpiration;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String userId = extractUserName(authentication); // 인증 정보에서 Username(userId) 추출

        String accessToken = jwtService.createAccessToken(userId); // JwtService의 createAccessToken을 사용하여 AccessToken 발급
        String refreshToken = jwtService.createRefreshToken(); // JwtService의 createRefreshToken을 사용하여 RefreshToken 발급

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken); // 응답 헤더에 AccessToken, RefreshToken 실어서 응답
        jwtService.updateRefreshToken(userId, refreshToken);

        // 응답으로 보내줄 member 정보
        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new NoSuchUserException());
        MemberLoginResponseDTO responseDTO = MemberLoginResponseDTO.toDTO(member);
        String result = objectMapper.writeValueAsString(responseDTO);
        response.getWriter().println(result);

        memberRepository.findByUserId(userId)
                .ifPresent(member1 -> {
                    member1.updateRefreshToken(refreshToken);
                    memberRepository.saveAndFlush(member1);
                });

        int i = member.getPoint();
        System.out.println("이건 될까");
        if(member.isPointsAddedToday() == false){
            member.setPointsAddedToday(true);
            i += 100;
            memberRepository.save(member);
            System.out.println("ㅅㅄㅄㅄㅄㅄㅄㅄ");
            LocalDateTime now = LocalDateTime.now();
            String content = "출석체크";
            PointHistory pointHistory=new PointHistory(now, i, content, member);
            System.out.println("시팔"+pointHistory.getPoint());
            pointHistoryRepository.save(pointHistory);
            Integer balancePoint= pointHistoryRepository.selectBalancePoint(now, member.getId());
            System.out.println(balancePoint+"밸런스포인트 시발");
            pointHistoryRepository.updateBalancePoint(pointHistory.getId(), balancePoint);
            memberRepository.updateBalancePoint(member.getId(), balancePoint);
        }
        //포인트 내역에도 저장하기
        log.info("로그인에 성공하였습니다. 아이디 : {}", userId);
        log.info("로그인에 성공하였습니다. AccessToken : {}", accessToken);
        log.info("발급된 AccessToken 민료 기간 : {}", accessTokenExpiration);
    }

    private String extractUserName(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
