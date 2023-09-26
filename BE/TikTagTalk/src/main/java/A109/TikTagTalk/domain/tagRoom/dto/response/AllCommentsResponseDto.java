package A109.TikTagTalk.domain.tagRoom.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AllCommentsResponseDto {
    private String content;
    private LocalDateTime writtenTime;
    private MemberDto member; //댓글 작성자 id
    private TagRoomDto tagRoom; //태그룸 주인 id
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
