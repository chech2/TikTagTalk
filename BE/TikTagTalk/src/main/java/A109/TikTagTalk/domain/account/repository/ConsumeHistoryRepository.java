package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.entity.ConsumeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumeHistoryRepository extends JpaRepository<ConsumeHistory,Long>, ConsumeHistoryRepositoryCustom {

}
