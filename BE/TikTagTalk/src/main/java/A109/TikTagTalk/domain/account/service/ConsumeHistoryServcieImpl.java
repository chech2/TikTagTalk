package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.AllConsumeHistoryResponseDto;
import A109.TikTagTalk.domain.account.dto.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.entity.ConsumeHistory;
import A109.TikTagTalk.domain.account.repository.ConsumeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumeHistoryServcieImpl implements ConsumeHistoryService {
    private final ConsumeHistoryRepository consumeHistoryRepository;


    @Override
    @Transactional(readOnly = true)
    public AllConsumeHistoryResponseDto allConsumeHistoryRecently(Long accountId) {
        List<ConsumeHistory> allConsumeHistoryList=consumeHistoryRepository.findAllRecently(accountId);
        return new AllConsumeHistoryResponseDto(allConsumeHistoryList);
    }

    @Override
    @Transactional(readOnly = true)
    public AllConsumeHistoryResponseDto allConsumeHistoryHighest(Long accountId) {
        List<ConsumeHistory> allConsumeHistoryList=consumeHistoryRepository.findAllHighest(accountId);
        return new AllConsumeHistoryResponseDto(allConsumeHistoryList);
    }

    @Override
    @Transactional(readOnly = true)
    public CheckAccountResponseDto checkAccountTotalAccount(Long accountId){
        Long totalAccount= consumeHistoryRepository.checkAccountTotalAccount(accountId);
        return new CheckAccountResponseDto(totalAccount);
    }
}
