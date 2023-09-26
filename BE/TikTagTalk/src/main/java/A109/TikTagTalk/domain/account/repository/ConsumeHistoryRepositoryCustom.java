package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.dto.request.ConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.dto.response.CheckAccountResponseDto;
import A109.TikTagTalk.domain.account.dto.response.CheckMemberTagResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.entity.ConsumeHistory;
import A109.TikTagTalk.domain.tag.entity.Tag;

import java.time.LocalDate;
import java.util.List;

public interface ConsumeHistoryRepositoryCustom {
    List<ConsumeHistory> findAllRecently(ConsumeHistoryRequestDto requestDto);
    List<ConsumeHistory> findAllHighest(ConsumeHistoryRequestDto requestDto);

    CheckAccountResponseDto checkAccountTagAmount(ConsumeHistoryRequestDto requestDto);

    List<CheckMemberTagResponseDto> calMemberTags(ConsumeHistoryRequestDto requestDto);

    ResponseDto modifyConsumeHistory(ConsumeHistory consumeHistory);
    CheckMemberTagResponseDto calMemberTag(Account account, Tag tag, LocalDate consumeTime);
}
