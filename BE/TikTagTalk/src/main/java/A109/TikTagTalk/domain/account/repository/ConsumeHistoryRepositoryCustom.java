package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.dto.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.entity.ConsumeHistory;
import A109.TikTagTalk.domain.tag.dto.TagDto;

import java.util.List;

public interface ConsumeHistoryRepositoryCustom {
    List<ConsumeHistory> findAllRecently(Long accountId);
    List<ConsumeHistory> findAllHighest(Long accountId);

    Long checkAccountTotalAccount(Long accountId);
    List<TagDto> checkAccountTagAndAmount(Long accountId);

}
