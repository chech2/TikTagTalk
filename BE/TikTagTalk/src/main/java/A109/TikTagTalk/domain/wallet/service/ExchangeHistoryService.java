package A109.TikTagTalk.domain.wallet.service;

import A109.TikTagTalk.domain.wallet.dto.request.ExchangeRequest;
import A109.TikTagTalk.domain.wallet.dto.response.ExchangeResponse;

import java.time.LocalDateTime;

public interface ExchangeHistoryService {
    public void test(ExchangeRequest request);

    ExchangeResponse exchangeCoin(LocalDateTime now, Long memberId, int coin, int point, Long exchangeRate);

    public void exchangeAlgo(Long memberId, int amount);

}
