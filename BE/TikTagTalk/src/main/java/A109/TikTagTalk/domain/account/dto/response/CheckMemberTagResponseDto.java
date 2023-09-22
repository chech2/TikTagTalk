package A109.TikTagTalk.domain.account.dto.response;

import A109.TikTagTalk.domain.tag.entity.Tag;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class CheckMemberTagResponseDto {
    private TagDto tag;
    private Long amount;
    private Long count;
    @Getter
    @Builder
    @NoArgsConstructor(access= AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class TagDto{
        private Long id;
    }
}
