//package A109.TikTagTalk.domain.tag.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor @AllArgsConstructor
//@Entity
//public class Store {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Long id;
//
//    private String name;
//
//    @OneToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="TAG_ID")
//    private Tag tag;
//}
