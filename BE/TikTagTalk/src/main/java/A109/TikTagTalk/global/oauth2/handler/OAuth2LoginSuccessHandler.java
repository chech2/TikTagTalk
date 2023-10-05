package A109.TikTagTalk.global.oauth2.handler;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.entity.Role;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import A109.TikTagTalk.domain.wallet.entity.PointHistory;
import A109.TikTagTalk.domain.wallet.repository.PointHistoryRepository;
import A109.TikTagTalk.global.jwt.service.JwtService;
import A109.TikTagTalk.global.oauth2.CustomOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${current.front}")
    private String frontBaseURL;

    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login 성공!");
        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            loginSuccess(request, response, oAuth2User);
        } catch (Exception e) {
            log.error("onAuthenticationSuccess: {}", e.getMessage());
            throw e;
        }
    }

    private void loginSuccess(HttpServletRequest request, HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {

        // response 세팅
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        String accessToken = jwtService.createAccessToken(oAuth2User.getUserId());
        String refreshToken = jwtService.createRefreshToken();

        String redirectUrl = UriComponentsBuilder.fromUriString(frontBaseURL + "/oauth/redirect/" + accessToken)
                .build().toUriString();

        if(oAuth2User.getRole() == Role.GUEST) {
            redirectUrl = UriComponentsBuilder.fromUriString(frontBaseURL + "/oauth2/sign-up/" + accessToken)
                    .build().toUriString();
        }

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(oAuth2User.getUserId(), refreshToken);
        redirectStrategy.sendRedirect(request, response, redirectUrl);

        Member member = memberRepository.findByUserId(oAuth2User.getUserId()).get();
        int i = member.getPoint();

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
    }
}
