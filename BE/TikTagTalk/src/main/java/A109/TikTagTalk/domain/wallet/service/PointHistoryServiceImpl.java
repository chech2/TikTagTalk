package A109.TikTagTalk.domain.wallet.service;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import A109.TikTagTalk.domain.wallet.dto.response.PointListResponse;
import A109.TikTagTalk.domain.wallet.entity.PointHistory;
import A109.TikTagTalk.domain.wallet.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointHistoryServiceImpl implements PointHistoryService{
    private final PointHistoryRepository pointHistoryRepository;
    private final MemberRepository memberRepository;

    //point 첫 화면
    @Override
    public List<PointListResponse> selectPointAll(String userId) {
        //Member member = new Member(); //테스트 시
        Member member = memberRepository.findByUserId(userId).get();
        List<PointListResponse> list = new ArrayList<>();

        List<PointHistory> pointHistoryList = pointHistoryRepository.findAllByMember_MemberId(member.getId());

        for(int idx = 0; idx < pointHistoryList.size(); idx++){
            PointListResponse tmp = new PointListResponse(pointHistoryList.get(idx));
            list.add(tmp);
        }
        return list;
    }

    //사용자 id로 포인트 리스트 찾기
    @Override
    public List<PointListResponse> selectPointListByUserIdAndDate(String userId, String start, String end) {
        //userId 이용해서 user id 값(pk, autoincrement) 구하기
        Member member = memberRepository.findByUserId(userId).get();

        start += " 00:00:00.0";
        end += " 23:59:59.9";

        Timestamp startDate = Timestamp.valueOf(start);
        Timestamp endDate = Timestamp.valueOf(end);

        return pointHistoryRepository.findByMemberIdAndPointTimeBetween(member.getId(), startDate, endDate)
                .stream()
                .map(PointListResponse::new)
                .collect(Collectors.toList());

    }

    @Override
    public Integer calculateBalancePoint(String userId, Timestamp end) throws NullPointerException{
        //현재 날짜 추출해서 pointHistory Entity에 넣어주기(데이터 update)

        //현재 날짜 설정
        LocalDateTime now = LocalDateTime.now();

        //UserId로 user id(pk, autoincrement) 찾기
        Member member = memberRepository.findByUserId(userId).get();

        Integer balancePoint = pointHistoryRepository.selectBalancePoint(now, member.getId());
        System.out.println(balancePoint);
        List<PointHistory> list = pointHistoryRepository.findAllByMember_MemberId(member.getId());

        if(list.size() == 0){
            return null;
        }

        PointHistory pointHistory = list.get(list.size() - 1);

        pointHistory.setBalancePoint(balancePoint);

        return balancePoint;
    }
}
