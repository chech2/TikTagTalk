package A109.TikTagTalk.domain.wallet.entity;

import A109.TikTagTalk.domain.user.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private LocalDateTime pointTime;

    @Column(nullable = false)//notnull 이라는 뜻
    private int point;

    @Column(nullable=false)
    private int balancePoint;

    @Column(nullable=false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public PointHistory(LocalDateTime pointTime, int point, String content, Member member){
        this.pointTime = pointTime;
        this.point = point;
        this.content = content;
        this.member = member;
    }
}
