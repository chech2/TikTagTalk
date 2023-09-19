package A109.TikTagTalk.domain.tag.repository;

import A109.TikTagTalk.domain.account.entity.QConsumeHistory;
import A109.TikTagTalk.domain.tag.dto.request.CheckMemberTagRequestDto;
import A109.TikTagTalk.domain.tag.entity.MemberTag;
import A109.TikTagTalk.domain.tag.entity.QMemberTag;
import A109.TikTagTalk.domain.tag.entity.Tag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberTagRepositoryImpl implements MemberTagRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private QMemberTag memberTag=new QMemberTag("memberTag");
    @Override
    public Boolean checkMemberTagExist(Long accountId, Long tagId,LocalDate gotTime) {
        LocalDate startDate;
        LocalDate endDate;
        if(gotTime.getMonthValue()<10) {
            startDate = LocalDate.parse(gotTime.getYear() + "-0" + gotTime.getMonthValue() + "-01");
            endDate = LocalDate.parse(gotTime.getYear() + "-0" + gotTime.getMonthValue() + "-" + gotTime.lengthOfMonth());
        }else{
            startDate = LocalDate.parse(gotTime.getYear() + "-" + gotTime.getMonthValue() + "-01");
            endDate = LocalDate.parse(gotTime.getYear() + "-" + gotTime.getMonthValue() + "-" + gotTime.lengthOfMonth());
        }

        MemberTag membertag =queryFactory
                .selectFrom(memberTag)
                .where(memberTag.account.id.eq(accountId),memberTag.tag.id.eq(tagId),memberTag.gotTime.between(startDate,endDate))
                .fetchOne();
        if(membertag==null){
            return false;
        }
        return true;
    }


    @Override
    public List<MemberTag> checkMemberTagList(CheckMemberTagRequestDto requestDto) {
        LocalDate today;
        LocalDate startTime;
        LocalDate endTime;
        if(requestDto.getYearAndMonth()==null){
            today=LocalDate.now();
        }else{
            today=LocalDate.parse(requestDto.getYearAndMonth()+"-01");
        }
        startTime=LocalDate.of(today.getYear(),today.getMonthValue(),1);
        endTime=LocalDate.of(today.getYear(),today.getMonthValue(),today.lengthOfMonth());

        List<MemberTag> membertag=queryFactory
                .selectFrom(memberTag)
                .where(memberTag.account.id.eq(requestDto.getAccountId()),memberTag.gotTime.between(startTime,endTime))
                .fetch();
        return membertag;
    }

    @Override
    public MemberTag findByAccountTagGotTime(Long accountId, Long tagId, LocalDate gotTime) {
        return queryFactory.selectFrom(memberTag)
                .where(memberTag.account.id.eq(accountId),memberTag.tag.id.eq(tagId),memberTag.gotTime.eq(gotTime))
                .fetchOne();
    }
}
