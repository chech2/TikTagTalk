package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.request.AllConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.request.ConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.response.AllConsumePlanResonseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseUtil;
import A109.TikTagTalk.domain.account.entity.ConsumePlan;
import A109.TikTagTalk.domain.account.exception.InvalidException;
import A109.TikTagTalk.domain.account.exception.NotExistException;
import A109.TikTagTalk.domain.account.repository.AccountRepository;
import A109.TikTagTalk.domain.account.repository.ConsumePlanRepository;
import A109.TikTagTalk.domain.tagRoom.exception.CustomAccessDeniedException;
import A109.TikTagTalk.domain.user.entity.Member;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsumePlanServiceImpl implements ConsumePlanService{
    private final AccountRepository accountRepository;
    private final ConsumePlanRepository consumePlanRepository;
    @Override
    @Transactional
    public ResponseDto insertConsumePlan(ConsumePlanRequestDto requestDto, Member member) throws InvalidException{
        Long totalAmount= requestDto.getTotalAmount();
        ConsumePlan consumePlan= ConsumePlan.builder()
                .totalAmount(totalAmount)
                .member(member)
                .yearAndMonth(requestDto.getYearAndMonth())
                .eatAmount((long)(totalAmount*((requestDto.getEatPercent())*1.0/100.0)))
                .groceryAmount((long)(totalAmount*((requestDto.getGroceryPercent())*1.0/100.0)))
                .rideAmount((long)(totalAmount*((requestDto.getRidePercent())*1.0/100.0)))
                .shoppingAmount((long)(totalAmount*((requestDto.getShoppingPercent())*1.0/100.0)))
                .snackAmount((long)(totalAmount*((requestDto.getSnackPercent())*1.0/100.0)))
                .insuranceAmount((long)(totalAmount*((requestDto.getInsurancePercent())*1.0/100.0)))
                .hobbyAmount((long)(totalAmount*((requestDto.getHobbyPercent())*1.0/100.0)))
                .hairAmount((long)(totalAmount*((requestDto.getHairPercent())*1.0/100.0)))
                .healthAmount((long)(totalAmount*((requestDto.getHealthPercent())*1.0/100.0)))
                .ottAmount((long)(totalAmount*((requestDto.getOttPercent())*1.0/100.0)))
                .petAmount((long)(totalAmount*((requestDto.getPetPercent())*1.0/100.0)))
                .travelAmount((long)(totalAmount*((requestDto.getTravelPercent())*1.0/100.0)))
                .build();
        int percentSum=0;
        percentSum= requestDto.getEatPercent()+requestDto.getGroceryPercent()+requestDto.getRidePercent()+requestDto.getShoppingPercent()+requestDto.getSnackPercent()+requestDto.getInsurancePercent()+requestDto.getHobbyPercent()+requestDto.getHairPercent()+requestDto.getHealthPercent()+requestDto.getOttPercent()+requestDto.getPetPercent()+requestDto.getTravelPercent();
        if(percentSum!=100){
            throw new InvalidException(HttpStatus.SC_BAD_REQUEST,"percent의 합은 100이 되어야 합니다.");
        }
        consumePlanRepository.save(consumePlan);
        return ResponseUtil.Success("consumeplan 삽입 성공");
    }

    @Override
    @Transactional(readOnly = true)
    public AllConsumePlanResonseDto allConsumePlan(AllConsumePlanRequestDto requestDto,Member member) {
        ConsumePlan consumePlan=consumePlanRepository.findByMemberIdAndYearAndMonth(member.getId(),requestDto.getYearAndMonth());
        if(consumePlan==null){
            throw new NotExistException(HttpStatus.SC_INTERNAL_SERVER_ERROR,"해당 consumeplan을 찾을 수 없습니다.");
        }
        AllConsumePlanResonseDto resonse=AllConsumePlanResonseDto.builder()
                .eatAmount(consumePlan.getEatAmount())
                .groceryAmount(consumePlan.getGroceryAmount())
                .hairAmount(consumePlan.getHairAmount())
                .healthAmount(consumePlan.getHealthAmount())
                .hobbyAmount(consumePlan.getHobbyAmount())
                .insuranceAmount(consumePlan.getInsuranceAmount())
                .ottAmount(consumePlan.getOttAmount())
                .petAmount(consumePlan.getPetAmount())
                .rideAmount(consumePlan.getRideAmount())
                .shoppingAmount(consumePlan.getShoppingAmount())
                .snackAmount(consumePlan.getSnackAmount())
                .travelAmount(consumePlan.getTravelAmount())
                .totalAmount(consumePlan.getTotalAmount())
                .yearAndMonth(consumePlan.getYearAndMonth()).build();
        return resonse;
    }

    @Override
    @Transactional
    public ResponseDto modifyConsumePlan(ConsumePlanRequestDto requestDto,Long planId, Member member) {
        ConsumePlan findConsumePlan=consumePlanRepository.findByMemberIdAndYearAndMonth(member.getId(),requestDto.getYearAndMonth());
        if(findConsumePlan==null){
            throw new NotExistException(HttpStatus.SC_INTERNAL_SERVER_ERROR,"해당 consumeplan을 찾을 수 없습니다.");
        }
        if(findConsumePlan.getMember().getId()!=member.getId()){
            throw new CustomAccessDeniedException(HttpStatus.SC_FORBIDDEN,"권한이 없습니다.");
        }
        Long totalAmount= requestDto.getTotalAmount();
        ConsumePlan consumePlan= ConsumePlan.builder()
                .totalAmount(totalAmount)
                .id(planId)
                .yearAndMonth(requestDto.getYearAndMonth())
                .eatAmount((long)(totalAmount*((requestDto.getEatPercent())*1.0/100.0)))
                .groceryAmount((long)(totalAmount*((requestDto.getGroceryPercent())*1.0/100.0)))
                .rideAmount((long)(totalAmount*((requestDto.getRidePercent())*1.0/100.0)))
                .shoppingAmount((long)(totalAmount*((requestDto.getShoppingPercent())*1.0/100.0)))
                .snackAmount((long)(totalAmount*((requestDto.getSnackPercent())*1.0/100.0)))
                .insuranceAmount((long)(totalAmount*((requestDto.getInsurancePercent())*1.0/100.0)))
                .hobbyAmount((long)(totalAmount*((requestDto.getHobbyPercent())*1.0/100.0)))
                .hairAmount((long)(totalAmount*((requestDto.getHairPercent())*1.0/100.0)))
                .healthAmount((long)(totalAmount*((requestDto.getHealthPercent())*1.0/100.0)))
                .ottAmount((long)(totalAmount*((requestDto.getOttPercent())*1.0/100.0)))
                .petAmount((long)(totalAmount*((requestDto.getPetPercent())*1.0/100.0)))
                .travelAmount((long)(totalAmount*((requestDto.getTravelPercent())*1.0/100.0)))
                .build();
        return consumePlanRepository.modifyConsumePlan(consumePlan);
    }
}