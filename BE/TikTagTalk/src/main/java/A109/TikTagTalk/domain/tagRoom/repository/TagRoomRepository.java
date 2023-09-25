package A109.TikTagTalk.domain.tagRoom.repository;

import A109.TikTagTalk.domain.tagRoom.entity.TagRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRoomRepository extends JpaRepository<TagRoom,Long>, TagRoomRepositoryCustom {
}
