package A109.TikTagTalk.domain.tagRoom.entity;

import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.user.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class TagRoom {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_ID")
    private Account account;


    @OneToMany(mappedBy="tagRoom")
    private List<TagRoomItem> tagRoomItems = new ArrayList<>();

    @OneToMany(mappedBy = "tagRoom")
    private List<Comment> comments=new ArrayList<>();
}
