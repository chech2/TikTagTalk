package A109.TikTagTalk.domain.debt.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDebt is a Querydsl query type for Debt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDebt extends EntityPathBase<Debt> {

    private static final long serialVersionUID = -1622684412L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDebt debt = new QDebt("debt");

    public final DatePath<java.time.LocalDate> createTime = createDate("createTime", java.time.LocalDate.class);

    public final A109.TikTagTalk.domain.user.entity.QMember debtor;

    public final ListPath<ExtendHistory, QExtendHistory> extendHistoryList = this.<ExtendHistory, QExtendHistory>createList("extendHistoryList", ExtendHistory.class, QExtendHistory.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> extendTime = createDate("extendTime", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final A109.TikTagTalk.domain.user.entity.QMember lender;

    public final NumberPath<Long> money = createNumber("money", Long.class);

    public final NumberPath<Integer> partialPay = createNumber("partialPay", Integer.class);

    public final NumberPath<Long> remainingMoney = createNumber("remainingMoney", Long.class);

    public final ListPath<RepaymentHistory, QRepaymentHistory> repaymentHistoryList = this.<RepaymentHistory, QRepaymentHistory>createList("repaymentHistoryList", RepaymentHistory.class, QRepaymentHistory.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> repaymentTime = createDate("repaymentTime", java.time.LocalDate.class);

    public final EnumPath<Debt.DebtStatus> status = createEnum("status", Debt.DebtStatus.class);

    public QDebt(String variable) {
        this(Debt.class, forVariable(variable), INITS);
    }

    public QDebt(Path<? extends Debt> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDebt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDebt(PathMetadata metadata, PathInits inits) {
        this(Debt.class, metadata, inits);
    }

    public QDebt(Class<? extends Debt> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.debtor = inits.isInitialized("debtor") ? new A109.TikTagTalk.domain.user.entity.QMember(forProperty("debtor"), inits.get("debtor")) : null;
        this.lender = inits.isInitialized("lender") ? new A109.TikTagTalk.domain.user.entity.QMember(forProperty("lender"), inits.get("lender")) : null;
    }

}

