package A109.TikTagTalk.domain.tag.entity;

import A109.TikTagTalk.domain.tagRoom.entity.Item;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy="tag")
    private List<Item> itemList;
}
