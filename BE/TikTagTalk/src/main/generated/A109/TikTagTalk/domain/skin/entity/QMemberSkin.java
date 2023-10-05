package A109.TikTagTalk.domain.skin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberSkin is a Querydsl query type for MemberSkin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberSkin extends EntityPathBase<MemberSkin> {

    private static final long serialVersionUID = -1211743918L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberSkin memberSkin = new QMemberSkin("memberSkin");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final A109.TikTagTalk.domain.user.entity.QMember member;

    public final QSkin skin;

    public QMemberSkin(String variable) {
        this(MemberSkin.class, forVariable(variable), INITS);
    }

    public QMemberSkin(Path<? extends MemberSkin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberSkin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberSkin(PathMetadata metadata, PathInits inits) {
        this(MemberSkin.class, metadata, inits);
    }

    public QMemberSkin(Class<? extends MemberSkin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new A109.TikTagTalk.domain.user.entity.QMember(forProperty("member"), inits.get("member")) : null;
        this.skin = inits.isInitialized("skin") ? new QSkin(forProperty("skin"), inits.get("skin")) : null;
    }

}

