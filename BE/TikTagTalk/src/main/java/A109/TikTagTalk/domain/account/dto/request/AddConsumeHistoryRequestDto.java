package A109.TikTagTalk.domain.account.dto.request;

import A109.TikTagTalk.domain.tag.entity.Tag;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class AddConsumeHistoryRequestDto {
    private Long amount;
    private TagDto tag;
    private String storeName;
    private String detail;
    private LocalDateTime consumeTime;
    private Long accountId;
    @Getter
    @NoArgsConstructor(access= AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class TagDto{
        private Long id; //프론트에선 해당 id에 대한 tag 이름을 보여주고 값을 넘길때는 tag의 id로.
    }
}
