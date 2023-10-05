package A109.TikTagTalk.domain.skin.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Skin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private int price;

    @Column(nullable=false)
    private String name;

//    @Builder
//    public Skin(Long id, int price, String name){
//        this.id = id;
//        this.name = name;
//        this.price = price;
//    }
}
