package A109.TikTagTalk.domain.skin.dto.response;

import A109.TikTagTalk.domain.skin.entity.Skin;
import A109.TikTagTalk.domain.user.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BuyResponse {
    private Skin skin;
    private Member member;

    public BuyResponse(Skin entity){
        this.skin = entity;
        //this.member =
    }
}
