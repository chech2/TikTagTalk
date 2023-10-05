package A109.TikTagTalk.domain.wallet.repository;

import A109.TikTagTalk.domain.wallet.entity.CoinHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CoinHistoryRepository extends JpaRepository<CoinHistory, Long> {
    //memberId는 pk 값, userId unique값
    //선택 기간에 따른 코인 내역 조회
    @Query("SELECT c FROM CoinHistory c WHERE c.coinTime >= :startDate AND c.coinTime <= :endDate AND c.member.id = :memberId")
    List<CoinHistory> findByMemberIdAndCoinTimeBetween(@Param("memberId") Long memberId, @Param("startDate")Timestamp startDate, @Param("endDate") Timestamp endDate);

    //잔여 코인 계산
    @Query("select SUM(c.coin) FROM CoinHistory c WHERE c.coinTime <= :theDate AND c.member.id = :memberId")
    Integer selectBalanceCoin(@Param("theDate") Timestamp theDate, @Param("memberId") Long memberId);

    @Query("select c from CoinHistory c where c.member.id = : memberId")
    List<CoinHistory> findAllByMember_Memberid(Long memberId);

}
