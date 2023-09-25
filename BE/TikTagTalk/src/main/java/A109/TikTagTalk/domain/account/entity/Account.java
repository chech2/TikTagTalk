package A109.TikTagTalk.domain.account.entity;

import A109.TikTagTalk.domain.tagRoom.entity.Comment;
import A109.TikTagTalk.domain.user.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long accountNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy="account")
    private List<ConsumeHistory> consumeHistoryList=new ArrayList<>();

    //임시임. comment
    @OneToMany(mappedBy = "account")
    private List<Comment> comments=new ArrayList<>();
}
