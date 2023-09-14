package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.request.AddConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.response.AllConsumeHistoryResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckMemberTagResponseDto;

import java.util.List;

public interface ConsumeHistoryService {
    List<AllConsumeHistoryResponseDto> allConsumeHistoryRecently(Long accountId);
    List<AllConsumeHistoryResponseDto> allConsumeHistoryHighest(Long accountId);

    CheckAccountResponseDto checkAccountTotalAccount(Long accountId);
    int addConsumeHistory(AddConsumeHistoryRequestDto reqestDto);

    void makeMemberTags(Long accountId);
}
