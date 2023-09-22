package A109.TikTagTalk.domain.account.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class InsertConsumePlanReponseDto {
    private LocalDate yearAndMonth;
    private Long totalAmount;
    private Long eatAmount;
    private Long groceryAmount;
    private Long rideAmount;
    private Long shoppingAmount;
    private Long snackAmount;
    private Long insuranceAmount;
    private Long hobbyAmount;
    private Long hairAmount;
    private Long healthAmount;
    private Long ottAmount;
    private Long petAmount;
    private Long travelAmount;
}
