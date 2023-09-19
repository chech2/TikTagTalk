package A109.TikTagTalk.domain.tag.repository;

import A109.TikTagTalk.domain.tag.dto.request.CheckMemberTagRequestDto;
import A109.TikTagTalk.domain.tag.entity.MemberTag;

import java.time.LocalDate;
import java.util.List;

public interface MemberTagRepositoryCustom {
    Boolean checkMemberTagExist(Long accountId, Long tagId, LocalDate gotTime);
    List<MemberTag> checkMemberTagList(CheckMemberTagRequestDto requestDto);

    MemberTag findByAccountTagGotTime(Long accountId,Long tagId,LocalDate gotTime);
}
