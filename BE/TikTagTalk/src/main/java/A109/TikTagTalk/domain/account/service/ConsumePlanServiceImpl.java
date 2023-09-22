package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.request.InsertConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.response.InsertConsumePlanReponseDto;
import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.entity.ConsumePlan;
import A109.TikTagTalk.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsumePlanServiceImpl implements ConsumePlanService{
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public InsertConsumePlanReponseDto insertConsumePlan(InsertConsumePlanRequestDto requestDto) {
        Account account=accountRepository.findById(requestDto.getAccount().getId()).get();
        
        return null;
    }
}
