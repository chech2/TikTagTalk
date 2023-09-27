package A109.TikTagTalk.domain.account.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InsertConsumePlanRequestDto {
    private String yearAndMonth;
    private Long totalAmount;
    private Integer eatPercent;
    private Integer groceryPercent;
    private Integer ridePercent;
    private Integer shoppingPercent;
    private Integer snackPercent;
    private Integer insurancePercent;
    private Integer hobbyPercent;
    private Integer hairPercent;
    private Integer healthPercent;
    private Integer ottPercent;
    private Integer petPercent;
    private Integer travelPercent;
}
