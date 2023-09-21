//package A109.TikTagTalk.domain.tagRoom.entity;
//
//import A109.TikTagTalk.domain.user.entity.Member;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Getter @Setter @Builder
//@NoArgsConstructor @AllArgsConstructor
//@Entity
//public class MemberItem {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="MEMBER_ID")
//    private Member member;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="ITEM_ID")
//    private Item item;
//}
