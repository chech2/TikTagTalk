package A109.TikTagTalk.domain.tagRoom.service;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseUtil;
import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.repository.AccountRepository;
import A109.TikTagTalk.domain.tag.entity.Tag;
import A109.TikTagTalk.domain.tag.repository.TagRepository;
import A109.TikTagTalk.domain.tagRoom.dto.request.InitMemberItemRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.UpdateMemberItemRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.response.InitMemberItemResponseDto;
import A109.TikTagTalk.domain.tagRoom.entity.Item;
import A109.TikTagTalk.domain.tagRoom.entity.MemberItem;
import A109.TikTagTalk.domain.tagRoom.repository.ItemRepository;
import A109.TikTagTalk.domain.tagRoom.repository.MemberItemRepository;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberItemServiceImpl implements MemberItemService{
    private final MemberItemRepository memberItemRepository;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional //room 얻는 서비스메서드
    public void getRoom(Member member) {
        Item item=itemRepository.findByName("room");
        Long positionX=0L;
        Long positionY=0L;
        Long gridZNumber=0L;
        Long rotation=0L;
        Boolean inRoom=true;
        Boolean wall=false;
        MemberItem memberItem=MemberItem.builder()
                .item(item)
                .member(member)
                .positionX(positionX)
                .positionY(positionY)
                .positionZ(gridZNumber)
                .rotation(rotation)
                .wall(wall)
                .inroom(inRoom)
                .build();
        memberItemRepository.save(memberItem);
    }

    @Override
    @Transactional
    public void memberItemInit(InitMemberItemRequestDto requestDto, Member member) {
        Item item=itemRepository.findById(requestDto.getItem().getId()).get();
        Long positionX=0L;
        Long positionY=0L;
        Long gridZNumber=1L;
        Long rotation=0L;
        Boolean inRoom=false;
        Boolean wall=false;
        if(item.getName().equals("IOU") || item.getName().equals("barbers_pole") || item.getName().equals("youtube_gold_play_button") || item.getName().equals("shit_youtube_gold_play_button") || item.getName().equals("youtube_silver_play_button")||item.getName().equals("shit_youtube_silver_play_button")){
            wall=true;
        }
        MemberItem memberItem=MemberItem.builder()
                .item(item)
                .member(member)
                .positionX(positionX)
                .positionY(positionY)
                .positionZ(gridZNumber)
                .rotation(rotation)
                .inroom(inRoom)
                .wall(wall)
                .build();
        memberItemRepository.save(memberItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InitMemberItemResponseDto> findMemberItems(Long memberId) {
        Member member=memberRepository.findById(memberId).get();
        List<MemberItem> memberItemList=memberItemRepository.findMemberItem(member);
        return memberItemList.stream()
                .map(memberItem -> {
                    InitMemberItemResponseDto.ItemDto itemDto=InitMemberItemResponseDto.ItemDto.builder()
                            .name(memberItem.getItem().getName())
                            .sizeY(memberItem.getItem().getSizeY())
                            .sizeX(memberItem.getItem().getSizeX())
                            .room(memberItem.getItem().getRoom())
                            .build();
                    return InitMemberItemResponseDto.builder()
                            .item(itemDto)
                            .position_x(memberItem.getPositionX())
                            .position_y(memberItem.getPositionY())
                            .grid_z_number(memberItem.getPositionZ())
                            .isRoom(memberItem.getInroom())
                            .rotation(memberItem.getRotation())
                            .wall(memberItem.getWall())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseDto updateMemberItem(UpdateMemberItemRequestDto requestDto,Member member) {
        List<UpdateMemberItemRequestDto.UpdateInfoDto> infoList=requestDto.getUpdateInfo();
        for(UpdateMemberItemRequestDto.UpdateInfoDto info:infoList){
            MemberItem memberItem=memberItemRepository.findByMemberItemName(member,info.getItem().getName());
            memberItemRepository.updateMemberItem(memberItem,info);
        }
        return ResponseUtil.Success("에셋 위치 수정 성공");
    }
    //멤버 아이템 !!!!! 멤버 태그!!! 수정!!
}
