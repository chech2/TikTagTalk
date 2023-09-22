package A109.TikTagTalk.global.oauth2;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.entity.Role;
import A109.TikTagTalk.domain.user.entity.SocialType;
import A109.TikTagTalk.global.oauth2.userInfo.GoogleOAuth2UserInfo;
import A109.TikTagTalk.global.oauth2.userInfo.KakaoOAuth2UserInfo;
import A109.TikTagTalk.global.oauth2.userInfo.NaverOAuth2UserInfo;
import A109.TikTagTalk.global.oauth2.userInfo.OAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.Random;

/**
 * 각 소셜에서 받아오는 데이터가 다르므로
 * 소셜별로 데이터를 받는 데이터를 분기 처리하는 DTO 클래스
 */
@Getter
public class OAuthAttributes {

    private String nameAttributeKey; // OAuth2 로그인 진행 시 키가 되는 필드 값, PK와 같은 의미
    private OAuth2UserInfo oAuth2UserInfo; // 소셜 타입별 로그인 유저 정보

    @Builder
    public OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    /**
     * SocialType에 맞는 메소드 호출하여 OAuthAttributes 객체 반환
     * 파라미터 : userNameAttributeName -> OAuth2 로그인 시 키(PK)가 되는 값 / attributes : OAuth 서비스의 유저 정보들
     * 소셜별 of 메소드(ofGoogle, ofKakao, ofNaver)들은 각각 소셜 로그인 API에서 제공하는
     * 회원의 식별값(id), attributes, nameAttributeKey를 저장 후 build
     */
    public static OAuthAttributes of(SocialType socialType, String userNameAttributeName, Map<String, Object> attributes) {

        if(socialType == SocialType.NAVER) {
            return ofNaver(userNameAttributeName, attributes);
        }
        else if(socialType == SocialType.KAKAO) {
            return ofKakao(userNameAttributeName, attributes);
        }
        else {
            return ofGoogle(userNameAttributeName, attributes);
        }
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new NaverOAuth2UserInfo(attributes))
                .build();
    }

    /**
     * of 메소드로 OAuthAttributes 객체가 생성되어, 유저 정보들이 담긴 OAuth2UserInfo가 소셜 타입별로 주입된 상태
     * OAuth2UserInfo에서 socialId(식별값) 가져와서 build
     * userId는 socialType + "-" + socialId로 설정
     * role은 GUEST로 설정
     */
    public Member toEntity(SocialType socialType, OAuth2UserInfo oAuth2UserInfo) {

        Random random = new Random();

        return Member.builder()
                .socialType(socialType)
                .socialId(oAuth2UserInfo.getId())
                .userId(socialType + "-" + oAuth2UserInfo.getId())
                .role(Role.GUEST)
                .avatarType(random.nextInt(0, 6) + 1)
                .attendance(1)
                .coin(0)
                .point(0)
                .build();
    }
}
