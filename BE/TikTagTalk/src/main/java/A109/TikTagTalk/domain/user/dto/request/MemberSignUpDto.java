package A109.TikTagTalk.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberSignUpDto {

    private String userId;
    private String password;

    /*
    * 자체 로그인이기 때문에 id/pw 이외의
    * 추가 정보도 입력
    */
    private String name;
    private String introduction;
    private int avatarType;
}
