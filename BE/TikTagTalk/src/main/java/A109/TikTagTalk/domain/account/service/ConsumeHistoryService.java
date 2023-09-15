package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.request.AddConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ModifyConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.response.AllConsumeHistoryResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckMemberTagResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;

import java.util.List;

public interface ConsumeHistoryService {
    List<AllConsumeHistoryResponseDto> allConsumeHistoryRecently(Long accountId);
    List<AllConsumeHistoryResponseDto> allConsumeHistoryHighest(Long accountId);

    CheckAccountResponseDto checkAccountTotalAccount(Long accountId);
    ResponseDto addConsumeHistory(AddConsumeHistoryRequestDto reqestDto);

    void makeMemberTags(Long accountId);

    ResponseDto deleteConsumeHistory(Long consumeHistoryId);

    ResponseDto modifyConsumeHistory(ModifyConsumeHistoryRequestDto requestDto);
}
