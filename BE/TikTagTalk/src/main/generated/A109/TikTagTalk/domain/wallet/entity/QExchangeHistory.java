package A109.TikTagTalk.domain.wallet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExchangeHistory is a Querydsl query type for ExchangeHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExchangeHistory extends EntityPathBase<ExchangeHistory> {

    private static final long serialVersionUID = -1109796358L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExchangeHistory exchangeHistory = new QExchangeHistory("exchangeHistory");

    public final NumberPath<Integer> coin = createNumber("coin", Integer.class);

    public final NumberPath<Double> exchangeRate = createNumber("exchangeRate", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final A109.TikTagTalk.domain.user.entity.QMember member;

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> pointTime = createDateTime("pointTime", java.time.LocalDateTime.class);

    public QExchangeHistory(String variable) {
        this(ExchangeHistory.class, forVariable(variable), INITS);
    }

    public QExchangeHistory(Path<? extends ExchangeHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExchangeHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExchangeHistory(PathMetadata metadata, PathInits inits) {
        this(ExchangeHistory.class, metadata, inits);
    }

    public QExchangeHistory(Class<? extends ExchangeHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new A109.TikTagTalk.domain.user.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

