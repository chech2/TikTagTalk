package A109.TikTagTalk.domain.debt.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExtendHistory is a Querydsl query type for ExtendHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExtendHistory extends EntityPathBase<ExtendHistory> {

    private static final long serialVersionUID = -844920119L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExtendHistory extendHistory = new QExtendHistory("extendHistory");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final QDebt debt;

    public final DateTimePath<java.time.LocalDateTime> extendTime = createDateTime("extendTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<ExtendStatus> status = createEnum("status", ExtendStatus.class);

    public QExtendHistory(String variable) {
        this(ExtendHistory.class, forVariable(variable), INITS);
    }

    public QExtendHistory(Path<? extends ExtendHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExtendHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExtendHistory(PathMetadata metadata, PathInits inits) {
        this(ExtendHistory.class, metadata, inits);
    }

    public QExtendHistory(Class<? extends ExtendHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.debt = inits.isInitialized("debt") ? new QDebt(forProperty("debt"), inits.get("debt")) : null;
    }

}

