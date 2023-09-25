package A109.TikTagTalk.domain.skin.service;

import A109.TikTagTalk.domain.skin.dto.request.BuyRequest;
import A109.TikTagTalk.domain.skin.dto.response.AllSkinResponse;
import A109.TikTagTalk.domain.skin.dto.response.BuyResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface SkinService {

    //모든 스킨 조회
    List<AllSkinResponse> selectAllSkins();

    BuyResponse buySkin(BuyRequest request);

    BuyResponse insertSkinItem(LocalDateTime now, String userId, Long skinId);

}
