package A109.TikTagTalk.domain.tagRoom.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InsertCommentRequestDto {
    private String content;
    private AccountDto account; //멤버로 바꿔
    private TagRoomDto tagRoom;

    //멤버로 바꿔
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class AccountDto{
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
