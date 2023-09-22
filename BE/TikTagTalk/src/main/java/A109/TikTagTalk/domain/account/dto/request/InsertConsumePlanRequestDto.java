package A109.TikTagTalk.domain.account.dto.request;

import lombok.*;
import org.joda.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InsertConsumePlanRequestDto {
    private LocalDate yearAndMonth;
    private Long totalAmount;
    private double eatPercent;
    private double groceryPercent;
    private double ridePercent;
    private double shoppingPercent;
    private double snackPercent;
    private double insurancePercent;
    private double hobbyPercent;
    private double hairPercent;
    private double healthPercent;
    private double ottPercent;
    private double petPercent;
    private double travelPercent;
    private AccountDto account;
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class AccountDto{
        private Long id;
    }
}
