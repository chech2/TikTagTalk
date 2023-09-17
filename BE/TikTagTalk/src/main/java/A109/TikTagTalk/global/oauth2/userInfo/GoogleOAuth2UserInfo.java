package A109.TikTagTalk.global.oauth2.userInfo;

import java.util.Map;

/**
 * 구글은 유저 정보가 감싸져 있지 않기 때문에
 * 바로 get으로 유저 정보 Key를 사용해서 꺼내면 된다.
 *
 * 구글 유저 정보 Response JSON 예시
 * {
 *    "sub": "식별값",
 *    "name": "name",
 *    "given_name": "given_name",
 *    "picture": "https//lh3.googleusercontent.com/~~",
 *    "email": "email",
 *    "email_verified": true,
 *    "locale": "ko"
 * }
 */
public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }
}
