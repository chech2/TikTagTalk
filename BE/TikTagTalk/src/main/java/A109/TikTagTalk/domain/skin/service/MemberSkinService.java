package A109.TikTagTalk.domain.skin.service;

import A109.TikTagTalk.domain.skin.dto.request.BuyRequest;
import A109.TikTagTalk.domain.skin.dto.response.BuyResponse;
import A109.TikTagTalk.domain.skin.entity.MemberSkin;
import A109.TikTagTalk.domain.user.entity.Member;

import java.util.List;

public interface MemberSkinService {
    List<MemberSkin> memberSkinList(Member member);

//    //상품 구매 - 원하는 상품 선택
//    Long selectItemById(Long itemId);
//    //상품 구매 - 구매
//    BuyResponse buyItem(BuyRequest request);
//
//    BuyResponse insertBuyItem(String email, Long itemId);

}
