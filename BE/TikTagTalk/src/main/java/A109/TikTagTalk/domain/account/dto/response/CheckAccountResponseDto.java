package A109.TikTagTalk.domain.account.dto.response;

import A109.TikTagTalk.domain.tag.entity.Tag;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class CheckAccountResponseDto {
    private Long totalAmount;
    private List<TagDto> tagList;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class TagDto{
        private Long amount;
        private String name;
        private double percent;
    }

}
