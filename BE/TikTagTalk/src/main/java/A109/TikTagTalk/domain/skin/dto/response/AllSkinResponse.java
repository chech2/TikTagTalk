package A109.TikTagTalk.domain.skin.dto.response;

import A109.TikTagTalk.domain.skin.entity.Skin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllSkinResponse {
    private Long id;
    private int price;
    private String name;
    //3d or 이미지

    public AllSkinResponse(Skin entity){
        this.id = entity.getId();
        this.price = entity.getPrice();
        this.name = entity.getName();
    }

    public AllSkinResponse(Object o) {
    }
}
