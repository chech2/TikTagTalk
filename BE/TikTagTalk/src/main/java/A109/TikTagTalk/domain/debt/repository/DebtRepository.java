package A109.TikTagTalk.domain.debt.repository;

import A109.TikTagTalk.domain.debt.entity.Debt;;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtRepository extends JpaRepository<Debt, Long>, DebtRepositoryCustom {
    Page<Debt> findAllByDebtorId(Long debtorId, Pageable pageable);

    Page<Debt> findAllByLenderId(Long lenderId, Pageable pageable);
}
