package A109.TikTagTalk.domain.tag.repository;

import A109.TikTagTalk.domain.tag.entity.MemberTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTagRepository extends JpaRepository<MemberTag,Long>,MemberTagRepositoryCustom {

}
