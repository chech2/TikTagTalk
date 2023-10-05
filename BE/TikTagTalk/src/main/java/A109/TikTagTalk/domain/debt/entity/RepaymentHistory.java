package A109.TikTagTalk.domain.debt.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class RepaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false)
    private LocalDate createTime;

    @Column(nullable = false)
    private Long money;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DEBT_ID")
    private Debt debt;
}
