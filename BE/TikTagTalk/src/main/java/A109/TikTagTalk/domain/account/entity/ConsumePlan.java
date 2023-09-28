package A109.TikTagTalk.domain.account.entity;

import A109.TikTagTalk.domain.user.entity.Member;
import jakarta.persistence.*;
import lombok.*;


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
    @JoinColumn(name="MEMBER_ID")
    private Member member;
}
