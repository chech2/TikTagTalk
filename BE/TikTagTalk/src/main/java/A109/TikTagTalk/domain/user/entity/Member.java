package A109.TikTagTalk.domain.user.entity;

import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.entity.ConsumePlan;
import A109.TikTagTalk.domain.debt.entity.Debt;
import A109.TikTagTalk.domain.skin.entity.MemberSkin;
import A109.TikTagTalk.domain.tag.entity.MemberTag;
import A109.TikTagTalk.domain.tagRoom.entity.Comment;
import A109.TikTagTalk.domain.tagRoom.entity.MemberItem;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userId; // 일반 로그인 가입 시 id로 사용 (소셜 로그인이라면 처음엔 socialType_socialId 값 -> 사용자가 수정)

    private String password; // 일반 로그인 가입 시 pw로 사용 (소셜 로그인이라면 랜덤 값)

    /*
    * role은 GUEST와 USER로 구분
    * 자체 로그인 시에는 회원가입 시 상관없이 USER로 role을 정하여 DB에 저장
    * OAuth2 로그인 시에는 첫 로그인 시에 role을 GUEST로 설정하고,
    * 추가 정보 입력 시 USER로 업데이트 되게 구현
    */
    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType; // KAKAO, NAVER, GOOGLE

    private String socialId; // 로그인한 소셜 타입의 실별자 값 (일반 로그인인 경우 null)

    /*
    * JWT를 사용하여 로그인 성공 시 AccessToken, RefreshToken을 발행하는데
    * 발행된 RefreshToken을 Member Entity에 저장한다.
    */
    private String refreshToken; // 리프레시 토큰

    private String name;

    private String introduction;

    @Column(nullable = false)
    private int avatarType;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_ID")
    private Account account;

    @OneToMany(mappedBy = "member")
    private List<MemberItem> memberItems=new ArrayList<>();

    @OneToMany(mappedBy="member")
    private List<ConsumePlan> consumePlans=new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberTag> memberTags=new ArrayList<>();

    @OneToMany(mappedBy = "debtor") // 내가 돈 갚아야 하는 차용증 리스트
    private List<Debt> lenders = new ArrayList<>();

    @OneToMany(mappedBy = "lender") // 내가 돈 받아야 하는 차용증 리스트
    private List<Debt> debtors = new ArrayList<>();


    @OneToMany(mappedBy = "member")
    private List<Comment> comments=new ArrayList<>();

    // 유저 권한 설정 메소드
    public void authorizeUser() {
        this.role = Role.USER;
    }

    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

}
