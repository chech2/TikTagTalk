package A109.TikTagTalk.domain.user.dto.response;

import A109.TikTagTalk.domain.user.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class FindMemberResponseDto {

    private Long id;
    private String userId;
    private String name;
    private int avatarType;

    public static FindMemberResponseDto toDTO(Member member) {
        return FindMemberResponseDto.builder()
                .id(member.getId())
                .userId(member.getUserId())
                .name(member.getName())
                .avatarType(member.getAvatarType())
                .build();
    }
}
