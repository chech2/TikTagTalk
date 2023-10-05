package A109.TikTagTalk.domain.skin.service;

import A109.TikTagTalk.domain.skin.dto.request.BuyRequest;
import A109.TikTagTalk.domain.skin.dto.response.BuyResponse;
import A109.TikTagTalk.domain.skin.entity.MemberSkin;
import A109.TikTagTalk.domain.skin.repository.MemberSkinRepository;
import A109.TikTagTalk.domain.user.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberSkinServiceImpl implements MemberSkinService{
    private final MemberSkinRepository memberSkinRepository;
    @Override
    public List<MemberSkin> memberSkinList(Member member) {


        return memberSkinRepository.findAllByMemberId(member.getId());
    }
}
