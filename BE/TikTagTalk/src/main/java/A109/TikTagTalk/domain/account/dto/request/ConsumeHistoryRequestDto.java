package A109.TikTagTalk.domain.account.dto.request;

import lombok.*;


@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class ConsumeHistoryRequestDto {
    private String yearAndMonth;
}
