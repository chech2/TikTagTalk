package A109.TikTagTalk.domain.tagRoom.repository;

import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.tagRoom.dto.request.UpdateMemberItemRequestDto;
import A109.TikTagTalk.domain.tagRoom.entity.MemberItem;
import A109.TikTagTalk.domain.user.entity.Member;

import java.util.List;

public interface MemberItemRepositoryCustom {
    List<MemberItem> findMemberItem(Member member);
    MemberItem findByMemberItemName(Member member, String itemName);
    void updateMemberItem(MemberItem memberItem, UpdateMemberItemRequestDto.UpdateInfoDto requestDto);
}
