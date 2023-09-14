package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.dto.response.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckMemberTagResponseDto;
import A109.TikTagTalk.domain.account.entity.ConsumeHistory;

import java.util.List;

public interface ConsumeHistoryRepositoryCustom {
    List<ConsumeHistory> findAllRecently(Long accountId);
    List<ConsumeHistory> findAllHighest(Long accountId);

    CheckAccountResponseDto checkAccountTagAmount(Long accountId);

    List<CheckMemberTagResponseDto> makeMemberTags(Long accountId);


}
