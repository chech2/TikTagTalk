package A109.TikTagTalk.domain.tagRoom.repository;

import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.tagRoom.dto.request.UpdateMemberItemRequestDto;
import A109.TikTagTalk.domain.tagRoom.entity.MemberItem;

import java.util.List;

public interface MemberItemRepositoryCustom {
    List<MemberItem> findMemberItem(Account account);
    MemberItem findByAccountItemName(Account account, String itemName);
    void updateMemberItem(MemberItem memberItem, UpdateMemberItemRequestDto.UpdateInfoDto requestDto);
}
