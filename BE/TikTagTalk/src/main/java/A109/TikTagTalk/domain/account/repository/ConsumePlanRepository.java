package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.entity.ConsumePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumePlanRepository extends JpaRepository<ConsumePlan,Long>,ConsumePlanRepositoryCustom {
}
