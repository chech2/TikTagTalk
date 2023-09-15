package A109.TikTagTalk.domain.account.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class ModifyConsumeHistoryRequestDto {
    private String storeName;
    private Long amount;
    private LocalDateTime consumeTime;
    private TagDto tag;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class TagDto{
        private Long id;
    }
}
