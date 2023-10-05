package A109.TikTagTalk.domain.debt.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRepaymentHistory is a Querydsl query type for RepaymentHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRepaymentHistory extends EntityPathBase<RepaymentHistory> {

    private static final long serialVersionUID = 1520664466L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRepaymentHistory repaymentHistory = new QRepaymentHistory("repaymentHistory");

    public final DatePath<java.time.LocalDate> createTime = createDate("createTime", java.time.LocalDate.class);

    public final QDebt debt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> money = createNumber("money", Long.class);

    public QRepaymentHistory(String variable) {
        this(RepaymentHistory.class, forVariable(variable), INITS);
    }

    public QRepaymentHistory(Path<? extends RepaymentHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRepaymentHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRepaymentHistory(PathMetadata metadata, PathInits inits) {
        this(RepaymentHistory.class, metadata, inits);
    }

    public QRepaymentHistory(Class<? extends RepaymentHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.debt = inits.isInitialized("debt") ? new QDebt(forProperty("debt"), inits.get("debt")) : null;
    }

}

