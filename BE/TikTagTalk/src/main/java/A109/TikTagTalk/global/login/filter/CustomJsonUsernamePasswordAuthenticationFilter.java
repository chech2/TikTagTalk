package A109.TikTagTalk.global.login.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 스프링 시큐리티의 폼 기반의 UsernamePasswordAuthenticationFilter를 참고하여 만든 커스텀 필터
 * 거의 구조가 같고, Type이 Json인 Login만 처리하도록 설정한 부분만 다르다. (커스텀 API용 필터 구현)
 * Username : 회원 아이디 -> userId로 설정
 * "/api/login" 요청이 들어왔을 때 JSON 값을 매핑 처리하는 필터
 */
@Slf4j
public class CustomJsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String DEFAULT_LOGIN_REQUEST_URL = "/api/login"; // "/api/login"으로 오는 요청을 처리
    private static final String HTTP_METHOD = "POST"; // 로그인 HHTP 메소드는 POST
    private static final String CONTENT_TYPE = "application/json"; // JSON 타입의 데이터로 오는 로그인 요청만 처리
    private static final String USERNAME_KEY = "userId"; // 회원 로그인 시 userId 요청 Json Key : "userId"
    private static final String PASSWORD_KEY = "password"; // 회원 로그인 시 비밀번호 요청 Json Key : "password"
    private static final AntPathRequestMatcher DEFAULT_LOGIN_PATH_REQUEST_METCHER = new AntPathRequestMatcher(DEFAULT_LOGIN_REQUEST_URL, HTTP_METHOD); // "/api/login" + POST로 온 요청에 매칭된다.

    private final ObjectMapper objectMapper;

    public CustomJsonUsernamePasswordAuthenticationFilter(ObjectMapper objectMapper) {
        super(DEFAULT_LOGIN_PATH_REQUEST_METCHER); // 위에서 설정한 "/api/login" + POST로 온 요청을 처리하기 위해 설정
        this.objectMapper = objectMapper;
    }

    /**
     * 인증 처리 메소드
     *
     * UsernamePasswordAuthenticationFilter와 동일하게 UsernamePasswordAuthenticationToken 사용
     * StreamUtils를 통해 request에서 messageBody(JSON) 반환
     * 요청 JSON Example
     * {
     *     "userId" : "example"
     *     "password" : "test123"
     * }
     * 꺼낸 messageBody를 objectMapper.readValue()로 Map으로 변환 (Key : JSON의 키 -> userId, password)
     * Map의 Key(userId, password)로 해당 userId, password 추춣 후
     * UsernamePasswordAuthenticationToken의 파라미터 principal, credentials에 대입
     *
     * AbstractAuthenticationProcessingFilter(부모)의 getAuthenticationManager()로 AuthenticationManager 객체를 반환 받은 후
     * authenticate()의 파라미터로 UsernamePasswordAuthenticationToken 객체를 넣고 인증 처리
     * (여기서 AuthenticationManager 객체는 ProviderManager -> SecurityConfig에서 설정)
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(request.getContentType() == null || !request.getContentType().equals(CONTENT_TYPE)) {
            throw new AuthenticationServiceException("Authentication Content-Type not suppored: " + request.getContentType());
        }

        String messageBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);

        // JSON 요청을 String으로 변환한 messageBody를 objectMapper.readValue()를 통해 Map으로 변환
        Map<String, String> usernamePasswordMap = objectMapper.readValue(messageBody, Map.class);

        String userId = usernamePasswordMap.get(USERNAME_KEY);
        String password = usernamePasswordMap.get(PASSWORD_KEY);


        // 인증 처리 객체인 AuthenticationManager가 인증 시 사용할 인증 대상 객체
        // userId가 인증 대상 객체의 principal,
        // password가 인증 대상 객체의 credentials
        // UsernamePasswordAuthenticationToken 인증 대상 객체를 통해 인증 처리 객체인 AuthenticationManager가 인증 성공/인증 실패 처리를 한다.
        // 인증 처리 객체 AuthenticationManager로 무슨 객체를 사용할지 setter로 SecurityConfig 클래스 파일에서 설정
        // 인증 처리 객체 AuthenticationManager는 FormLogin과 동일하게 ProviderManager를 사용
        // ProviderManager의 구현체로 DaoAuthenticationProvider 겍체 사용
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userId, password); // principal과 credentials 전달

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
