package A109.TikTagTalk.domain.tagRoom.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class UpdateMemberItemRequestDto {
    private Long positionX;
    private Long positionY;
    private Long positionZ;
    private AccountDto account;
    private ItemDto item;

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
