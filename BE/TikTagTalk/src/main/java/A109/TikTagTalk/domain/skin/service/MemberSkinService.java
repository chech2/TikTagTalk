package A109.TikTagTalk.domain.skin.service;

import A109.TikTagTalk.domain.skin.entity.MemberSkin;
import A109.TikTagTalk.domain.user.entity.Member;

import java.util.List;

public interface MemberSkinService {
    List<MemberSkin> memberSkinList(Member member);
}
