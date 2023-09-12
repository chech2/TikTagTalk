package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long>, AccountRepositoryCustom {
}
