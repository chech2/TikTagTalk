package A109.TikTagTalk.domain.tagRoom.dto.response;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TagRoomResponseDto {
    private MemberDto member;

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class MemberDto{
        private String userId;
        private int avatarType;
    }
}
