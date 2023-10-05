package A109.TikTagTalk.domain.tagRoom.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class InitMemberItemResponseDto {
    private Long position_x;
    private Long position_y;
    private Long grid_z_number;
    private Boolean isRoom;
    private Boolean wall;
    private ItemDto item;
    private Long rotation;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class ItemDto{
        private String name;
        private Boolean room;
        private int sizeX;
        private int sizeY;
    }
}
