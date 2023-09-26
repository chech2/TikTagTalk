package A109.TikTagTalk.domain.tagRoom.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class InitMemberItemRequestDto {
    private Long accountId;
    private TagDto tag;
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class TagDto{
        private Long id;
    }
}
