package A109.TikTagTalk.domain.skin.dto.response;

import A109.TikTagTalk.domain.skin.entity.MemberSkin;
import A109.TikTagTalk.domain.skin.entity.Skin;
import A109.TikTagTalk.domain.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSkinListResponse {
    private Long id;
    private Member member;
    private Skin skin;

    public MemberSkinListResponse(MemberSkin entity) {
        this.id = entity.getId();
        this.skin = entity.getSkin();
        this.member = entity.getMember();

    }



}
