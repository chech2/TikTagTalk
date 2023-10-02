package A109.TikTagTalk.domain.account.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class AllConsumePlanRequestDto {
    private String yearAndMonth;
}
