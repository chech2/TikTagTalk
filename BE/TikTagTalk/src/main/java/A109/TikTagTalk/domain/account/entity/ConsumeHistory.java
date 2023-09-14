package A109.TikTagTalk.domain.account.entity;

import A109.TikTagTalk.domain.tag.entity.Store;
import A109.TikTagTalk.domain.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
public class ConsumeHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String detail;

    private Boolean isManual;

    private String storeName; //수동 등록 할때 필요

    @Column(nullable=false)
    private long amount;

    @Column(nullable=false)
    private LocalDateTime consumeTime;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TAG_ID")
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_ID")
    private Account account;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STORE_ID")
    private Store store;
}
