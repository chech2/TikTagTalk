package A109.TikTagTalk.domain.debt.repository;

import A109.TikTagTalk.domain.debt.entity.RepaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepaymentHistoryRepository extends JpaRepository<RepaymentHistory, Long> {

}
