package A109.TikTagTalk.domain.wallet.service;

import A109.TikTagTalk.domain.wallet.dto.request.ExchangeRequest;
import A109.TikTagTalk.domain.wallet.dto.response.ExchangeResponse;
import A109.TikTagTalk.domain.wallet.entity.ExchangeHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface ExchangeHistoryService {
    public List<ExchangeHistory> test(ExchangeRequest request);

    ExchangeResponse exchangeCoin(LocalDateTime now, Long memberId, int coin, int point, Long exchangeRate);

    public void exchangeAlgo(Long memberId, int amount);

}
