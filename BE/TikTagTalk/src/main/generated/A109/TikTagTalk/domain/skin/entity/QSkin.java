package A109.TikTagTalk.domain.skin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSkin is a Querydsl query type for Skin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSkin extends EntityPathBase<Skin> {

    private static final long serialVersionUID = -2013425704L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSkin skin = new QSkin("skin");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final A109.TikTagTalk.domain.tagRoom.entity.QItem item;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public QSkin(String variable) {
        this(Skin.class, forVariable(variable), INITS);
    }

    public QSkin(Path<? extends Skin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSkin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSkin(PathMetadata metadata, PathInits inits) {
        this(Skin.class, metadata, inits);
    }

    public QSkin(Class<? extends Skin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new A109.TikTagTalk.domain.tagRoom.entity.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

