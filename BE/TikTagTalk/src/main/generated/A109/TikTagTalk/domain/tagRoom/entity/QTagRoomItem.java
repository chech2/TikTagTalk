package A109.TikTagTalk.domain.tagRoom.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTagRoomItem is a Querydsl query type for TagRoomItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTagRoomItem extends EntityPathBase<TagRoomItem> {

    private static final long serialVersionUID = -525177257L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTagRoomItem tagRoomItem = new QTagRoomItem("tagRoomItem");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> locationX = createNumber("locationX", Double.class);

    public final NumberPath<Double> locationY = createNumber("locationY", Double.class);

    public final NumberPath<Double> locationZ = createNumber("locationZ", Double.class);

    public final QMemberItem memberItem;

    public final QTagRoom tagRoom;

    public QTagRoomItem(String variable) {
        this(TagRoomItem.class, forVariable(variable), INITS);
    }

    public QTagRoomItem(Path<? extends TagRoomItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTagRoomItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTagRoomItem(PathMetadata metadata, PathInits inits) {
        this(TagRoomItem.class, metadata, inits);
    }

    public QTagRoomItem(Class<? extends TagRoomItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberItem = inits.isInitialized("memberItem") ? new QMemberItem(forProperty("memberItem"), inits.get("memberItem")) : null;
        this.tagRoom = inits.isInitialized("tagRoom") ? new QTagRoom(forProperty("tagRoom"), inits.get("tagRoom")) : null;
    }

}

