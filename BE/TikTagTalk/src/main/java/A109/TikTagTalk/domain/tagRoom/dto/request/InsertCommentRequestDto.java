package A109.TikTagTalk.domain.tagRoom.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InsertCommentRequestDto {
    private String content;
    private MemberDto member;
    private TagRoomDto tagRoom;

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class MemberDto{
        private Long id;
    }
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class TagRoomDto{
        private Long id;
    }
}
