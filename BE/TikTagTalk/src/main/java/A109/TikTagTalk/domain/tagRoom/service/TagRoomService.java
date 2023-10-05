package A109.TikTagTalk.domain.tagRoom.service;

import A109.TikTagTalk.domain.tagRoom.dto.response.TagRoomResponseDto;
import A109.TikTagTalk.domain.tagRoom.entity.TagRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface TagRoomService {
    TagRoomResponseDto findTagRoomOwner(Long tagRoomId);
}
