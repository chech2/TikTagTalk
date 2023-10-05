package A109.TikTagTalk.domain.tagRoom.service;

import A109.TikTagTalk.domain.tagRoom.dto.response.TagRoomResponseDto;
import A109.TikTagTalk.domain.tagRoom.entity.TagRoom;
import A109.TikTagTalk.domain.tagRoom.repository.TagRoomRepository;
import A109.TikTagTalk.domain.user.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagRoomServiceImpl implements TagRoomService{
    private final TagRoomRepository tagRoomRepository;

    @Override
    @Transactional
    public TagRoomResponseDto findTagRoomOwner(Long tagRoomId){
        TagRoom tagRoom=tagRoomRepository.findById(tagRoomId).get();
        Member member=tagRoom.getMember();
        TagRoomResponseDto.MemberDto owner= TagRoomResponseDto.MemberDto.builder()
                .userId(member.getUserId())
                .avatarType(member.getAvatarType()).build();
        TagRoomResponseDto tagRoomResponse=TagRoomResponseDto.builder()
                .member(owner)
                .build();
        return tagRoomResponse;
    }

}
