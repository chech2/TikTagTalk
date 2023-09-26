package A109.TikTagTalk.domain.tagRoom.service;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.InitMemberItemRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.UpdateMemberItemRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.response.InitMemberItemResponseDto;
import A109.TikTagTalk.domain.user.entity.Member;

import java.util.List;

public interface MemberItemService {
    void memberItemInit(InitMemberItemRequestDto requestDto, Member member);
    List<InitMemberItemResponseDto> findMemberItems(Long accountId);
    ResponseDto updateMemberItem(UpdateMemberItemRequestDto requestDto,Member member);
}
