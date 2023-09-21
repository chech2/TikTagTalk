package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.request.AddConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ModifyConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.response.AllConsumeHistoryResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.tag.entity.Tag;

import java.util.List;

public interface ConsumeHistoryService {
    List<AllConsumeHistoryResponseDto> allConsumeHistoryRecently(ConsumeHistoryRequestDto requestDto);
    List<AllConsumeHistoryResponseDto> allConsumeHistoryHighest(ConsumeHistoryRequestDto requestDto);

    CheckAccountResponseDto checkAccountTotalAccount(ConsumeHistoryRequestDto requestDto);
    ResponseDto addConsumeHistory(AddConsumeHistoryRequestDto reqestDto);

    ResponseDto makeMemberTags(ConsumeHistoryRequestDto requestDto);

    ResponseDto deleteConsumeHistory(Long consumeHistoryId);

    ResponseDto modifyConsumeHistory(ModifyConsumeHistoryRequestDto requestDto,Long consumeHistoryId);

}
