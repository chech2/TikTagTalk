package A109.TikTagTalk.domain.tag.repository;

import A109.TikTagTalk.domain.tag.dto.request.CheckMemberTagRequestDto;
import A109.TikTagTalk.domain.tag.entity.MemberTag;
import A109.TikTagTalk.domain.user.entity.Member;

import java.time.LocalDate;
import java.util.List;

public interface MemberTagRepositoryCustom {
    Boolean checkMemberTagExist(Long memberId, Long tagId, LocalDate gotTime);
    List<MemberTag> checkMemberTagList(CheckMemberTagRequestDto requestDto, Member member);

    MemberTag findByMemberTagGotTime(Long memberId,Long tagId,LocalDate gotTime);
}
