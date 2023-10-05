package A109.TikTagTalk.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTalkTalk is a Querydsl query type for TalkTalk
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTalkTalk extends EntityPathBase<TalkTalk> {

    private static final long serialVersionUID = -176728415L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTalkTalk talkTalk = new QTalkTalk("talkTalk");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember receiver;

    public final QMember sender;

    public final EnumPath<TalkTalkStatus> status = createEnum("status", TalkTalkStatus.class);

    public QTalkTalk(String variable) {
        this(TalkTalk.class, forVariable(variable), INITS);
    }

    public QTalkTalk(Path<? extends TalkTalk> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTalkTalk(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTalkTalk(PathMetadata metadata, PathInits inits) {
        this(TalkTalk.class, metadata, inits);
    }

    public QTalkTalk(Class<? extends TalkTalk> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.receiver = inits.isInitialized("receiver") ? new QMember(forProperty("receiver"), inits.get("receiver")) : null;
        this.sender = inits.isInitialized("sender") ? new QMember(forProperty("sender"), inits.get("sender")) : null;
    }

}

