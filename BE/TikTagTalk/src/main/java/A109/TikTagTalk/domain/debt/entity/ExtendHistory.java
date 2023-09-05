package A109.TikTagTalk.domain.debt.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class ExtendHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExtendStatus status;

    @Column(nullable = false)
    private LocalDateTime extendTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DEBT_ID")
    private Debt debt;
}
