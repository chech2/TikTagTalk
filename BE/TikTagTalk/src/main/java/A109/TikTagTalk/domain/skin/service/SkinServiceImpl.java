package A109.TikTagTalk.domain.skin.service;

import A109.TikTagTalk.domain.skin.dto.request.BuyRequest;
import A109.TikTagTalk.domain.skin.dto.response.BuyResponse;
import A109.TikTagTalk.domain.skin.entity.MemberSkin;
import A109.TikTagTalk.domain.skin.entity.Skin;
import A109.TikTagTalk.domain.skin.repository.MemberSkinRepository;
import A109.TikTagTalk.domain.skin.repository.SkinRepository;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import A109.TikTagTalk.domain.wallet.entity.CoinHistory;
import A109.TikTagTalk.domain.wallet.repository.CoinHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkinServiceImpl implements SkinService{
    private final SkinRepository skinRepository;
    private final MemberSkinRepository memberSkinRepository;
    private final MemberRepository memberRepository;
    private final CoinHistoryRepository coinHistoryRepository;

    @Override
    public List<Skin> selectAllSkins() {
        return skinRepository.findAll();
    }

        //상품 구매 - 원하는 상품 선택
    @Override
    public Long selectItemById(Long itemId) {
        //상품 id로 상품 찾아서 반환
        return skinRepository
                .findById(itemId)
                .orElseThrow()
                .getId();
    }


    //상품 구매 - 구매
    @Override
    public BuyResponse buySkin(BuyRequest request) {
        BuyResponse buyResponse = new BuyResponse(request.getSkin());
        return buyResponse;
    }





    //구매한 스킨을 DB에 저장
    @Override
    public BuyResponse insertSkinItem(LocalDateTime now, String userId, Long skinId) {

        Skin skin = skinRepository.findById(skinId).get();

        Member member = memberRepository.findByUserId(userId).get();

        Timestamp Tnow = Timestamp.valueOf(now);

        //겁나 헷갈림
        BuyRequest buyRequest = new BuyRequest();
        buyRequest.setBuytime(Tnow);
        buyRequest.setSkin(skin);
        buyRequest.setMember(member);

        CoinHistory coinHistory2 = buyRequest.toEntity();

        //c.save(coinHistory2); //어디에다가 save 해야하나


        CoinHistory coinHistory = new CoinHistory();
        coinHistory.setCoinTime(Tnow.toLocalDateTime());
        coinHistory.setCoin(0 - skin.getPrice());
        coinHistory.setMember(member);
        coinHistory.setContent(skin.getName());
        List<CoinHistory> coinHistoryList = coinHistoryRepository.findAllByMember_Memberid(member.getId());
        Integer coins = coinHistoryList.get(coinHistoryList.size() - 1).getBalanceCoin();

        coinHistory.setBalanceCoin(coins);

        coinHistoryRepository.save(coinHistory);

        BuyResponse buyResponse = new BuyResponse(skin);
        buyResponse.setSkin(skin);

        return buyResponse;
    }
}
