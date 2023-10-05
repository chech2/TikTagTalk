package A109.TikTagTalk.domain.tagRoom.repository;

import A109.TikTagTalk.domain.tagRoom.entity.Item;
import A109.TikTagTalk.domain.tagRoom.entity.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRespositoryImpl implements ItemRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private QItem item=new QItem("item");
    @Override
    public Item findItemByTagIdAndIsSkinAndIsShit(Long tagId,boolean isSkin,boolean isShit) {
        return queryFactory
                .selectFrom(item)
                .where(item.isSkin.eq(isSkin).and(item.tag.id.eq(tagId)).and(item.isShit.eq(isShit)))
                .fetchOne();
    }
}
