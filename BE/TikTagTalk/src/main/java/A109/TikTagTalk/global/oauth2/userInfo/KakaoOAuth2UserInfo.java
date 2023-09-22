package A109.TikTagTalk.global.oauth2.userInfo;

import java.util.Map;

/**
 * 카카오는 유저 정보가 'kakao_account.progile'으로 2번 감싸져 있는 구조 ('kakao_account' -> 'profile')
 * 따라서 get을 2번 사용하여 데이터를 꺼낸 후 사용하고 싶은 정보의 Key로 꺼내서 사용하면 된다.
 *
 * 이때, getId는 Long으로 변환되어 (String)으로 캐스팅될 수 없으므로
 * String.valueOf()를 사용하여 캐스팅 한다.
 *
 * 카카오 유저 정보 Response JSON 예시
 * {
 *     "id":123456789,
 *     "connected_at": "2022-04-11T01:45:28Z",
 *     "kakao_account": {
 *         "profile_nickname_needs_agreement": false,
 *         "profile_image_needs_agreement	": false,
 *         "profile": {
 *             "nickname": "홍길동",
 *             "thumbnail_image_url": "http://yyy.kakao.com/.../img_110x110.jpg",
 *             "profile_image_url": "http://yyy.kakao.com/dn/.../img_640x640.jpg",
 *             "is_default_image":false
 *         },
 *         "name_needs_agreement":false,
 *         "name":"홍길동",
 *         "email_needs_agreement":false,
 *         "is_email_valid": true,
 *         "is_email_verified": true,
 *         "email": "sample@sample.com",
 *         "age_range_needs_agreement":false,
 *         "age_range":"20~29",
 *         "birthyear_needs_agreement": false,
 *         "birthyear": "2002",
 *         "birthday_needs_agreement":false,
 *         "birthday":"1130",
 *         "birthday_type":"SOLAR",
 *         "gender_needs_agreement":false,
 *         "gender":"female",
 *         "phone_number_needs_agreement": false,
 *         "phone_number": "+82 010-1234-5678",
 *         "ci_needs_agreement": false,
 *         "ci": "${CI}",
 *         "ci_authenticated_at": "2019-03-11T11:25:22Z",
 *     },
 *     "properties":{
 *         "${CUSTOM_PROPERTY_KEY}": "${CUSTOM_PROPERTY_VALUE}",
 *         ...
 *     }
 * }
 */
public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }
}
