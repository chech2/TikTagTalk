package A109.TikTagTalk.domain.wallet.service;

import A109.TikTagTalk.domain.wallet.dto.response.CoinListResponse;

import java.sql.Timestamp;
import java.util.List;

public interface CoinHistoryService {

    List<CoinListResponse> selectCoinAll(String userId);

    List<CoinListResponse> selectCoinListByUserIdAndDate(String userId, String startDate, String endDate);

    Integer calculateBalanceCoin(String userId, Timestamp end);
}
