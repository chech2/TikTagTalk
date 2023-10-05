package A109.TikTagTalk.domain.wallet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCoinHistory is a Querydsl query type for CoinHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoinHistory extends EntityPathBase<CoinHistory> {

    private static final long serialVersionUID = -583406004L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCoinHistory coinHistory = new QCoinHistory("coinHistory");

    public final NumberPath<Integer> balanceCoin = createNumber("balanceCoin", Integer.class);

    public final NumberPath<Integer> coin = createNumber("coin", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> coinTime = createDateTime("coinTime", java.time.LocalDateTime.class);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final A109.TikTagTalk.domain.user.entity.QMember member;

    public QCoinHistory(String variable) {
        this(CoinHistory.class, forVariable(variable), INITS);
    }

    public QCoinHistory(Path<? extends CoinHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCoinHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCoinHistory(PathMetadata metadata, PathInits inits) {
        this(CoinHistory.class, metadata, inits);
    }

    public QCoinHistory(Class<? extends CoinHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new A109.TikTagTalk.domain.user.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

