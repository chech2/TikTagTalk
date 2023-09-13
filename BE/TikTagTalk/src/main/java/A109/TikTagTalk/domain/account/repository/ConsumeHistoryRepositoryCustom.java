package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.dto.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.dto.CheckMemberTagResponseDto;
import A109.TikTagTalk.domain.account.entity.ConsumeHistory;

import java.util.List;

public interface ConsumeHistoryRepositoryCustom {
    List<ConsumeHistory> findAllRecently(Long accountId);
    List<ConsumeHistory> findAllHighest(Long accountId);

    CheckAccountResponseDto checkAccountTagAmount(Long accountId);

    List<CheckMemberTagResponseDto> checkTags(Long accountId);
}
