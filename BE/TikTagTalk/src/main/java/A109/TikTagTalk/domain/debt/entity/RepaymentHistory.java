//package A109.TikTagTalk.domain.debt.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.springframework.data.annotation.CreatedDate;
//
//import java.time.LocalDateTime;
//
//@Getter @Setter @Builder
//@NoArgsConstructor @AllArgsConstructor
//@Entity
//public class RepaymentHistory {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @CreatedDate
//    @Column(nullable = false)
//    private LocalDateTime createTime;
//
//    @Column(nullable = false)
//    private Long money;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="DEBT_ID")
//    private Debt debt;
//}
