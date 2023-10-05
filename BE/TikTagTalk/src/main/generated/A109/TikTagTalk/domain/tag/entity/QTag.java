package A109.TikTagTalk.domain.tag.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTag is a Querydsl query type for Tag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTag extends EntityPathBase<Tag> {

    private static final long serialVersionUID = 562041860L;

    public static final QTag tag = new QTag("tag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<A109.TikTagTalk.domain.tagRoom.entity.Item, A109.TikTagTalk.domain.tagRoom.entity.QItem> itemList = this.<A109.TikTagTalk.domain.tagRoom.entity.Item, A109.TikTagTalk.domain.tagRoom.entity.QItem>createList("itemList", A109.TikTagTalk.domain.tagRoom.entity.Item.class, A109.TikTagTalk.domain.tagRoom.entity.QItem.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QTag(String variable) {
        super(Tag.class, forVariable(variable));
    }

    public QTag(Path<? extends Tag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTag(PathMetadata metadata) {
        super(Tag.class, metadata);
    }

}

