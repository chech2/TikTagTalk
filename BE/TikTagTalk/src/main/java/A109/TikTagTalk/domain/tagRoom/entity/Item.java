package A109.TikTagTalk.domain.tagRoom.entity;

import A109.TikTagTalk.domain.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique = true)
    private String name;

    private String s3Url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TAG_ID")
    private Tag tag;

    public void mappingItemAndTag(Tag tag){
        this.tag=tag;
        tag.getItemList().add(this);
    }

}
