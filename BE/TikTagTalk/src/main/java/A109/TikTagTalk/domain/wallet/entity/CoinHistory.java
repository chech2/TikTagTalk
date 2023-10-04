package A109.TikTagTalk.domain.wallet.entity;

import A109.TikTagTalk.domain.user.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class CoinHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private LocalDateTime coinTime;

    @Column(nullable = false)//notnull 이라는 뜻
    private int coin;

    @Column(nullable=false)
    private int balanceCoin;

    @Column(nullable=false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
