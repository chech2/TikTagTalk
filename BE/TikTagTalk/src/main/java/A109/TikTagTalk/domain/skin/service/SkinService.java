package A109.TikTagTalk.domain.skin.service;

import A109.TikTagTalk.domain.skin.dto.request.BuyRequest;
import A109.TikTagTalk.domain.skin.dto.response.BuyResponse;
import A109.TikTagTalk.domain.skin.entity.Skin;

import java.time.LocalDateTime;
import java.util.List;

public interface SkinService {

    //모든 스킨 조회
    List<Skin> selectAllSkins();

    //상품 구매 - 원하는 상품 선택
    Long selectItemById(Long itemId);

    //상품 구매 - 구매
    BuyResponse buySkin(BuyRequest request);

    BuyResponse insertSkinItem(LocalDateTime now, String userId, Long skinId);

}
