package A109.TikTagTalk.domain.wallet.repository;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.wallet.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
    //선택 기간에 따른 포인트 내역 조회
    @Query("SELECT p FROM PointHistory p WHERE p.pointTime >= :startDate AND p.pointTime <= :endDate AND p.member.id = :memberId")
    List<PointHistory> findByMemberIdAndPointTimeBetween(@Param("memberId") Long memberId, @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

    //잔여 포인트 계산
    @Query("SELECT SUM(p.point) FROM PointHistory p WHERE p.pointTime <= :theDate AND p.member.id = :memberId")
    Integer selectBalancePoint(@Param("theDate") Timestamp theDate, @Param("memberId") Long memberId);

    List<PointHistory> findAllByMember_MemberId(Long memberId);

    List<PointHistory> findByMember(Member member);

}
