package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.AllConsumeHistoryResponseDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumeHistoryService {
    AllConsumeHistoryResponseDto allConsumeHistory();

    @Query("select h from ConsumeHistory  h where h.account=:accountId")
    AllConsumeHistoryResponseDto findOne(Long accountId);
}
