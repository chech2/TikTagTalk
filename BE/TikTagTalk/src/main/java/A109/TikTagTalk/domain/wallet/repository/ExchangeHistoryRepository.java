package A109.TikTagTalk.domain.wallet.repository;

import A109.TikTagTalk.domain.wallet.entity.ExchangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeHistoryRepository extends JpaRepository<ExchangeHistory, Long> {

}
