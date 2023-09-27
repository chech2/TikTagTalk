package A109.TikTagTalk.domain.skin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSkin is a Querydsl query type for Skin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSkin extends EntityPathBase<Skin> {

    private static final long serialVersionUID = -2013425704L;

    public static final QSkin skin = new QSkin("skin");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public QSkin(String variable) {
        super(Skin.class, forVariable(variable));
    }

    public QSkin(Path<? extends Skin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSkin(PathMetadata metadata) {
        super(Skin.class, metadata);
    }

}

