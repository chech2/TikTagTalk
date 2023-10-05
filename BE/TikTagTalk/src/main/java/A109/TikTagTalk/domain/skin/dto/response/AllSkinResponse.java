package A109.TikTagTalk.domain.skin.dto.response;

import A109.TikTagTalk.domain.skin.entity.Skin;
import A109.TikTagTalk.domain.tagRoom.entity.Item;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AllSkinResponse {
    private Long id;
    private int price;
    private ItemDto item;

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class ItemDto{
        private String name;
    }
}
