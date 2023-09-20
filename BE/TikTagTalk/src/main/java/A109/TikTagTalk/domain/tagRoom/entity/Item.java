package A109.TikTagTalk.domain.tagRoom.entity;

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

//    @Enumerated(EnumType.STRING)
//    private Category category;
}
