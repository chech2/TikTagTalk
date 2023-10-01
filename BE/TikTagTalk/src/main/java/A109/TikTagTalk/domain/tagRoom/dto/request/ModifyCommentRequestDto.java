package A109.TikTagTalk.domain.tagRoom.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class ModifyCommentRequestDto {
    private Long id;
    private String content;

}
