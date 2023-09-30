package A109.TikTagTalk.domain.tag.service;

import A109.TikTagTalk.domain.tag.dto.request.CheckMemberTagRequestDto;
import A109.TikTagTalk.domain.tag.dto.response.CheckMemberTagResponseDto;
import A109.TikTagTalk.domain.tag.entity.MemberTag;
import A109.TikTagTalk.domain.tag.repository.MemberTagRepository;
import A109.TikTagTalk.domain.user.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberTagServiceImpl implements MemberTagService{
    private final MemberTagRepository memberTagRepository;
    @Override
    @Transactional(readOnly = true)
    public List<CheckMemberTagResponseDto> checkMemberTagList(CheckMemberTagRequestDto requestDto, Member member) {
        List<MemberTag> memberTagList=memberTagRepository.checkMemberTagList(requestDto,member);
        return memberTagList.stream()
                .map(memberTag -> {
                    CheckMemberTagResponseDto.TagDto tagDto= CheckMemberTagResponseDto.TagDto.builder()
                            .name(memberTag.getTag().getName()).build();
                    return CheckMemberTagResponseDto.builder()
                            .tag(tagDto)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
