package A109.TikTagTalk.domain.wallet.service;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.wallet.dto.response.PointListResponse;
import A109.TikTagTalk.domain.wallet.entity.PointHistory;
import A109.TikTagTalk.domain.wallet.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointHistoryServiceImpl implements PointHistoryService{
    private final PointHistoryRepository pointHistoryRepository;
    //private final MemberRepository memberRepository;

    //point 첫 화면
    @Override
    public List<PointListResponse> selectPointAll(String userId) {
        //Member member = new Member(); //테스트 시
        //Member member = memberRepository.findByUserId(userId).get();
        List<PointListResponse> list = new ArrayList<>();

        //List<PointHistory> pointHistoryList = pointHistoryRepository.findAllByMember_MemberId(member.getMemberId());

//        for(int idx = 0; idx < pointHistoryList.size(); idx++){
//            PointListResponse tmp = new PointListResponse(PointHistoryList.get(idx));
//            list.add(tmp);
//        }
        return list;
    }

    //사용자 이메일로 포인트 리스트 찾기
    @Override
    public List<PointListResponse> selectPointListByUserIdAndDate(String userId, String startDate, String endDate) {
        //userId 이용해서
        return null;

    }

    @Override
    public Integer calculateBalancePoint(String userId, Timestamp end) {
        return null;
    }
}
