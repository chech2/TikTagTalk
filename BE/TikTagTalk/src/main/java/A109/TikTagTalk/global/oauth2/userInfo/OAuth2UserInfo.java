package A109.TikTagTalk.global.oauth2.userInfo;

import java.util.Map;
import java.util.Objects;

public abstract class OAuth2UserInfo {

    // 추상 클래스를 상속받는 클래스에서만 사용할 수 있도록 protected 제어자 사용
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getId(); // 소셜 식별 값 : 구글 - "sub", 카카오 - "id", 네이버 - "id"
}
