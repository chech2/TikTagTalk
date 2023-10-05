package A109.TikTagTalk.domain.tagRoom.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberItem is a Querydsl query type for MemberItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberItem extends EntityPathBase<MemberItem> {

    private static final long serialVersionUID = 628597982L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberItem memberItem = new QMemberItem("memberItem");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath inroom = createBoolean("inroom");

    public final QItem item;

    public final A109.TikTagTalk.domain.user.entity.QMember member;

    public final NumberPath<Long> positionX = createNumber("positionX", Long.class);

    public final NumberPath<Long> positionY = createNumber("positionY", Long.class);

    public final NumberPath<Long> positionZ = createNumber("positionZ", Long.class);

    public final NumberPath<Long> rotation = createNumber("rotation", Long.class);

    public final BooleanPath wall = createBoolean("wall");

    public QMemberItem(String variable) {
        this(MemberItem.class, forVariable(variable), INITS);
    }

    public QMemberItem(Path<? extends MemberItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberItem(PathMetadata metadata, PathInits inits) {
        this(MemberItem.class, metadata, inits);
    }

    public QMemberItem(Class<? extends MemberItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
        this.member = inits.isInitialized("member") ? new A109.TikTagTalk.domain.user.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

