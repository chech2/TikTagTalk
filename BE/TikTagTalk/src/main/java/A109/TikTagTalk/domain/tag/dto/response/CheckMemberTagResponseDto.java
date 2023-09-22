package A109.TikTagTalk.domain.tag.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class CheckMemberTagResponseDto {
    private TagDto tag;

    @Getter
    @NoArgsConstructor(access=AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class TagDto{
        private String name;
    }
}
