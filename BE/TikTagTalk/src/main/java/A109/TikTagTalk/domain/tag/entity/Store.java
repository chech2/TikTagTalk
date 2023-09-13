package A109.TikTagTalk.domain.tag.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TAG_ID")
    private Tag tag;

    @Builder
    public Store(String name,Tag tag){
        this.name=name;
        this.tag=tag;
    }
}
