package A109.TikTagTalk.global.login.service;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // DaoAuthenticationProvicer가 설정해준 userId를 가진 member를 찾아온다.
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));

        return User.builder()
                .username(member.getUserId())
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();
    }
}
