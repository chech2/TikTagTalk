//package A109.TikTagTalk.domain.debt.repository;
//
//import A109.TikTagTalk.domain.debt.entity.Debt;
//import A109.TikTagTalk.domain.tagRoom.entity.Comment;
//import A109.TikTagTalk.domain.tagRoom.entity.QComment;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
//
//@Repository
//@RequiredArgsConstructor
//public class DebtRepositoryImpl implements DebtRepositoryCustom {
//    private final JPAQueryFactory queryFactory;
//    private QComment comment=new QComment("comment");
//
//    @Override
//    public Page<Debt> findAllByDebtorId(Long debtorId, Pageable pageable) {}
//
//    @Override
//    public Page<Debt> findAllByLenderId(Long lenderId, Pageable pageable) {
//        return null;
//    }
//}
