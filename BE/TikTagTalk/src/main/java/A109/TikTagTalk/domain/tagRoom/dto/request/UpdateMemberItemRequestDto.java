package A109.TikTagTalk.domain.tagRoom.dto.request;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class UpdateMemberItemRequestDto {
    private List<UpdateInfoDto> updateInfo;
    @Getter
    @NoArgsConstructor(access= AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class UpdateInfoDto{
        private Long positionX;
        private Long positionY;
        private Long positionZ;
        private Long rotation;
        private Boolean isRoom;
        private ItemDto item;
    }
    @Getter
    @NoArgsConstructor(access= AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class ItemDto{
        private String name;
    }

}
