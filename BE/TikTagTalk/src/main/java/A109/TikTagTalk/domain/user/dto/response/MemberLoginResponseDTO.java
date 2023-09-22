package A109.TikTagTalk.domain.user.dto.response;

import A109.TikTagTalk.domain.user.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MemberLoginResponseDTO {

    private Long id;
    private String userId;
    private int avatarType;
    private int coin;
    private int point;

    public static MemberLoginResponseDTO toDTO(Member member) {
        return MemberLoginResponseDTO.builder()
                .id(member.getId())
                .userId(member.getUserId())
                .avatarType(member.getAvatarType())
                .coin(member.getCoin())
                .point(member.getPoint())
                .build();
    }
}
