package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.request.AddConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ModifyConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.response.AllConsumeHistoryResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.tag.entity.Tag;
import A109.TikTagTalk.domain.user.entity.Member;

import java.util.List;

public interface ConsumeHistoryService {
    List<AllConsumeHistoryResponseDto> allConsumeHistoryRecently(ConsumeHistoryRequestDto requestDto, Member member);
    List<AllConsumeHistoryResponseDto> allConsumeHistoryHighest(ConsumeHistoryRequestDto requestDto,Member member);

    CheckAccountResponseDto checkAccountTotalAccount(ConsumeHistoryRequestDto requestDto,Member member);
    ResponseDto addConsumeHistory(AddConsumeHistoryRequestDto reqestDto,Member member);

    ResponseDto makeMemberTags(ConsumeHistoryRequestDto requestDto,Member member);

    ResponseDto deleteConsumeHistory(Long consumeHistoryId,Member member);

    ResponseDto modifyConsumeHistory(ModifyConsumeHistoryRequestDto requestDto,Long consumeHistoryId,Member member);

}
