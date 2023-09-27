package A109.TikTagTalk.domain.tagRoom.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InsertCommentRequestDto {
    private String content;
    private TagRoomDto tagRoom;

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class TagRoomDto{
        private Long id;
    }
}
