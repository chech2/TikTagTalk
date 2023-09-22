package A109.TikTagTalk.domain.tagRoom.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class TagRoomItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private Double locationX;

    @Column(nullable=false)
    private Double locationY;

    @Column(nullable=false)
    private Double locationZ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TAGROOM_ID")
    private TagRoom tagRoom;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ITEM_ID")
    private MemberItem memberItem;
}
