package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.request.AddConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ModifyConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.response.*;
import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.entity.ConsumeHistory;
import A109.TikTagTalk.domain.account.repository.AccountRepository;
import A109.TikTagTalk.domain.account.repository.ConsumeHistoryRepository;
import A109.TikTagTalk.domain.tag.entity.MemberTag;
import A109.TikTagTalk.domain.tag.entity.Tag;
import A109.TikTagTalk.domain.tag.repository.MemberTagRepository;
import A109.TikTagTalk.domain.tag.repository.StoreRepository;
import A109.TikTagTalk.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsumeHistoryServcieImpl implements ConsumeHistoryService {
    private final ConsumeHistoryRepository consumeHistoryRepository;
    private final AccountRepository accountRepository;
    private final TagRepository tagRepository;
    private final StoreRepository storeRepository;
    private final MemberTagRepository memberTagRepository;
    @Override
    @Transactional
    public ResponseDto addConsumeHistory(AddConsumeHistoryRequestDto requestDto) {
        Account account=accountRepository.findById(requestDto.getAccountId()).get();
        Tag tag=tagRepository.findById(requestDto.getTag().getId()).get();

        ConsumeHistory consumeHistory=ConsumeHistory.builder()
                .account(account)
                .amount(requestDto.getAmount())
                .detail(requestDto.getDetail())
                .consumeTime(requestDto.getConsumeTime())
                .tag(tag)
                .isManual(false)
                .storeName(requestDto.getStoreName())
                .build();
        consumeHistoryRepository.save(consumeHistory);

        if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),requestDto.getConsumeTime().toLocalDate())){
            //끝 이미 있으니까.
            System.out.println("이미 잇다.");
        }
        else{
            //해당 태그가 기준이 넘으면 -> 멤버태그 추가
            Long sum=consumeHistoryRepository.tagSum(tag);
            System.out.println(sum);
            //해당 태그가 기준이 안넘으면 -> 멤버태그 추가X
        }

//        LocalDateTime getConsumeTime=requestDto.getConsumeTime();
//        String gotTime="";
//        if(getConsumeTime.getMonthValue()<10){
//            gotTime+=getConsumeTime.getYear()+"-0"+getConsumeTime.getMonthValue();
//        }else{
//            gotTime+=getConsumeTime.getYear()+"-"+getConsumeTime.getMonthValue();
//        }
//
//        ConsumeHistoryRequestDto consumeHistoryRequestDto=ConsumeHistoryRequestDto.builder()
//                .accountId(requestDto.getAccountId())
//                .yearAndMonth(gotTime)
//                .build();
//        int flag=makeMemberTags(consumeHistoryRequestDto);
//        if(flag==0){
//            System.out.println("추가 X");
//        }else{
//            System.out.println("추가");
//        }
        return ResponseUtil.Success("소비 내역 추가 성공");
    }
    public boolean isExceed(){
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AllConsumeHistoryResponseDto> allConsumeHistoryRecently(ConsumeHistoryRequestDto requestDto) {
        List<ConsumeHistory> allConsumeHistoryList = consumeHistoryRepository.findAllRecently(requestDto);
        return allConsumeHistoryList.stream()
                .map(nowHistory -> {
                    AllConsumeHistoryResponseDto.StoreDto storeDto = (nowHistory.getStore() == null) ?
                            null :
                            AllConsumeHistoryResponseDto.StoreDto.builder()
                                    .name(nowHistory.getStore().getName())
                                    .build();

                    AllConsumeHistoryResponseDto.TagDto tagDto = AllConsumeHistoryResponseDto.TagDto.builder()
                            .name(nowHistory.getTag().getName())
                            .build();

                    return AllConsumeHistoryResponseDto.builder()
                            .amount(nowHistory.getAmount())
                            .consumeTime(nowHistory.getConsumeTime())
                            .store(storeDto)
                            .storeName((storeDto == null) ? nowHistory.getStoreName() : null)
                            .tag(tagDto)
                            .detail(nowHistory.getDetail())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AllConsumeHistoryResponseDto> allConsumeHistoryHighest(ConsumeHistoryRequestDto requestDto) {
        List<ConsumeHistory> allConsumeHistoryList = consumeHistoryRepository.findAllHighest(requestDto);
        return allConsumeHistoryList.stream()
                .map(nowHistory -> {
                    AllConsumeHistoryResponseDto.StoreDto storeDto = (nowHistory.getStore() == null) ?
                            null :
                            AllConsumeHistoryResponseDto.StoreDto.builder()
                                    .name(nowHistory.getStore().getName())
                                    .build();

                    AllConsumeHistoryResponseDto.TagDto tagDto = AllConsumeHistoryResponseDto.TagDto.builder()
                            .name(nowHistory.getTag().getName())
                            .build();

                    return AllConsumeHistoryResponseDto.builder()
                            .amount(nowHistory.getAmount())
                            .consumeTime(nowHistory.getConsumeTime())
                            .store(storeDto)
                            .storeName((storeDto == null) ? nowHistory.getStoreName() : null)
                            .tag(tagDto)
                            .detail(nowHistory.getDetail())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CheckAccountResponseDto checkAccountTotalAccount(ConsumeHistoryRequestDto requestDto) {
        CheckAccountResponseDto result=consumeHistoryRepository.checkAccountTagAmount(requestDto);
        return result;
    }

    @Override
    @Transactional //더미 데이터 용 멤버태그 획득
    public int makeMemberTags(ConsumeHistoryRequestDto requestDto) {
        List<CheckMemberTagResponseDto> list = consumeHistoryRepository.makeMemberTags(requestDto);
        //Account는 id가 1인 애를 그냥 넣자
        Account account=accountRepository.findById(requestDto.getAccountId()).get();

        for (int i = 0; i < list.size(); i++) {
            Tag tag=tagRepository.findById(list.get(i).getTag().getId()).get();
            LocalDate gotTime;
            if(requestDto.getYearAndMonth()==null){
                gotTime=LocalDate.now();
            }else{
                gotTime=LocalDate.parse(requestDto.getYearAndMonth()+"-01");
            }
            if(!(memberTagRepository.checkMemberTagExist(requestDto.getAccountId(),tag.getId(),gotTime))) {
                if (list.get(i).getTag().getId() == 1) { //식비, 조건 : 35만원,30회
                    if (list.get(i).getAmount() >= 350000 || list.get(i).getCount() >= 30) {
                        return saveMemberTag(account,tag,gotTime);
                    }
                } else if (list.get(i).getTag().getId() == 2) { //편/마/잡, 조건 : 25만원,20회
                    if (list.get(i).getAmount() >= 250000 || list.get(i).getCount() >= 20) {
                        return saveMemberTag(account,tag,gotTime);
                    }
                } else if (list.get(i).getTag().getId() == 3) { //교통, 조건 : 15만원,10회
                    if (list.get(i).getAmount() >= 150000 || list.get(i).getCount() >= 10) {
                        return saveMemberTag(account,tag,gotTime);
                    }
                } else if (list.get(i).getTag().getId() == 4) { //쇼핑, 조건 : 10만원,5회
                    if (list.get(i).getAmount() >= 100000 || list.get(i).getCount() >= 5) {
                        return saveMemberTag(account,tag,gotTime);
                    }
                } else if (list.get(i).getTag().getId() == 5) { //카페, 조건 : 35만원,30회
                    if (list.get(i).getAmount() >= 350000 || list.get(i).getCount() >= 30) {
                        return saveMemberTag(account,tag,gotTime);
                    }
                } else if (list.get(i).getTag().getId() == 6) { //보험, 조건 : 5만원
                    if (list.get(i).getAmount() >= 50000) {
                        return saveMemberTag(account,tag,gotTime);
                    }
                } else if (list.get(i).getTag().getId() == 7) { //취미, 조건 : 10만원,5회
                    if (list.get(i).getAmount() >= 100000 || list.get(i).getCount() >= 5) {
                        return saveMemberTag(account,tag,gotTime);
                    }
                } else if (list.get(i).getTag().getId() == 8) { //미용, 조건 : 5만원,5회
                    if (list.get(i).getAmount() >= 50000 || list.get(i).getCount() >= 5) {
                        return saveMemberTag(account,tag,gotTime);
                    }
                } else if (list.get(i).getTag().getId() == 9) { //의료, 조건 : 15만원,5회
                    if (list.get(i).getAmount() >= 150000 || list.get(i).getCount() >= 5) {
                        return saveMemberTag(account,tag,gotTime);
                    }
                } else if (list.get(i).getTag().getId() == 10) { //정기결제, 조건 :1회
                    if (list.get(i).getCount() >= 1) {
                        return saveMemberTag(account,tag,gotTime);
                    }
                } else if (list.get(i).getTag().getId() == 11) { //반려동물, 조건 : 1회
                    if (list.get(i).getCount() >= 1) {
                        return saveMemberTag(account,tag,gotTime);
                    }
                } else if (list.get(i).getTag().getId() == 12) { //여행, 조건 : 1회
                    if (list.get(i).getCount() >= 1) {
                        return saveMemberTag(account,tag,gotTime);
                    }
                }
            }
        }
        return 0;
    }
    public int saveMemberTag(Account account,Tag tag, LocalDate gotTime){
        MemberTag memberTag = MemberTag.builder()
                .tag(tag)
                .account(account)
                .gotTime(gotTime)
                .build();
        memberTagRepository.save(memberTag);
        return 1;
    }
    @Override
    @Transactional
    public ResponseDto deleteConsumeHistory(Long consumeHistoryId) {
        ConsumeHistory consumeHistory=consumeHistoryRepository.findById(consumeHistoryId).get();
        if(!(consumeHistory.getIsManual())){
            consumeHistoryRepository.delete(consumeHistory);
            return ResponseUtil.Success("해당 소비 내역 삭제 성공");
        }
        return ResponseUtil.Failure("해당 소비 내역 삭제 실패");
    }

    @Override
    @Transactional
    public ResponseDto modifyConsumeHistory(ModifyConsumeHistoryRequestDto requestDto,Long consumeHistoryId) {
        Tag tag=Tag.builder().id(requestDto.getTag().getId()).build();
        ConsumeHistory consumeHistory=ConsumeHistory.builder()
                .id(consumeHistoryId)
                .storeName(requestDto.getStoreName())
                .amount(requestDto.getAmount())
                .consumeTime(requestDto.getConsumeTime())
                .tag(tag)
                .build();
        return consumeHistoryRepository.modifyConsumeHistory(consumeHistory);
    }
}
