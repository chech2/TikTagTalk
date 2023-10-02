package A109.TikTagTalk.domain.debt.entity;

import A109.TikTagTalk.domain.user.entity.Member;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long money;

    @Column(nullable = false)
    private Long remainingMoney; //초기값 money와 동일

    @CreatedDate
    @Column(nullable = false)
    private LocalDate createTime;


    @Column(nullable = false)
    private LocalDate repaymentTime;

    private LocalDate extendTime;

    @Column(nullable = false)
    private int partialPay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DEBTOR_ID")
    private Member debtor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="LENDER_ID")
    private Member lender;

    @OneToMany(mappedBy = "debt")
    private List<RepaymentHistory> repaymentHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "debt")
    private List<ExtendHistory> extendHistoryList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DebtStatus status = DebtStatus.REQUESTING;

    public enum DebtStatus {
        REQUESTING("승인 대기"), DENIED("거절"), ACTIVE("상환중"), INAVTIVE("상환 완료"), ARREARS("체납");

        @Getter
        private String status;

        DebtStatus (String status){
            this.status = status;
        }
    }
}
