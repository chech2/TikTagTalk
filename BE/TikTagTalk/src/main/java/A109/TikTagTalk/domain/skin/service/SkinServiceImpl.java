package A109.TikTagTalk.domain.skin.service;

import A109.TikTagTalk.domain.skin.dto.request.BuyRequest;
import A109.TikTagTalk.domain.skin.dto.response.AllSkinResponse;
import A109.TikTagTalk.domain.skin.dto.response.BuyResponse;
import A109.TikTagTalk.domain.skin.entity.Skin;
import A109.TikTagTalk.domain.skin.repository.SkinRepository;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.wallet.entity.CoinHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkinServiceImpl implements SkinService{
    private final SkinRepository skinRepository;
    //private final MemberRepository memberRepository;

    @Override
    public List<AllSkinResponse> selectAllSkins() {
//        return skinRepository.findAll().stream()
//                .map(AllSkinResponse::new)
//                .collect(Collectors.toList());
    }

    @Override
    public BuyResponse buySkin(BuyRequest request) {
        return null;
    }

    @Override
    public BuyResponse insertSkinItem(LocalDateTime now, String userId, Long skinId) {

//        Skin skin = skinRepository.findById(skinId).get();
//
//        Member member = member;
//
//        Timestamp Tnow = Timestamp.valueOf(now);
//
//        CoinHistory coinHistory = new CoinHistory();
//        coinHistory.setCoinTime(Tnow.toLocalDateTime());
//        coinHistory.setCoin(0 - skin.getPrice());
//        coinHistory.setMember(member);
//        coinHistory.setId();

        return null;
    }
}
