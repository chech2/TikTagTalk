package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.entity.ConsumeHistory;
import A109.TikTagTalk.domain.account.entity.QConsumeHistory;
import A109.TikTagTalk.domain.tag.dto.TagDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.sum;

@Repository
@RequiredArgsConstructor
public class ConsumeHistoryRepositoryImpl implements ConsumeHistoryRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private QConsumeHistory consumeHistory=new QConsumeHistory("consumeHistory");
    @Override
    public List<ConsumeHistory> findAllRecently(Long accountId) {
        List<ConsumeHistory> list=queryFactory
                .selectFrom(consumeHistory)
                .where(consumeHistory.account.id.eq(accountId))
                .orderBy(consumeHistory.consumeTime.desc())
                .fetch();
        return list;
    }

    @Override
    public List<ConsumeHistory> findAllHighest(Long accountId) {
        List<ConsumeHistory> list=queryFactory
                .selectFrom(consumeHistory)
                .where(consumeHistory.account.id.eq(accountId))
                .orderBy(consumeHistory.amount.desc())
                .fetch();
        return list;
    }

    @Override
    public Long checkAccountTotalAccount(Long accountId){
        Long totalAmount=queryFactory
                .select(consumeHistory.amount.sum())
                .from(consumeHistory)
                .where(consumeHistory.account.id.eq(accountId))
                .fetchOne();
        return totalAmount;
    }

    @Override
    public List<TagDto> checkAccountTagAndAmount(Long accountId){
        List<TagDto> tagList=new ArrayList<>();
        List<Tuple> tupleList=queryFactory
                .select(consumeHistory.amount.sum(),consumeHistory.tagId)
                .from(consumeHistory)
                .where(consumeHistory.account.id.eq(accountId))
                .groupBy(consumeHistory.tagId)
                .fetch();
        for(Tuple tuple:tupleList){
            Long amountSum=tuple.get(consumeHistory.amount);
            Long tagId=tuple.get(consumeHistory.tagId);
            tagList.add(new TagDto(tagId,amountSum));
        }
    }
}
