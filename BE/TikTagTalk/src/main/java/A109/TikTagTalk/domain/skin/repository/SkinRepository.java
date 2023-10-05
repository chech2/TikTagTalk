package A109.TikTagTalk.domain.skin.repository;

import A109.TikTagTalk.domain.skin.entity.Skin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkinRepository extends JpaRepository<Skin, Long>, SkinRepositoryCustom {
}
