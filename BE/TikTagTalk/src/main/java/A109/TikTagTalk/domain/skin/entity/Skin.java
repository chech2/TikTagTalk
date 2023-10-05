package A109.TikTagTalk.domain.skin.entity;

import A109.TikTagTalk.domain.tagRoom.entity.Item;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Skin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private int price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

//    @Builder
//    public Skin(Long id, int price, String name){
//        this.id = id;
//        this.name = name;
//        this.price = price;
//    }
}
