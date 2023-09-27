package A109.TikTagTalk.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberOAuthSignUpDto {

    private String userId;
    private String name;
    private String introduction;
    private int avatarType;
}
