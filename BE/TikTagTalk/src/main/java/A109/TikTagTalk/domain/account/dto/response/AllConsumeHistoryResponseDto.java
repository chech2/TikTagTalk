package A109.TikTagTalk.domain.account.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class AllConsumeHistoryResponseDto {
    private Long amount;
    private LocalDateTime consumeTime;
    private String detail;
    private String storeName;
    private StoreDto store;
    private TagDto tag;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class StoreDto{
        private String name;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class TagDto{
        private String name;
    }
}
