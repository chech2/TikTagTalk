//package A109.TikTagTalk.domain.debt.entity;
//
//import A109.TikTagTalk.domain.user.entity.Member;
//import jakarta.persistence.*;
//import lombok.*;
//import org.springframework.data.annotation.CreatedDate;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter @Setter @Builder
//@NoArgsConstructor @AllArgsConstructor
//@Entity
//public class Debt {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private DebtStatus status;
//
//    @Column(nullable = false)
//    private Long money;
//
//    @Column(nullable = false)
//    private Long remainingMoney; //초기값 money와 동일
//
//    @CreatedDate
//    @Column(nullable = false)
//    private LocalDateTime createTime;
//
//
//    @Column(nullable = false)
//    private LocalDateTime repaymentTime;
//
//    private LocalDateTime extendTime;
//
//    @Column(nullable = false)
//    private boolean partialPay;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="DEBTOR_ID")
//    private Member debtor;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="LENDER_ID")
//    private Member lender;
//
//    @OneToMany(mappedBy = "debt")
//    private List<RepaymentHistory> repaymentHistoryList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "debt")
//    private List<ExtendHistory> extendHistoryList = new ArrayList<>();
//}
