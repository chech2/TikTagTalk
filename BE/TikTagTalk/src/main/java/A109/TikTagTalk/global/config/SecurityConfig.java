package A109.TikTagTalk.global.config;

import A109.TikTagTalk.domain.user.repository.MemberRepository;
import A109.TikTagTalk.global.jwt.filter.JwtAuthenticationProcessingFilter;
import A109.TikTagTalk.global.jwt.service.JwtService;
import A109.TikTagTalk.global.login.filter.CustomJsonUsernamePasswordAuthenticationFilter;
import A109.TikTagTalk.global.login.handler.LoginFailureHandler;
import A109.TikTagTalk.global.login.handler.LoginSuccessHandler;
import A109.TikTagTalk.global.login.service.LoginService;
import A109.TikTagTalk.global.oauth2.handler.OAuth2LoginFailureHandler;
import A109.TikTagTalk.global.oauth2.handler.OAuth2LoginSuccessHandler;
import A109.TikTagTalk.global.oauth2.service.CustomOAuth2UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;

/**
 * 인증은 CustomJsonUsernamePasswordAuthenticationFilter에서 authenticate()로 인증된 사용자로 처리
 * JwtAuthenticationProcessingFilter는 AccessToken, RefreshToken 재발급
 */
@Configuration
@EnableWebSecurity // Spring Security 기능 사용
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${current.front}")
    private String frontBaseURL;

    private final LoginService loginService;
    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOriginPattern("http://localhost:3000");
        config.addAllowedOriginPattern("http://j9a109.p.ssafy.io:3000");
        config.addAllowedOriginPattern("https://j9a109.p.ssafy.io:3000");
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization");
        config.addExposedHeader("Authorization-refresh");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable) // httpBasic 사용 x
                .cors((cors) ->
                        cors.configurationSource(corsConfigurationSource()))
                .csrf((csrf) ->
                        csrf.disable()) // csrf 보안 사용 x
                .formLogin(AbstractHttpConfigurer::disable) // FormLogin 사용 x
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용하지 않으므로 STATELESS로 설정
                .requiresChannel((requiresChannel) -> requiresChannel
                        .requestMatchers("/api/oauth2/authorization").requiresSecure()
                )
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests // URL별 권한 관리 옵션
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/api/members/sign-up").permitAll() // 회원가입 접근 가능
                        .requestMatchers("/api/refresh").permitAll()
                        .requestMatchers("/api/members/check-userId").permitAll()
                        .requestMatchers("/swagger-ui/*").permitAll() // swagger 관련 설정(개발 종료 후 삭제)
                        .requestMatchers("/v3/api-docs").permitAll() // swagger 관련 설정(개발 종료 후 삭제)
                        .requestMatchers("/v3/api-docs/swagger-config").permitAll() // swagger 관련 설정(개발 종료 후 삭제)
                        .requestMatchers("/swagger").permitAll() // swagger 관련 설정(개발 종료 후 삭제)
                        .anyRequest().authenticated() // 위의 경로 이외에는 모두 인증된 사용자만 접근 가능
                )

                .oauth2Login((oauth2login) -> oauth2login // 소셜 로그인 설정
                        .successHandler(oAuth2LoginSuccessHandler) // 동의하고 계속하기를 눌렀을 대 Handler 설정
                        .failureHandler(oAuth2LoginFailureHandler) // 소셜 로그인 실패 시 핸들러 설정
                        .authorizationEndpoint((endpoint) -> endpoint
                                .baseUri("/api/oauth2/authorization"))
                        .redirectionEndpoint((endpoint) ->
                                endpoint.baseUri("/login/oauth2/code/*"))
                        .userInfoEndpoint((endpoint) -> endpoint
                                .userService(customOAuth2UserService)) // customUserService 설정
                )

                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout")) // 로그아웃 요청의 매칭 패턴 지정
                        .logoutSuccessHandler((request, response, authentication) -> { // 로그아웃 성공 시 호출되는 핸들러
                            return; // 특별한 동작 없이 그냥 리턴
                        })
                        .invalidateHttpSession(true) // HttpSession을 무효화 -> 이때 SecurityContextHolder가 비워짐
                )

                .exceptionHandling((handling -> handling
                        .authenticationEntryPoint(new AuthenticationEntryPoint() {
                            @Override
                            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                                response.setStatus(462);
                            }
                        }))
                );

        // 원래 스프링 시큐리티 필터 순서가 LogoutFilter 이후에 로그인 필터 동작
        // 따라서, LogoutFilter 이후에 우리가 만든 필터 동작하도록 설정
        // 순서 : LogoutFilter -> JwtAuthenticationProcessingFilter -> CustomJsonUsernamePasswordAuthenticationFilter
        http.addFilterAfter(customJsonUsernamePasswordAuthenticationFilter(), LogoutFilter.class);
        http.addFilterBefore(jwtAuthenticationProcessingFilter(), CustomJsonUsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * AuthenticationManager 설정 후 등록
     * PasswordEncoder를 사용하는 AuthenticationProvider 지정 (PasswordEncoder는 위에서 등록한 PasswordEncoder 사용)
     * FormLogin(기존 스프링 시큐리티 로그인)과 동일하게 DaoAuthenticationProvicer 사용
     * UserDetailsService는 커스텀 LoginServcie로 등록
     * 또한, FormLogin과 동일하게 AuthenticationManager로는 구현체인 ProviderManager 사용 (return ProviderNamager)
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(loginService);
        return new ProviderManager(provider);
    }

    /**
     * 로그인 성공 시 호출되는 LoginSuccessJWTProviderHandler 빈 등록
     */
    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler(jwtService, memberRepository);
    }

    /**
     * 로그인 실패 시 호출되는 LoginFailureHandler 빈 등록
     */
    @Bean
    public LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
    }

    /**
     * CustomJsonUsernamePasswordAuthenticationFilter 빈 등록
     * 커스텀 필터를 사용하기 위해 만든 커스텀 필터를 Bean으로 등록
     * setAuthenticationManager(authenticationManager())로 위에서 등록한 AuthenticationManager(ProviderManager) 설정
     * 로그인 성공 시 호출할 handler, 실패 시 호출할 handler로 위에서 등록한 handler 설정
     */
    @Bean
    public CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordAuthenticationFilter() {
        CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordAuthenticationFilter
                = new CustomJsonUsernamePasswordAuthenticationFilter(objectMapper);
        customJsonUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
        customJsonUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
        customJsonUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return customJsonUsernamePasswordAuthenticationFilter;
    }

    @Bean
    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
        JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter
                = new JwtAuthenticationProcessingFilter(jwtService, memberRepository);
        return jwtAuthenticationProcessingFilter;
    }
}
