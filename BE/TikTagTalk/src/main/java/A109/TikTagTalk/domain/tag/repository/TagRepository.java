package A109.TikTagTalk.domain.tag.repository;

import A109.TikTagTalk.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
