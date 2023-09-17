package A109.TikTagTalk.global.oauth2;

import A109.TikTagTalk.domain.user.entity.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

/**
 * CustomOAuth2User를 구현하는 이유는
 * Resource Server에서 제공하지 않는 추가 정보들을 내 서비스에서 가지고 있기 위함이다.
 * Resource Sever에서 제공하는 정보만 사용해도 된다면
 * 굳이 CustomOAuth2User를 구현하지 않고, 일반 DefaultOAuth2User를 사용하면 된다.
 */
@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

    private String userId;
    private Role role;

    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey,
                            String userId, Role role) {
        super(authorities, attributes, nameAttributeKey);
        this.userId = userId;
        this.role = role;
    }
}
