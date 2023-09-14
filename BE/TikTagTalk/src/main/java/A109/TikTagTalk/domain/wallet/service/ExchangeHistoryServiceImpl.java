package A109.TikTagTalk.domain.wallet.service;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.wallet.dto.request.ExchangeRequest;
import A109.TikTagTalk.domain.wallet.dto.response.ExchangeResponse;
import A109.TikTagTalk.domain.wallet.entity.CoinHistory;
import A109.TikTagTalk.domain.wallet.entity.ExchangeHistory;
import A109.TikTagTalk.domain.wallet.entity.PointHistory;
import A109.TikTagTalk.domain.wallet.repository.CoinHistoryRepository;
import A109.TikTagTalk.domain.wallet.repository.ExchangeHistoryRepository;
import A109.TikTagTalk.domain.wallet.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeHistoryServiceImpl implements ExchangeHistoryService{
    private final ExchangeHistoryRepository exchangeHistoryRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final CoinHistoryRepository coinHistoryRepository;
    private final MemberRepository memberRepository;

    @Override
    public void test(ExchangeRequest request) {
        List<ExchangeHistory> list = exchangeHistoryRepository.findAll();
        return list;
    }

    @Override
    public ExchangeResponse exchangeCoin(LocalDateTime now, Long memberId, int coin, int point, Long exchangeRate) throws NullPointerException{
        //여기서 coin은 환전하려는 코인 갯수

        PointHistory pointHistory1 = pointHistoryRepository.findById(memberId).get();

        Member member = memberRepository.findById(memberId).get();

        Timestamp Tnow = Timestamp.valueOf(now);

        ExchangeRequest exchangeRequest = new ExchangeRequest();
        exchangeRequest.setExchangeTime(Tnow.toLocalDateTime());
        exchangeRequest.setCoin(coin);
        exchangeRequest.setPoint(point);
        exchangeRequest.setExchangeRate(exchangeRate);

        ExchangeHistory exchangeHistory = exchangeRequest.toEntity();

        exchangeHistoryRepository.save(exchangeHistory);

        CoinHistory coinHistory = new CoinHistory();
        coinHistory.setCoinTime(Tnow.toLocalDateTime());
        coinHistory.setCoin(0 + coin);
        coinHistory.setMember(member);

        List<CoinHistory> coinHistoryList = coinHistoryRepository.findAllByMember_Memberid(member.getId());
        Integer coins = coinHistoryList.get(coinHistoryList.size() - 1).getBalanceCoin();

        coinHistory.setBalanceCoin(coins);

        coinHistoryRepository.save(coinHistory);

        PointHistory pointHistory = new PointHistory();
        pointHistory = new PointHistory();
        pointHistory.setPointTime(Tnow.toLocalDateTime());
        pointHistory.setPoint(0 - point);
        pointHistory.setMember(member);

        List<PointHistory> pointHistoryList = pointHistoryRepository.findAllByMember_MemberId(member.getId());
        Integer points = pointHistoryList.get(pointHistoryList.size()-1).getBalancePoint();

        pointHistory.setBalancePoint(points);

        pointHistoryRepository.save(pointHistory);


        ExchangeResponse exchangeResponse = new ExchangeResponse(pointHistory);
        ExchangeResponse exchangeResponse1 = new ExchangeResponse(coinHistory);
        ExchangeResponse exchangeResponse2 = new ExchangeResponse(exchangeHistory);

        return null;
    }


    @Override
    public void exchangeAlgo(Long userId, int amount) {

    }
}
