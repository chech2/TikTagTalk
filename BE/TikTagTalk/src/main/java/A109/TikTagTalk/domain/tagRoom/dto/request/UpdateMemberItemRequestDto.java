package A109.TikTagTalk.domain.tagRoom.dto.request;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class UpdateMemberItemRequestDto {
    private List<UpdateInfoDto> updateInfo;
    private AccountDto account;
    @Getter
    @NoArgsConstructor(access= AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class UpdateInfoDto{
        private Long positionX;
        private Long positionY;
        private Long positionZ;
        private Long rotation;
        private ItemDto item;
    }
    @Getter
    @NoArgsConstructor(access= AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class ItemDto{
        private String name;
    }
    @Getter
    @NoArgsConstructor(access= AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class AccountDto{
        private Long id;
    }

}
