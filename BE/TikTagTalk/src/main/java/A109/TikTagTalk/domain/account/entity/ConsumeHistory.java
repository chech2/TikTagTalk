package A109.TikTagTalk.domain.account.entity;

import A109.TikTagTalk.domain.tag.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class ConsumeHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String detail;

    @Column(nullable=false)
    private long amount;

    @Column(nullable=false)
    private LocalDateTime consumeTime;

    @Column(nullable = false)
    private Long tagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_ID")
    private Account account;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STORE_ID")
    private Store store;
}
