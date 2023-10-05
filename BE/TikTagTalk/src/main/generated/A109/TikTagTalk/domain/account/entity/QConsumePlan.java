package A109.TikTagTalk.domain.account.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConsumePlan is a Querydsl query type for ConsumePlan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConsumePlan extends EntityPathBase<ConsumePlan> {

    private static final long serialVersionUID = -775393860L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsumePlan consumePlan = new QConsumePlan("consumePlan");

    public final NumberPath<Long> eatAmount = createNumber("eatAmount", Long.class);

    public final NumberPath<Long> groceryAmount = createNumber("groceryAmount", Long.class);

    public final NumberPath<Long> hairAmount = createNumber("hairAmount", Long.class);

    public final NumberPath<Long> healthAmount = createNumber("healthAmount", Long.class);

    public final NumberPath<Long> hobbyAmount = createNumber("hobbyAmount", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> insuranceAmount = createNumber("insuranceAmount", Long.class);

    public final A109.TikTagTalk.domain.user.entity.QMember member;

    public final NumberPath<Long> ottAmount = createNumber("ottAmount", Long.class);

    public final NumberPath<Long> petAmount = createNumber("petAmount", Long.class);

    public final NumberPath<Long> rideAmount = createNumber("rideAmount", Long.class);

    public final NumberPath<Long> shoppingAmount = createNumber("shoppingAmount", Long.class);

    public final NumberPath<Long> snackAmount = createNumber("snackAmount", Long.class);

    public final NumberPath<Long> totalAmount = createNumber("totalAmount", Long.class);

    public final NumberPath<Long> travelAmount = createNumber("travelAmount", Long.class);

    public final StringPath yearAndMonth = createString("yearAndMonth");

    public QConsumePlan(String variable) {
        this(ConsumePlan.class, forVariable(variable), INITS);
    }

    public QConsumePlan(Path<? extends ConsumePlan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConsumePlan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConsumePlan(PathMetadata metadata, PathInits inits) {
        this(ConsumePlan.class, metadata, inits);
    }

    public QConsumePlan(Class<? extends ConsumePlan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new A109.TikTagTalk.domain.user.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

