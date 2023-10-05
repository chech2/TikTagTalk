package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.request.AddConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ModifyConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.response.*;
import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.entity.ConsumeHistory;
import A109.TikTagTalk.domain.account.entity.ConsumePlan;
import A109.TikTagTalk.domain.account.repository.AccountRepository;
import A109.TikTagTalk.domain.account.repository.ConsumeHistoryRepository;
import A109.TikTagTalk.domain.account.repository.ConsumePlanRepository;
import A109.TikTagTalk.domain.tag.entity.MemberTag;
import A109.TikTagTalk.domain.tag.entity.Tag;
import A109.TikTagTalk.domain.tag.repository.MemberTagRepository;
import A109.TikTagTalk.domain.tag.repository.StoreRepository;
import A109.TikTagTalk.domain.tag.repository.TagRepository;
import A109.TikTagTalk.domain.tagRoom.dto.request.InitMemberItemRequestDto;
import A109.TikTagTalk.domain.tagRoom.entity.Item;
import A109.TikTagTalk.domain.tagRoom.entity.MemberItem;
import A109.TikTagTalk.domain.tagRoom.repository.ItemRepository;
import A109.TikTagTalk.domain.tagRoom.repository.MemberItemRepository;
import A109.TikTagTalk.domain.tagRoom.service.MemberItemService;
import A109.TikTagTalk.domain.user.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsumeHistoryServcieImpl implements ConsumeHistoryService {
    private final ConsumeHistoryRepository consumeHistoryRepository;
    private final AccountRepository accountRepository;
    private final TagRepository tagRepository;
    private final MemberTagRepository memberTagRepository;
    private final MemberItemRepository memberItemRepository;
    private final ItemRepository itemRepository;
    private final MemberItemService memberItemService;
    private final ConsumePlanRepository consumePlanRepository;
    @Override
    @Transactional
    public ResponseDto addConsumeHistory(AddConsumeHistoryRequestDto requestDto,Member member) {
        Account account=accountRepository.findAccountByMemberId(member);
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

        if(!(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),requestDto.getConsumeTime().toLocalDate()))){
            CheckMemberTagResponseDto response=consumeHistoryRepository.calMemberTag(account,tag,requestDto.getConsumeTime().toLocalDate());
            getMemberTag(response.getTag().getId(),response.getAmount(),response.getCount(),member,tag,requestDto.getConsumeTime().toLocalDate());
        }
        return ResponseUtil.Success("소비 내역 추가 성공");
    }

    @Override
    @Transactional(readOnly = true)
    public List<AllConsumeHistoryResponseDto> allConsumeHistoryRecently(ConsumeHistoryRequestDto requestDto, Member member) {
        Account account=accountRepository.findAccountByMemberId(member);
        List<ConsumeHistory> allConsumeHistoryList = consumeHistoryRepository.findAllRecently(requestDto,account);
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
    public List<AllConsumeHistoryResponseDto> allConsumeHistoryHighest(ConsumeHistoryRequestDto requestDto,Member member) {
        Account account=accountRepository.findAccountByMemberId(member);
        List<ConsumeHistory> allConsumeHistoryList = consumeHistoryRepository.findAllHighest(requestDto,account);
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
    public CheckAccountResponseDto checkAccountTotalAccount(ConsumeHistoryRequestDto requestDto,Member member) {
        Account account=accountRepository.findAccountByMemberId(member);
        CheckAccountResponseDto result=consumeHistoryRepository.checkAccountTagAmount(requestDto.getYearAndMonth(),account);
        return result;
    }

    @Override
    @Transactional //더미 데이터 용 멤버태그 획득
    public ResponseDto makeMemberTags(ConsumeHistoryRequestDto requestDto,Member member) {
        Account account=accountRepository.findAccountByMemberId(member);
        List<CheckMemberTagResponseDto> list = consumeHistoryRepository.calMemberTags(requestDto,account);
        //Account는 id가 1인 애를 그냥 넣자
        for (int i = 0; i < list.size(); i++) {
            Tag tag=tagRepository.findById(list.get(i).getTag().getId()).get();
            LocalDate gotTime;
            if(requestDto.getYearAndMonth()==null){
                gotTime=LocalDate.now();
            }else{
                gotTime=LocalDate.parse(requestDto.getYearAndMonth()+"-01");
            }
            if(!(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime))) {
                getMemberTag(list.get(i).getTag().getId(),list.get(i).getAmount(),list.get(i).getCount(),member,tag,gotTime);
            }
        }
        return ResponseUtil.Success("태그 더미 데이터 생성 성공");
    }
    @Transactional
    public void saveMemberTag(Member member,Tag tag, LocalDate gotTime,Item item){
        MemberTag memberTag = MemberTag.builder()
                .tag(tag)
                .member(member)
                .gotTime(gotTime)
                .build();
        memberTagRepository.save(memberTag);
        InitMemberItemRequestDto.TagDto tagDto= InitMemberItemRequestDto.TagDto.builder()
                .id(tag.getId()).build();
        InitMemberItemRequestDto.ItemDto itemDto= InitMemberItemRequestDto.ItemDto.builder()
                .id(item.getId()).build();
        InitMemberItemRequestDto requestDto=InitMemberItemRequestDto.builder()
                .tag(tagDto)
                .item(itemDto)
                .build();
        memberItemService.memberItemInit(requestDto,member);
    }
    public boolean isExceedConsumePlan(Long consumePlaneAmount,Long spentAmount){ //소비 계획을 넘었는지 않넘었는지 true이면 넘었음 -> 해당 shit으로 변경
        if(consumePlaneAmount<spentAmount){
            return false;
        }
        return true;
    }
    public String makeLocalDateToYearAndMonth(LocalDate localDate){
        int year=localDate.getYear();
        int month=localDate.getMonthValue();
        if(month<10){
            return year+"-0"+month;
        }
        return year+"-"+month;
    }
    public void getMemberTag(Long tagId,Long amountSum,Long amountCount,Member member, Tag tag,LocalDate gotTime){
        //consumePlan이 null이면 shit으로 변하지 X ->isExceedConsumePlan 호출 X
        ConsumePlan consumePlan=consumePlanRepository.findByMemberIdAndYearAndMonth(member.getId(),makeLocalDateToYearAndMonth(gotTime));

        if (tagId == 1) { //식비, 조건 : 35만원,30회
            if (amountSum >= 350000 || amountCount >= 30) {
                if(!(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime))){
                    //shit이 아닌 아이템 찾음 -> isExceedConsumePlan해서 true이면, shit인 item을 찾아야됨
                    if(consumePlan!=null){
                        if(isExceedConsumePlan(consumePlan.getEatAmount(),amountSum)){ //소비계획을 안넘었다.
                           Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                        else{
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,true);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                    }else{
                        Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                        saveMemberTag(member,tag,gotTime,item);
                    }

                }
            }else{
                if(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(member,tag,gotTime);
                }
            }
        } else if (tagId == 2) { //편/마/잡, 조건 : 25만원,20회
            if (amountSum >= 250000 || amountCount >= 20) {
                if(!(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime))){
                    if(consumePlan!=null){
                        if(isExceedConsumePlan(consumePlan.getGroceryAmount(),amountSum)){ //소비계획을 안넘었다.
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                        else{
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,true);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                    }else{
                        Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                        saveMemberTag(member,tag,gotTime,item);
                    }
                }
            }else{
                if(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(member,tag,gotTime);
                }
            }
        } else if (tagId == 3) { //교통, 조건 : 15만원,10회
            if (amountSum >= 150000 || amountCount >= 10) {
                if(!(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime))){
                    if(consumePlan!=null){
                        if(isExceedConsumePlan(consumePlan.getRideAmount(),amountSum)){ //소비계획을 안넘었다.
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                        else{
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,true);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                    }else{
                        Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                        saveMemberTag(member,tag,gotTime,item);
                    }
                }
            }else{
                if(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(member,tag,gotTime);
                }
            }
        } else if (tagId == 4) { //쇼핑, 조건 : 10만원,5회
            if (amountSum >= 100000 || amountCount >= 5) {
                if(!(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime))){
                    if(consumePlan!=null){
                        if(isExceedConsumePlan(consumePlan.getShoppingAmount(),amountSum)){ //소비계획을 안넘었다.
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                        else{
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,true);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                    }else{
                        Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                        saveMemberTag(member,tag,gotTime,item);
                    }
                }
            }else{
                if(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(member,tag,gotTime);
                }
            }
        } else if (tagId == 5) { //카페, 조건 : 35만원,30회
            if (amountSum >= 350000 || amountCount >= 30) {
                if(!(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime))){
                    if(consumePlan!=null){
                        if(isExceedConsumePlan(consumePlan.getSnackAmount(),amountSum)){ //소비계획을 안넘었다.
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                            //cute_coffee_cup
                            if(!(item.getName().equals("cute_coffee_cup"))){
                                saveMemberTag(member,tag,gotTime,item);
                            }
                        }
                        else{
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,true);
                            if(!(item.getName().equals("cute_coffee_cup"))){
                                saveMemberTag(member,tag,gotTime,item);
                            }
                        }
                    }else{
                        Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                        if(!(item.getName().equals("cute_coffee_cup"))){
                            saveMemberTag(member,tag,gotTime,item);
                        }
                    }
                }
            }else{
                if(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(member,tag,gotTime);
                }
            }
        } else if (tagId == 6) { //보험, 조건 : 5만원
            if (amountSum >= 50000) {
                if(!(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime))){
                    if(consumePlan!=null){
                        if(isExceedConsumePlan(consumePlan.getInsuranceAmount(),amountSum)){ //소비계획을 안넘었다.
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                        else{
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,true);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                    }else{
                        Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                        saveMemberTag(member,tag,gotTime,item);
                    }
                }
            }else{
                if(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(member,tag,gotTime);
                }
            }
        } else if (tagId == 7) { //취미, 조건 : 10만원,5회
            if (amountSum >= 100000 || amountCount >= 5) {
                if(!(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime))){
                    if(consumePlan!=null){
                        if(isExceedConsumePlan(consumePlan.getHobbyAmount(),amountSum)){ //소비계획을 안넘었다.
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                        else{
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,true);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                    }else{
                        Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                        saveMemberTag(member,tag,gotTime,item);
                    }
                }
            }else{
                if(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(member,tag,gotTime);
                }
            }
        } else if (tagId == 8) { //미용, 조건 : 5만원,5회
            if (amountSum >= 50000 || amountCount >= 5) {
                if(!(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime))){
                    if(consumePlan!=null){
                        if(isExceedConsumePlan(consumePlan.getHairAmount(),amountSum)){ //소비계획을 안넘었다.
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                        else{
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,true);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                    }else{
                        Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                        saveMemberTag(member,tag,gotTime,item);
                    }
                }
            }else{
                if(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(member,tag,gotTime);
                }
            }
        } else if (tagId == 9) { //의료, 조건 : 15만원,5회
            if (amountSum >= 150000 || amountCount >= 5) {
                if(consumePlan!=null){
                    if(isExceedConsumePlan(consumePlan.getHealthAmount(),amountSum)){ //소비계획을 안넘었다.
                        Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                        saveMemberTag(member,tag,gotTime,item);
                    }
                    else{
                        Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,true);
                        saveMemberTag(member,tag,gotTime,item);
                    }
                }else{
                    Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                    saveMemberTag(member,tag,gotTime,item);
                }
            }else{
                if(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(member,tag,gotTime);
                }
            }
        } else if (tagId == 10) { //정기결제, 조건 :1회
            if (amountCount >= 1) {
                if(!(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime))){
                    if(consumePlan!=null){
                        if(isExceedConsumePlan(consumePlan.getOttAmount(),amountSum)){ //소비계획을 안넘었다.
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                            if(!(item.getName().equals("youtube_gold_play_button"))){
                                saveMemberTag(member,tag,gotTime,item);
                            }
                        }
                        else{
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,true);
                            if(!(item.getName().equals("youtube_gold_play_button"))){
                                saveMemberTag(member,tag,gotTime,item);
                            }
                        }
                    }else{
                        Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                        if(!(item.getName().equals("youtube_gold_play_button"))){
                            saveMemberTag(member,tag,gotTime,item);
                        }
                    }
                }
            }else{
                if(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(member,tag,gotTime);
                }
            }
        } else if (tagId == 11) { //반려동물, 조건 : 1회
            if (amountCount >= 1) {
                if(!(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime))){
                    if(consumePlan!=null){
                        if(isExceedConsumePlan(consumePlan.getPetAmount(),amountSum)){ //소비계획을 안넘었다.
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                            if(!(item.getName().equals("shiba"))){
                                saveMemberTag(member,tag,gotTime,item);
                            }
                        }
                        else{
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,true);
                            if(!(item.getName().equals("shiba"))){
                                saveMemberTag(member,tag,gotTime,item);
                            }
                            saveMemberTag(member,tag,gotTime,item);
                        }
                    }else{
                        Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                        if(!(item.getName().equals("shiba"))){
                            saveMemberTag(member,tag,gotTime,item);
                        }
                        saveMemberTag(member,tag,gotTime,item);
                    }
                }
            }else{
                if(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(member,tag,gotTime);
                }
            }
        } else if (tagId == 12) { //여행, 조건 : 1회
            if (amountCount >= 1) {
                if(!(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime))){
                    if(consumePlan!=null){
                        if(isExceedConsumePlan(consumePlan.getTravelAmount(),amountSum)){ //소비계획을 안넘었다.
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                        else{
                            Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,true);
                            saveMemberTag(member,tag,gotTime,item);
                        }
                    }else{
                        Item item=itemRepository.findItemByTagIdAndIsSkinAndIsShit(tagId,false,false);
                        saveMemberTag(member,tag,gotTime,item);
                    }
                }
            }else{
                if(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),gotTime)){
                    deleteMemberTag(member,tag,gotTime);
                }
            }
        }
    }

    @Transactional
    public void deleteMemberTag(Member member, Tag tag,LocalDate gotTime){
        MemberTag memberTag=memberTagRepository.findByMemberTagGotTime(member.getId(),tag.getId(),gotTime);
        memberTagRepository.delete(memberTag);
    }
    @Override
    @Transactional
    public ResponseDto deleteConsumeHistory(Long consumeHistoryId,Member member) {
        ConsumeHistory consumeHistory=consumeHistoryRepository.findById(consumeHistoryId).get();
        Account account=consumeHistory.getAccount();
        Tag tag=consumeHistory.getTag();

        if(!(consumeHistory.getIsManual())){
            consumeHistoryRepository.delete(consumeHistory);

            if(memberTagRepository.checkMemberTagExist(member.getId(),tag.getId(),consumeHistory.getConsumeTime().toLocalDate())){
                CheckMemberTagResponseDto response=consumeHistoryRepository.calMemberTag(account,tag,consumeHistory.getConsumeTime().toLocalDate());
                getMemberTag(response.getTag().getId(),response.getAmount(),response.getCount(),member,tag,consumeHistory.getConsumeTime().toLocalDate());
            }
            return ResponseUtil.Success("해당 소비 내역 삭제 성공");
        }
        return ResponseUtil.Failure("해당 소비 내역 삭제 실패");
    }

    @Override
    @Transactional
    public ResponseDto modifyConsumeHistory(ModifyConsumeHistoryRequestDto requestDto,Long consumeHistoryId,Member member) {
        Tag tag=Tag.builder().id(requestDto.getTag().getId()).build();
        Account account=member.getAccount();

        ConsumeHistory consumeHistory=ConsumeHistory.builder()
                .id(consumeHistoryId)
                .storeName(requestDto.getStoreName())
                .amount(requestDto.getAmount())
                .consumeTime(requestDto.getConsumeTime())
                .tag(tag)
                .build();
        consumeHistoryRepository.modifyConsumeHistory(consumeHistory);
        CheckMemberTagResponseDto response=consumeHistoryRepository.calMemberTag(account,tag,consumeHistory.getConsumeTime().toLocalDate());
        getMemberTag(response.getTag().getId(),response.getAmount(),response.getCount(),member,tag,consumeHistory.getConsumeTime().toLocalDate());

        return ResponseUtil.Success("수동 등록 수정 성공");
    }
}
