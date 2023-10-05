package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.dto.request.ConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.response.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckMemberTagResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseUtil;
import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.entity.ConsumeHistory;
import A109.TikTagTalk.domain.account.entity.QConsumeHistory;
import A109.TikTagTalk.domain.tag.entity.QTag;
import A109.TikTagTalk.domain.tag.entity.Tag;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsumeHistoryRepositoryImpl implements ConsumeHistoryRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private QConsumeHistory consumeHistory=new QConsumeHistory("consumeHistory");
    private QTag qTag=new QTag("qtag");

    @Override
    public CheckMemberTagResponseDto calMemberTag(Account account, Tag tag, LocalDate consumeTime){
        LocalDate startTime;
        LocalDate endTime;

        startTime = LocalDate.of(consumeTime.getYear(), consumeTime.getMonthValue(), 1);
        endTime = LocalDate.of(consumeTime.getYear(), consumeTime.getMonthValue(), consumeTime.lengthOfMonth());

        Tuple tuple= queryFactory
                .select(consumeHistory.amount.sum(),consumeHistory.amount.count())
                .from(consumeHistory)
                .where(consumeHistory.account.id.eq(account.getId()),consumeHistory.tag.id.eq(tag.getId()),consumeHistory.consumeTime.between(startTime.atStartOfDay(),endTime.atTime(LocalTime.MAX)))
                .groupBy(consumeHistory.tag)
                .fetchOne();

        return CheckMemberTagResponseDto.builder()
                .amount(tuple.get(0,Long.class))
                .count(tuple.get(1,Long.class))
                .tag(CheckMemberTagResponseDto.TagDto.builder().id(tag.getId()).build())
                .build();
    }

    @Override
    public List<ConsumeHistory> copyAllConsumeHistory(Account account) {
        return queryFactory.selectFrom(consumeHistory)
                .where(consumeHistory.account.eq(account))
                .fetch();
    }

    @Override
    public List<ConsumeHistory> findAllRecently(ConsumeHistoryRequestDto requestDto,Account account) {
        LocalDate today;
        LocalDate startTime;
        LocalDate endTime;

        if(requestDto.getYearAndMonth()==null) {
            today = LocalDate.now();
        }else{
            today = LocalDate.parse(requestDto.getYearAndMonth()+"-01");
        }
        startTime = LocalDate.of(today.getYear(), today.getMonthValue(), 1);
        endTime = LocalDate.of(today.getYear(), today.getMonthValue(), today.lengthOfMonth());
        List<ConsumeHistory> list=queryFactory
                .selectFrom(consumeHistory)
                .where(consumeHistory.account.eq(account),consumeHistory.consumeTime.between(startTime.atStartOfDay(),endTime.atTime(LocalTime.MAX)))
                .orderBy(consumeHistory.consumeTime.desc())
                .fetch();
        return list;
    }

    @Override
    public List<ConsumeHistory> findAllHighest(ConsumeHistoryRequestDto requestDto,Account account) {
        LocalDate today;
        LocalDate startTime;
        LocalDate endTime;
        if(requestDto.getYearAndMonth()==null) {
            today = LocalDate.now();
        }else{
            today = LocalDate.parse(requestDto.getYearAndMonth()+"-01");
        }
        startTime = LocalDate.of(today.getYear(), today.getMonthValue(), 1);
        endTime = LocalDate.of(today.getYear(), today.getMonthValue(), today.lengthOfMonth());
        List<ConsumeHistory> list=queryFactory
                .selectFrom(consumeHistory)
                .where(consumeHistory.account.eq(account),consumeHistory.consumeTime.between(startTime.atStartOfDay(),endTime.atTime(LocalTime.MAX)))
                .orderBy(consumeHistory.amount.desc())
                .fetch();
        return list;
    }

    @Override
    public CheckAccountResponseDto checkAccountTagAmount(String yearAndMonth,Account account) {
        LocalDate today;
        LocalDate startTime;
        LocalDate endTime;
        if(yearAndMonth==null) {
            today = LocalDate.now();
        }else{
            today = LocalDate.parse(yearAndMonth+"-01");
        }
        startTime = LocalDate.of(today.getYear(), today.getMonthValue(), 1);
        endTime = LocalDate.of(today.getYear(), today.getMonthValue(), today.lengthOfMonth());
        Long totalAmount=queryFactory
                .select(consumeHistory.amount.sum())
                .from(consumeHistory)
                .where(consumeHistory.account.eq(account),consumeHistory.consumeTime.between(startTime.atStartOfDay(),endTime.atTime(LocalTime.MAX)))
                .fetchOne();
        List<CheckAccountResponseDto.TagDto> tagList=new ArrayList<>();
        List<Tuple> tuples=queryFactory
                .select(consumeHistory.amount.sum(),qTag.name)
                .from(consumeHistory)
                .join(qTag).on(consumeHistory.tag.id.eq(qTag.id))
                .where(consumeHistory.account.eq(account),consumeHistory.consumeTime.between(startTime.atStartOfDay(),endTime.atTime(LocalTime.MAX)))
                .groupBy(consumeHistory.tag)
                .orderBy(consumeHistory.amount.sum().desc())
                .fetch();
        for(Tuple tuple:tuples){
            tagList.add(CheckAccountResponseDto.TagDto.builder()
                    .amount(tuple.get(0,Long.class))
                    .name(tuple.get(1,String.class))
                    .percent((Math.round(((double)tuple.get(0,Long.class)/(double)totalAmount)*1000))/10.0).build());
        }
        return new CheckAccountResponseDto(totalAmount,tagList);
    }

    @Override
    public List<CheckMemberTagResponseDto> calMemberTags(ConsumeHistoryRequestDto requestDto,Account account) {
        LocalDate today;
        LocalDate startTime;
        LocalDate endTime;
        if(requestDto.getYearAndMonth()==null) {
            today = LocalDate.now();
        }else{
            today = LocalDate.parse(requestDto.getYearAndMonth()+"-01");
        }
        startTime = LocalDate.of(today.getYear(), today.getMonthValue(), 1);
        endTime = LocalDate.of(today.getYear(), today.getMonthValue(), today.lengthOfMonth());
        List<CheckMemberTagResponseDto> list=new ArrayList<>();
        List<Tuple> tuples=queryFactory
                .select(consumeHistory.amount.sum(),consumeHistory.amount.count(),consumeHistory.tag)
                .from(consumeHistory)
                .where(consumeHistory.account.eq(account), consumeHistory.consumeTime.between(startTime.atStartOfDay(),endTime.atTime(LocalTime.MAX)))
                .groupBy(consumeHistory.tag)
                .fetch();
        for(Tuple tuple:tuples){
            list.add(CheckMemberTagResponseDto.builder()
                    .amount(tuple.get(0,Long.class))
                    .count(tuple.get(1,Long.class))
                    .tag(CheckMemberTagResponseDto.TagDto.builder().id(tuple.get(2, Tag.class).getId()).build())
                    .build());
        }
        return list;
    }

    @Override
    public ResponseDto modifyConsumeHistory(ConsumeHistory consumeHistoryRequest) {
        queryFactory.update(consumeHistory)
                .set(consumeHistory.amount,consumeHistoryRequest.getAmount())
                .set(consumeHistory.consumeTime,consumeHistoryRequest.getConsumeTime())
                .set(consumeHistory.tag,consumeHistoryRequest.getTag())
                .set(consumeHistory.storeName,consumeHistoryRequest.getStoreName())
                .where(consumeHistory.id.eq(consumeHistoryRequest.getId()))
                .execute();
        return ResponseUtil.Success("수동 내역 수정 성공");
    }

}
