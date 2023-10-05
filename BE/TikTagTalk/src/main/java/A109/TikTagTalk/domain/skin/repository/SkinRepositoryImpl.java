package A109.TikTagTalk.domain.skin.repository;

import A109.TikTagTalk.domain.tagRoom.entity.Item;
import A109.TikTagTalk.domain.tagRoom.entity.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SkinRepositoryImpl implements SkinRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private QItem item=new QItem("item");

    @Override
    public Item findItemByTagIdAndIsSkinAndIsShit(Long tagId, boolean isSkin, boolean isShit) {
        return queryFactory
                .selectFrom(item)
                .where(item.isSkin.eq(isSkin).and(item.tag.id.eq(tagId)).and(item.isShit.eq(isShit)))
                .fetchOne();
    }


    //isSkin = true, isShit = false
}
