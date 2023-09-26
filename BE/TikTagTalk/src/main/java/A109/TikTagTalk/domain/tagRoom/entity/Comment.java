package A109.TikTagTalk.domain.tagRoom.entity;

import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.user.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TAGROOM_ID")
    private TagRoom tagRoom;

    private String content;
    private LocalDateTime writtenTime;

    public void mappingMemberAndTagRoomAndComment(Member member,TagRoom tagRoom){
        this.member=member;
        this.tagRoom=tagRoom;
        member.getComments().add(this);
        tagRoom.getComments().add(this);
    }

}
