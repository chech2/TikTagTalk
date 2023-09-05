package A109.TikTagTalk.domain.user.entity;

import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.debt.entity.Debt;
import A109.TikTagTalk.domain.skin.entity.MemberSkin;
import A109.TikTagTalk.domain.tag.entity.MemberTag;
import A109.TikTagTalk.domain.tagRoom.entity.MemberItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;

    private String introduction;

    @Column(nullable = false)
    private int avatarType;

    @Column(nullable = false)
    private int profileImageType;

    @Column(nullable = false)
    private int attendance;

    @Column(nullable = false)
    private int coin;

    @Column(nullable = false)
    private int point;

    @OneToMany(mappedBy = "sender")
    private List<TalkTalk> sends = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<TalkTalk> receives = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberSkin> skinList = new ArrayList<>();

    @OneToOne(mappedBy = "member")
    private Account account;

    @OneToMany(mappedBy = "member")
    private List<MemberItem> memberItems=new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberTag> memberTags=new ArrayList<>();

    @OneToMany(mappedBy = "debtor") // 내가 돈 갚아야 하는 차용증 리스트
    private List<Debt> lenders = new ArrayList<>();

    @OneToMany(mappedBy = "lender") // 내가 돈 받아야 하는 차용증 리스트
    private List<Debt> debtors = new ArrayList<>();
}
