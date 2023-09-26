package A109.TikTagTalk.domain.wallet.service;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import A109.TikTagTalk.domain.wallet.dto.response.CoinListResponse;
import A109.TikTagTalk.domain.wallet.entity.CoinHistory;
import A109.TikTagTalk.domain.wallet.entity.PointHistory;
import A109.TikTagTalk.domain.wallet.repository.CoinHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinHistoryServiceImpl implements CoinHistoryService{
    private final CoinHistoryRepository coinHistoryRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<CoinListResponse> selectCoinAll(String userId) {
        Member member = memberRepository.findByUserId(userId).get();

        List<CoinListResponse> list = new ArrayList<>();

        List<CoinHistory> coinHistoryList = coinHistoryRepository.findAllByMember_Memberid(member.getId());

        for(int idx = 0; idx < coinHistoryList.size(); idx++){
            CoinListResponse tmp = new CoinListResponse(coinHistoryList.get(idx));
            list.add(tmp);
        }

        return list;
    }

    @Override
    public List<CoinListResponse> selectCoinListByUserIdAndDate(String userId, String start, String end) {
        Member member = memberRepository.findByUserId(userId).get();

        start += " 00:00:00.0";
        end += " 23:59:59.9";

        Timestamp startDate = Timestamp.valueOf(start);
        Timestamp endDate = Timestamp.valueOf(end);

        return coinHistoryRepository.findByMemberIdAndCoinTimeBetween(member.getId(), startDate, endDate)
                .stream()
                .map(CoinListResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Integer calculateBalanceCoin(String userId, Timestamp end) throws NullPointerException {

        LocalDateTime now = LocalDateTime.now();
        Timestamp Tnow = Timestamp.valueOf(now);

        Member member = memberRepository.findByUserId(userId).get();

        Integer balanceCoin = coinHistoryRepository.selectBalanceCoin(Tnow, member.getId());

        List<CoinHistory> list = coinHistoryRepository.findAllByMember_Memberid(member.getId());

        if(list.size() == 0) {
            return null;
        }

        CoinHistory coinHistory = list.get(list.size() - 1);

        coinHistory.setBalanceCoin(balanceCoin);

        return balanceCoin;
    }
}
