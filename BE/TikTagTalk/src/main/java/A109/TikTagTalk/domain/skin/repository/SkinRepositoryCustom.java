package A109.TikTagTalk.domain.skin.repository;

import A109.TikTagTalk.domain.tagRoom.entity.Item;

public interface SkinRepositoryCustom {
    Item findItemByTagIdAndIsSkinAndIsShit(Long tagId, boolean isSkin, boolean isShit);
}
