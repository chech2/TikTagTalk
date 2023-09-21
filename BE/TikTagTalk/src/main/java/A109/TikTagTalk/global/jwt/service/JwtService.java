package A109.TikTagTalk.global.jwt.service;

import A109.TikTagTalk.domain.user.repository.MemberRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    /*
    * JWT의 Subject와 Claim으로 userId 사용 -> 클레임의 name을 "userId"로 설정
    * JWT의 헤더에 들어오는 값 : 'Authorization(Key) = Bearer (토큰)(Value)' 형식
    */
    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String USERID_CLAIM = "userId";
    private static final String BEARER = "Bearer ";

    private final MemberRepository memberRepository;

    /*
    * AccessToken 생성 메소드
    */
    public String createAccessToken(String userId) {
        Date now = new Date();
        return JWT.create() // JWT 토큰을 생성하는 빌더 반환
                .withSubject(ACCESS_TOKEN_SUBJECT) // JWT의 Subject 지정 -> AccessToken이므로 AccessToken
                .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod)) // 토큰 만료 시간 설정

                // 클레임으로는 userId 하나만 사용
                // 추가적으로 식별자나 이름 등의 정보를 더 추가해도 된다
                // 추가할 경우 .withClaim(클레임 이름, 클래임 값)으로 설정하면 된다
                .withClaim(USERID_CLAIM, userId)
                .sign(Algorithm.HMAC512(secretKey)); // HMAC512 알고리즘 사용, application-jwt.properties에서 지정한 secret 키로 암호화
    }

    /**
     * RefreshToken 생성
     * RefreshToken은 Claim에 userId도 넣지 않으므로 withClaim() x
     */
    public String createRefreshToken() {
        Date now = new Date();
        return JWT.create()
                .withSubject(REFRESH_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
                .sign(Algorithm.HMAC512(secretKey));
    }

    /**
     * AccessToken 헤더에 실어서 보내기
     */
    public void sendAccessToken(HttpServletResponse response, String accessToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        response.setHeader(accessHeader, accessToken);
        log.info("재발급된 Access Token : {}", accessToken);
    }

    /**
     * AccessToken + RefreshToken 헤더에 실어서 보내기
     */
    public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(response, accessToken);
        setRefreshTokenHeader(response, refreshToken);
        log.info("Access Token, Refresh Token 헤더 설정 완료");
    }

    /**
     * AccessToken 헤더 설정
     */
    public void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
        response.setHeader(accessHeader, accessToken);
    }

    /**
     * RefreshToken 헤더 설정
     */
    public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
        response.setHeader(refreshHeader, refreshToken);
    }

    /**
     * 헤더에서 RefreshToken 추출
     * 토큰 형식 : Bearer XXX에서 Bearer를 제외하고 순수 토큰만 가져오기 위해서
     * 헤더를 가져온 후 "Bearer"를 삭제(""로 replace)
     */
    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    /**
     * 헤더에서 AccessToken 추출
     * 토큰 형식 : Bearer XXX에서 Bearer를 제외하고 순수 토큰만 가져오기 위해서
     * 헤더를 가져온 후 "Bearer"를 삭제(""로 replace)
     */
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(accessToken -> accessToken.startsWith(BEARER))
                .map(accessToken -> accessToken.replace(BEARER, ""));
    }

    /**
     * AccessToken에서 userId 추출
     * 추출 전에 JWT.require()로 검증기 생성
     * verify로 AccessToken 검증 후
     * 유효하다면 getClaim()으로 userId 추출
     * 유효하지 않다면 빈 Optional 객체 반환
     */
    public Optional<String> extractUserId(String accessToken) {
        try {
            // 토큰 유효성을 검사하는 데에 사용할 알고리즘이 있는 JWT verifier builder로 반환
            return Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                    .build() // 반환된 빌더로 JWT verifier 생성
                    .verify(accessToken) // accessToken을 검증하고 유효하지 않다면 예외 발생
                    .getClaim(USERID_CLAIM) // claim(userId) 가져오기
                    .asString());
        } catch (Exception e) {
            log.error("엑세스 토큰이 유효하지 않습니다.");
            return Optional.empty();
        }
    }

    /**
     * RefreshToken DB 저장(업데이트)
     * 회원 가입 시에 Member Entity가 저장될 때는, RefreshToken이 발급되기 전이기 때문에
     * DB에 Refresh Token Column에 null로 저장된다
     * 따라서, 로그인 시 Refresh Token을 발급하면서, 발급한 RefreshToken을 DB에 저장한다.
     */
    public void updateRefreshToken(String userId, String refreshToken) {
        memberRepository.findByUserId(userId)
                .ifPresentOrElse(
                        member -> member.updateRefreshToken(refreshToken),
                        () -> new Exception("일치하는 회원이 없습니다.")
                );
    }

    /**
     * 매 인증 시마다(클라이언트가 토큰을 헤더에 담아서 요청할 때마다) 토큰 검증 단계를 거치게 된다.
     * 각 AccessToken, RefreshToken의 유효성을 검증할 때 사용되는 메소드이다.
     * 토큰이 유효하지 않으면 예외를 발생시켜 false를 반환하고, 유효하다면 true를 반환한다.
     */
    public boolean isTokenValid(String token) {
        try {
            log.info("token={}", token);
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token);
            return true;
        } catch (Exception e) {
            log.error("유효하지 않은 토큰입니다. {}", e.getMessage());
            return false;
        }
    }
}
