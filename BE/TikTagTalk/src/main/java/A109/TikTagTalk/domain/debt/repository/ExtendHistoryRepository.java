package A109.TikTagTalk.domain.debt.repository;

import A109.TikTagTalk.domain.debt.entity.Debt;
import A109.TikTagTalk.domain.debt.entity.ExtendHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtendHistoryRepository extends JpaRepository<ExtendHistory, Long>{

}