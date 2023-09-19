package A109.TikTagTalk.domain.tagRoom.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

//    @Enumerated(EnumType.STRING)
//    private Category category;
}
