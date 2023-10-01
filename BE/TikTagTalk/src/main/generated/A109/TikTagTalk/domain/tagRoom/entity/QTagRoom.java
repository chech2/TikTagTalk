package A109.TikTagTalk.domain.tagRoom.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTagRoom is a Querydsl query type for TagRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTagRoom extends EntityPathBase<TagRoom> {

    private static final long serialVersionUID = 1783935268L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTagRoom tagRoom = new QTagRoom("tagRoom");

    public final A109.TikTagTalk.domain.account.entity.QAccount account;

    public final ListPath<Comment, QComment> comments = this.<Comment, QComment>createList("comments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final A109.TikTagTalk.domain.user.entity.QMember member;

    public final ListPath<TagRoomItem, QTagRoomItem> tagRoomItems = this.<TagRoomItem, QTagRoomItem>createList("tagRoomItems", TagRoomItem.class, QTagRoomItem.class, PathInits.DIRECT2);

    public QTagRoom(String variable) {
        this(TagRoom.class, forVariable(variable), INITS);
    }

    public QTagRoom(Path<? extends TagRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTagRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTagRoom(PathMetadata metadata, PathInits inits) {
        this(TagRoom.class, metadata, inits);
    }

    public QTagRoom(Class<? extends TagRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new A109.TikTagTalk.domain.account.entity.QAccount(forProperty("account"), inits.get("account")) : null;
        this.member = inits.isInitialized("member") ? new A109.TikTagTalk.domain.user.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

