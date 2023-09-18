package A109.TikTagTalk.domain.tag.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class CheckMemberTagRequestDto {
    private Long accountId;
    private String yearAndMonth;
}
