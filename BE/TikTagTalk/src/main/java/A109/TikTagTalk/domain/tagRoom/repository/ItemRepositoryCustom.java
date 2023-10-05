package A109.TikTagTalk.domain.tagRoom.repository;

import A109.TikTagTalk.domain.tagRoom.entity.Item;

import java.util.List;

public interface ItemRepositoryCustom {
    Item findItemByTagIdAndIsSkinAndIsShit(Long tagId,boolean isSkin,boolean isShit);
}
