package A109.TikTagTalk.domain.account.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class AllConsumePlanResonseDto {
    private int statusCode;
    private String errorMessage;
    private String yearAndMonth;
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
