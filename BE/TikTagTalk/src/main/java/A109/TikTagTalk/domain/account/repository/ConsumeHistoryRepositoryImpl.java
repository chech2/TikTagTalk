package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.dto.AllConsumeHistoryResponseDto;
import A109.TikTagTalk.domain.account.dto.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.dto.CheckMemberTagResponseDto;
import A109.TikTagTalk.domain.account.entity.ConsumeHistory;
import A109.TikTagTalk.domain.account.entity.QConsumeHistory;
import A109.TikTagTalk.domain.tag.entity.QTag;
import A109.TikTagTalk.domain.tag.entity.Tag;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ConsumeHistoryRepositoryImpl implements ConsumeHistoryRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private QConsumeHistory consumeHistory=new QConsumeHistory("consumeHistory");
    private QTag qTag=new QTag("qtag");
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
    public CheckAccountResponseDto checkAccountTagAmount(Long accountId) {
        Long totalAmount=queryFactory
                .select(consumeHistory.amount.sum())
                .from(consumeHistory)
                .where(consumeHistory.account.id.eq(accountId))
                .fetchOne();
        List<CheckAccountResponseDto.TagDto> tagList=new ArrayList<>();
        List<Tuple> tuples=queryFactory
                .select(consumeHistory.amount.sum(),qTag.name)
                .from(consumeHistory)
                .join(qTag).on(consumeHistory.tag.id.eq(qTag.id))
                .where(consumeHistory.account.id.eq(accountId))
                .groupBy(consumeHistory.tag)
                .fetch();
        for(Tuple tuple:tuples){
            tagList.add(new CheckAccountResponseDto.TagDto(tuple.get(1, String.class),tuple.get(0,Long.class),(Math.round(((double)tuple.get(0,Long.class)/(double)totalAmount)*1000)/10.0) ));
        }
        return new CheckAccountResponseDto(totalAmount,tagList);
    }

    @Override
    public List<CheckMemberTagResponseDto> checkTags(Long accountId) {
        List<CheckMemberTagResponseDto> list=new ArrayList<>();
        List<Tuple> tuples=queryFactory
                .select(consumeHistory.amount.sum(),consumeHistory.amount.count(),consumeHistory.tag)
                .from(consumeHistory)
                .groupBy(consumeHistory.tag)
                .where(consumeHistory.account.id.eq(accountId))
                .fetch();
        for(Tuple tuple:tuples){
            list.add(new CheckMemberTagResponseDto(tuple.get(2, Tag.class),tuple.get(1, Long.class),tuple.get(0,Long.class)));
        }
        return list;
    }

}
