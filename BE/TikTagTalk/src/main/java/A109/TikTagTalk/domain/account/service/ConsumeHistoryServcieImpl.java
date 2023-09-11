package A109.TikTagTalk.domain.account.service;

import A109.TikTagTalk.domain.account.dto.AllConsumeHistoryResponseDto;
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
    public AllConsumeHistoryResponseDto allConsumeHistory() {
        List<ConsumeHistory> allConsumeHistoryList=consumeHistoryRepository.findAll();
//        System.out.println(allConsumeHistoryList.get(0).getAmount());
        return new AllConsumeHistoryResponseDto(allConsumeHistoryList);
    }

    @Override
    public AllConsumeHistoryResponseDto findOne(Long accountId) {
        List<ConsumeHistory> list=consumeHistoryRepository.findByAccountId(accountId);

        return new AllConsumeHistoryResponseDto(list);
    }
}
