package A109.TikTagTalk.domain.account.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
public class ConsumePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_ID")
    private Account account;
}
