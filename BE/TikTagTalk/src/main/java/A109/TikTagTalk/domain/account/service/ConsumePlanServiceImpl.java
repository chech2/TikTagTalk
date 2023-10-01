package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.request.InsertConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseUtil;
import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.entity.ConsumePlan;
import A109.TikTagTalk.domain.account.repository.AccountRepository;
import A109.TikTagTalk.domain.account.repository.ConsumePlanRepository;
import A109.TikTagTalk.domain.user.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsumePlanServiceImpl implements ConsumePlanService{
    private final AccountRepository accountRepository;
    private final ConsumePlanRepository consumePlanRepository;
    @Override
    @Transactional
    public ResponseDto insertConsumePlan(InsertConsumePlanRequestDto requestDto, Member member) {
        Account account = member.getAccount();
        Long totalAmount= requestDto.getTotalAmount();
        ConsumePlan consumePlan= ConsumePlan.builder()
                .totalAmount(totalAmount)
                .account(account)
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
        consumePlanRepository.save(consumePlan);
        return ResponseUtil.Success("consumeplan 삽입 성공");
    }
}