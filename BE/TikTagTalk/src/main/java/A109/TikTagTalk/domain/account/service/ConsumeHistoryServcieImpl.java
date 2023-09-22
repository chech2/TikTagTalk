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
import A109.TikTagTalk.domain.tagRoom.entity.Item;
import A109.TikTagTalk.domain.tagRoom.entity.MemberItem;
import A109.TikTagTalk.domain.tagRoom.repository.MemberItemRepository;
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
    private final MemberTagRepository memberTagRepository;
    private final MemberItemRepository memberItemRepository;
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

        if(!(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),requestDto.getConsumeTime().toLocalDate()))){
            CheckMemberTagResponseDto response=consumeHistoryRepository.calMemberTag(account,tag,requestDto.getConsumeTime().toLocalDate());
            getMemberTag(response.getTag().getId(),response.getAmount(),response.getCount(),account,tag,requestDto.getConsumeTime().toLocalDate());
        }
        return ResponseUtil.Success("소비 내역 추가 성공");
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
    public ResponseDto makeMemberTags(ConsumeHistoryRequestDto requestDto) {
        List<CheckMemberTagResponseDto> list = consumeHistoryRepository.calMemberTags(requestDto);
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
                getMemberTag(list.get(i).getTag().getId(),list.get(i).getAmount(),list.get(i).getCount(),account,tag,gotTime);
            }
        }
        return ResponseUtil.Success("태그 더미 데이터 생성 성공");
    }
    public void saveMemberTag(Account account,Tag tag, LocalDate gotTime){
        MemberTag memberTag = MemberTag.builder()
                .tag(tag)
                .account(account)
                .gotTime(gotTime)
                .build();
        memberTagRepository.save(memberTag);
    }
    public void getMemberTag(Long tagId,Long amountSum,Long amountCount,Account account, Tag tag,LocalDate gotTime){
        if (tagId == 1) { //식비, 조건 : 35만원,30회
            if (amountSum >= 350000 || amountCount >= 30) {
                saveMemberTag(account,tag,gotTime);
            }else{
                if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(account,tag,gotTime);
                }
            }
        } else if (tagId == 2) { //편/마/잡, 조건 : 25만원,20회
            if (amountSum >= 250000 || amountCount >= 20) {
                saveMemberTag(account,tag,gotTime);
            }else{
                if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(account,tag,gotTime);
                }
            }
        } else if (tagId == 3) { //교통, 조건 : 15만원,10회
            if (amountSum >= 150000 || amountCount >= 10) {
                saveMemberTag(account,tag,gotTime);
            }else{
                if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(account,tag,gotTime);
                }
            }
        } else if (tagId == 4) { //쇼핑, 조건 : 10만원,5회
            if (amountSum >= 100000 || amountCount >= 5) {
                saveMemberTag(account,tag,gotTime);
            }else{
                if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(account,tag,gotTime);
                }
            }
        } else if (tagId == 5) { //카페, 조건 : 35만원,30회
            if (amountSum >= 350000 || amountCount >= 30) {
                Item item=tag.getItemList().get(0);
                MemberItem memberItem=MemberItem.builder()
                        .item(item)
                        .account(account)
                        .room(false)
                        .wall(false)
                        .positionY(5L)
                        .positionX(5L)
                        .positoinZ(1L)
//                        .sizeX()
//                        .sizeY()
                        .rotation(0L)
                        .build();
                memberItemRepository.save(memberItem);
                saveMemberTag(account,tag,gotTime);
            }else{
                if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(account,tag,gotTime);
                }
            }
        } else if (tagId == 6) { //보험, 조건 : 5만원
            if (amountSum >= 50000) {
                saveMemberTag(account,tag,gotTime);
            }else{
                if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(account,tag,gotTime);
                }
            }
        } else if (tagId == 7) { //취미, 조건 : 10만원,5회
            if (amountSum >= 100000 || amountCount >= 5) {
                Item item=tag.getItemList().get(0);
                MemberItem memberItem=MemberItem.builder()
                        .item(item)
                        .account(account)
                        .positionY(5L)
                        .positionX(5L)
                        .positoinZ(1L)
                        .rotation(0L)
//                        .sizeX()
//                        .sizeY()
                        .build();
                memberItemRepository.save(memberItem);
                saveMemberTag(account,tag,gotTime);
            }else{
                if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(account,tag,gotTime);
                }
            }
        } else if (tagId == 8) { //미용, 조건 : 5만원,5회
            if (amountSum >= 50000 || amountCount >= 5) {
                saveMemberTag(account,tag,gotTime);
            }else{
                if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(account,tag,gotTime);
                }
            }
        } else if (tagId == 9) { //의료, 조건 : 15만원,5회
            if (amountSum >= 150000 || amountCount >= 5) {
                saveMemberTag(account,tag,gotTime);
            }else{
                if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(account,tag,gotTime);
                }
            }
        } else if (tagId == 10) { //정기결제, 조건 :1회
            if (amountCount >= 1) {
                saveMemberTag(account,tag,gotTime);
            }else{
                if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(account,tag,gotTime);
                }
            }
        } else if (tagId == 11) { //반려동물, 조건 : 1회
            if (amountCount >= 1) {
                saveMemberTag(account,tag,gotTime);
            }else{
                if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(account,tag,gotTime);
                }
            }
        } else if (tagId == 12) { //여행, 조건 : 1회
            if (amountCount >= 1) {
                saveMemberTag(account,tag,gotTime);
            }else{
                if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(account,tag,gotTime);
                }
            }
        }
    }
    public void deleteMemberTag(Account account, Tag tag,LocalDate gotTime){
        MemberTag memberTag=memberTagRepository.findByAccountTagGotTime(account.getId(),tag.getId(),gotTime);
        memberTagRepository.delete(memberTag);
    }
    @Override
    @Transactional
    public ResponseDto deleteConsumeHistory(Long consumeHistoryId) {
        ConsumeHistory consumeHistory=consumeHistoryRepository.findById(consumeHistoryId).get();
        Account account=consumeHistory.getAccount();
        Tag tag=consumeHistory.getTag();

        if(!(consumeHistory.getIsManual())){
            consumeHistoryRepository.delete(consumeHistory);
            if(memberTagRepository.checkMemberTagExist(account.getId(),tag.getId(),consumeHistory.getConsumeTime().toLocalDate())){
                CheckMemberTagResponseDto response=consumeHistoryRepository.calMemberTag(account,tag,consumeHistory.getConsumeTime().toLocalDate());
                getMemberTag(response.getTag().getId(),response.getAmount(),response.getCount(),account,tag,consumeHistory.getConsumeTime().toLocalDate());
            }
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
