package A109.TikTagTalk.domain.wallet.service;

import A109.TikTagTalk.domain.wallet.dto.response.PointListResponse;

import java.sql.Timestamp;
import java.util.List;

public interface PointHistoryService {

    List<PointListResponse> selectPointAll(String userId);

    List<PointListResponse> selectPointListByUserIdAndDate(String userId, String startDate, String endDate);

    Integer calculateBalancePoint(String userId, Timestamp end);


}
