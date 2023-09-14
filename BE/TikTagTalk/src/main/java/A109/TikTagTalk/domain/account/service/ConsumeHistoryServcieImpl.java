package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.request.AddConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.response.AllConsumeHistoryResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckMemberTagResponseDto;
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
    public int addConsumeHistory(AddConsumeHistoryRequestDto requestDto) {
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
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AllConsumeHistoryResponseDto> allConsumeHistoryRecently(Long accountId) {
        List<ConsumeHistory> allConsumeHistoryList = consumeHistoryRepository.findAllRecently(accountId);
        return allConsumeHistoryList.stream()
                .map(nowHistory -> AllConsumeHistoryResponseDto.builder()
                        .amount(nowHistory.getAmount())
                        .consumeTime(nowHistory.getConsumeTime())
                        .store(AllConsumeHistoryResponseDto.StoreDto.builder().name(nowHistory.getStore().getName()).build())
                        .tag(AllConsumeHistoryResponseDto.TagDto.builder().name(nowHistory.getTag().getName()).build())
                        .detail(nowHistory.getDetail())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AllConsumeHistoryResponseDto> allConsumeHistoryHighest(Long accountId) {
        List<ConsumeHistory> allConsumeHistoryList = consumeHistoryRepository.findAllHighest(accountId);
        return allConsumeHistoryList.stream()
                .map(nowHistory -> AllConsumeHistoryResponseDto.builder()
                        .amount(nowHistory.getAmount())
                        .consumeTime(nowHistory.getConsumeTime())
                        .store(AllConsumeHistoryResponseDto.StoreDto.builder().name(nowHistory.getStore().getName()).build())
                        .tag(AllConsumeHistoryResponseDto.TagDto.builder().name(nowHistory.getTag().getName()).build())
                        .detail(nowHistory.getDetail())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CheckAccountResponseDto checkAccountTotalAccount(Long accountId) {
        CheckAccountResponseDto result=consumeHistoryRepository.checkAccountTagAmount(accountId);
        return result;
    }

    @Override
    @Transactional
    public void makeMemberTags(Long accountId) {
        List<CheckMemberTagResponseDto> list = consumeHistoryRepository.makeMemberTags(accountId);
        //Account는 id가 1인 애를 그냥 넣자
        Account account=accountRepository.findById(accountId).get();

        for (int i = 0; i < list.size(); i++) {
            Tag tag=tagRepository.findById(list.get(i).getTag().getId()).get();

            if(list.get(i).getTag().getId()==1){ //식비, 조건 : 35만원,30회
                if(list.get(i).getAmount()>=350000 || list.get(i).getCount()>=30){
                    MemberTag memberTag=MemberTag.builder()
                            .tag(tag)
                            .account(account)
                            .build();
                    memberTagRepository.save(memberTag);
                }
            }else if(list.get(i).getTag().getId()==2){ //편/마/잡, 조건 : 25만원,20회
                if(list.get(i).getAmount()>=250000 || list.get(i).getCount()>=20){
                    MemberTag memberTag=MemberTag.builder()
                            .tag(tag)
                            .account(account)
                            .build();
                    memberTagRepository.save(memberTag);
                }
            }else if(list.get(i).getTag().getId()==3){ //교통, 조건 : 15만원,10회
                if(list.get(i).getAmount()>=150000 || list.get(i).getCount()>=10){
                    MemberTag memberTag=MemberTag.builder()
                            .tag(tag)
                            .account(account)
                            .build();
                    memberTagRepository.save(memberTag);
                }
            }else if(list.get(i).getTag().getId()==4){ //쇼핑, 조건 : 10만원,5회
                if(list.get(i).getAmount()>=100000 || list.get(i).getCount()>=5){
                    MemberTag memberTag=MemberTag.builder()
                            .tag(tag)
                            .account(account)
                            .build();
                    memberTagRepository.save(memberTag);
                }
            }else if(list.get(i).getTag().getId()==5){ //카페, 조건 : 35만원,30회
                if(list.get(i).getAmount()>=350000 || list.get(i).getCount()>=30){
                    MemberTag memberTag=MemberTag.builder()
                            .tag(tag)
                            .account(account)
                            .build();
                    memberTagRepository.save(memberTag);
                }
            }else if(list.get(i).getTag().getId()==6){ //보험, 조건 : 5만원
                if(list.get(i).getAmount()>=50000){
                    MemberTag memberTag=MemberTag.builder()
                            .tag(tag)
                            .account(account)
                            .build();
                    memberTagRepository.save(memberTag);
                }
            }else if(list.get(i).getTag().getId()==7){ //취미, 조건 : 10만원,5회
                if(list.get(i).getAmount()>=100000 || list.get(i).getCount()>=5){
                    MemberTag memberTag=MemberTag.builder()
                            .tag(tag)
                            .account(account)
                            .build();
                    memberTagRepository.save(memberTag);
                }
            }else if(list.get(i).getTag().getId()==8){ //미용, 조건 : 5만원,5회
                if(list.get(i).getAmount()>=50000 || list.get(i).getCount()>=5){
                    MemberTag memberTag=MemberTag.builder()
                            .tag(tag)
                            .account(account)
                            .build();
                    memberTagRepository.save(memberTag);
                }
            }else if(list.get(i).getTag().getId()==9){ //의료, 조건 : 15만원,5회
                if(list.get(i).getAmount()>=150000 || list.get(i).getCount()>=5){
                    MemberTag memberTag=MemberTag.builder()
                            .tag(tag)
                            .account(account)
                            .build();
                    memberTagRepository.save(memberTag);
                }
            }else if(list.get(i).getTag().getId()==10){ //정기결제, 조건 :1회
                if(list.get(i).getCount()>=1){
                    MemberTag memberTag=MemberTag.builder()
                            .tag(tag)
                            .account(account)
                            .build();
                    memberTagRepository.save(memberTag);
                }
            }else if(list.get(i).getTag().getId()==11){ //반려동물, 조건 : 1회
                if(list.get(i).getCount()>=1){
                    MemberTag memberTag=MemberTag.builder()
                            .tag(tag)
                            .account(account)
                            .build();
                    memberTagRepository.save(memberTag);
                }
            }else if(list.get(i).getTag().getId()==12){ //여행, 조건 : 1회
                if(list.get(i).getCount()>=1){
                    MemberTag memberTag=MemberTag.builder()
                            .tag(tag)
                            .account(account)
                            .build();
                    memberTagRepository.save(memberTag);
                }
            }
        }
    }
}
