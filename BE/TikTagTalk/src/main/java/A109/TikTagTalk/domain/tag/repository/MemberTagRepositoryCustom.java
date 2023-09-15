package A109.TikTagTalk.domain.tag.repository;

public interface MemberTagRepositoryCustom {
    Boolean checkMemberTagExist(Long accountId,Long tagId);
}
