package A109.TikTagTalk.domain.account.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConsumeHistory is a Querydsl query type for ConsumeHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConsumeHistory extends EntityPathBase<ConsumeHistory> {

    private static final long serialVersionUID = -3435231L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsumeHistory consumeHistory = new QConsumeHistory("consumeHistory");

    public final QAccount account;

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final DateTimePath<java.time.LocalDateTime> consumeTime = createDateTime("consumeTime", java.time.LocalDateTime.class);

    public final StringPath detail = createString("detail");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isManual = createBoolean("isManual");

    public final A109.TikTagTalk.domain.tag.entity.QStore store;

    public final StringPath storeName = createString("storeName");

    public final A109.TikTagTalk.domain.tag.entity.QTag tag;

    public QConsumeHistory(String variable) {
        this(ConsumeHistory.class, forVariable(variable), INITS);
    }

    public QConsumeHistory(Path<? extends ConsumeHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConsumeHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConsumeHistory(PathMetadata metadata, PathInits inits) {
        this(ConsumeHistory.class, metadata, inits);
    }

    public QConsumeHistory(Class<? extends ConsumeHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new QAccount(forProperty("account"), inits.get("account")) : null;
        this.store = inits.isInitialized("store") ? new A109.TikTagTalk.domain.tag.entity.QStore(forProperty("store"), inits.get("store")) : null;
        this.tag = inits.isInitialized("tag") ? new A109.TikTagTalk.domain.tag.entity.QTag(forProperty("tag")) : null;
    }

}

