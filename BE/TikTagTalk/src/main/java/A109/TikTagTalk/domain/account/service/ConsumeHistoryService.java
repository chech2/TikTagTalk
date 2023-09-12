package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.AllConsumeHistoryResponseDto;
import A109.TikTagTalk.domain.account.dto.CheckAccountResponseDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumeHistoryService {
    AllConsumeHistoryResponseDto allConsumeHistoryRecently(Long accountId);
    AllConsumeHistoryResponseDto allConsumeHistoryHighest(Long accountId);

    CheckAccountResponseDto checkAccountTotalAccount(Long accountId);
}
