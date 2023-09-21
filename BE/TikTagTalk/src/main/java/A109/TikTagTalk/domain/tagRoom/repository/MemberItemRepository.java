package A109.TikTagTalk.domain.tagRoom.repository;

import A109.TikTagTalk.domain.tagRoom.entity.MemberItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberItemRepository extends JpaRepository<MemberItem, Long>,MemberItemRepositoryCustom {
}
