package A109.TikTagTalk.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1675751427L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final A109.TikTagTalk.domain.account.entity.QAccount account;

    public final NumberPath<Integer> attendance = createNumber("attendance", Integer.class);

    public final NumberPath<Integer> avatarType = createNumber("avatarType", Integer.class);

    public final NumberPath<Integer> coin = createNumber("coin", Integer.class);

    public final ListPath<A109.TikTagTalk.domain.tagRoom.entity.Comment, A109.TikTagTalk.domain.tagRoom.entity.QComment> comments = this.<A109.TikTagTalk.domain.tagRoom.entity.Comment, A109.TikTagTalk.domain.tagRoom.entity.QComment>createList("comments", A109.TikTagTalk.domain.tagRoom.entity.Comment.class, A109.TikTagTalk.domain.tagRoom.entity.QComment.class, PathInits.DIRECT2);

    public final ListPath<A109.TikTagTalk.domain.account.entity.ConsumePlan, A109.TikTagTalk.domain.account.entity.QConsumePlan> consumePlans = this.<A109.TikTagTalk.domain.account.entity.ConsumePlan, A109.TikTagTalk.domain.account.entity.QConsumePlan>createList("consumePlans", A109.TikTagTalk.domain.account.entity.ConsumePlan.class, A109.TikTagTalk.domain.account.entity.QConsumePlan.class, PathInits.DIRECT2);

    public final ListPath<A109.TikTagTalk.domain.debt.entity.Debt, A109.TikTagTalk.domain.debt.entity.QDebt> debtors = this.<A109.TikTagTalk.domain.debt.entity.Debt, A109.TikTagTalk.domain.debt.entity.QDebt>createList("debtors", A109.TikTagTalk.domain.debt.entity.Debt.class, A109.TikTagTalk.domain.debt.entity.QDebt.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction = createString("introduction");

    public final ListPath<A109.TikTagTalk.domain.debt.entity.Debt, A109.TikTagTalk.domain.debt.entity.QDebt> lenders = this.<A109.TikTagTalk.domain.debt.entity.Debt, A109.TikTagTalk.domain.debt.entity.QDebt>createList("lenders", A109.TikTagTalk.domain.debt.entity.Debt.class, A109.TikTagTalk.domain.debt.entity.QDebt.class, PathInits.DIRECT2);

    public final ListPath<A109.TikTagTalk.domain.tagRoom.entity.MemberItem, A109.TikTagTalk.domain.tagRoom.entity.QMemberItem> memberItems = this.<A109.TikTagTalk.domain.tagRoom.entity.MemberItem, A109.TikTagTalk.domain.tagRoom.entity.QMemberItem>createList("memberItems", A109.TikTagTalk.domain.tagRoom.entity.MemberItem.class, A109.TikTagTalk.domain.tagRoom.entity.QMemberItem.class, PathInits.DIRECT2);

    public final ListPath<A109.TikTagTalk.domain.tag.entity.MemberTag, A109.TikTagTalk.domain.tag.entity.QMemberTag> memberTags = this.<A109.TikTagTalk.domain.tag.entity.MemberTag, A109.TikTagTalk.domain.tag.entity.QMemberTag>createList("memberTags", A109.TikTagTalk.domain.tag.entity.MemberTag.class, A109.TikTagTalk.domain.tag.entity.QMemberTag.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final BooleanPath pointsAddedToday = createBoolean("pointsAddedToday");

    public final ListPath<TalkTalk, QTalkTalk> receives = this.<TalkTalk, QTalkTalk>createList("receives", TalkTalk.class, QTalkTalk.class, PathInits.DIRECT2);

    public final StringPath refreshToken = createString("refreshToken");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final ListPath<TalkTalk, QTalkTalk> sends = this.<TalkTalk, QTalkTalk>createList("sends", TalkTalk.class, QTalkTalk.class, PathInits.DIRECT2);

    public final ListPath<A109.TikTagTalk.domain.skin.entity.MemberSkin, A109.TikTagTalk.domain.skin.entity.QMemberSkin> skinList = this.<A109.TikTagTalk.domain.skin.entity.MemberSkin, A109.TikTagTalk.domain.skin.entity.QMemberSkin>createList("skinList", A109.TikTagTalk.domain.skin.entity.MemberSkin.class, A109.TikTagTalk.domain.skin.entity.QMemberSkin.class, PathInits.DIRECT2);

    public final StringPath socialId = createString("socialId");

    public final EnumPath<SocialType> socialType = createEnum("socialType", SocialType.class);

    public final StringPath userId = createString("userId");

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new A109.TikTagTalk.domain.account.entity.QAccount(forProperty("account"), inits.get("account")) : null;
    }

}

