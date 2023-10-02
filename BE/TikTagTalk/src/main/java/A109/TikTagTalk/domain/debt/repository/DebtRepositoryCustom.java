package A109.TikTagTalk.domain.debt.repository;

import A109.TikTagTalk.domain.debt.entity.Debt;
import A109.TikTagTalk.domain.tagRoom.entity.Comment;
import A109.TikTagTalk.domain.user.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DebtRepositoryCustom {



}
