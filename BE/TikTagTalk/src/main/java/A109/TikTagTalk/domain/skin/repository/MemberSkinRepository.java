package A109.TikTagTalk.domain.skin.repository;

import A109.TikTagTalk.domain.skin.entity.MemberSkin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberSkinRepository extends JpaRepository<MemberSkin, Long> {

    List<MemberSkin> findAllByMemberId(Long memberId);
}
