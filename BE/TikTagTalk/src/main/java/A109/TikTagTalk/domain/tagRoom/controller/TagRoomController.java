package A109.TikTagTalk.domain.tagRoom.controller;

import A109.TikTagTalk.domain.tagRoom.dto.response.TagRoomResponseDto;
import A109.TikTagTalk.domain.tagRoom.service.TagRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tagroom")
@Slf4j
public class TagRoomController {
    private final TagRoomService tagRoomService;

    @GetMapping("/{tagRoomId}")
    public TagRoomResponseDto findTagRoomOwner(@PathVariable Long tagRoomId){
        return tagRoomService.findTagRoomOwner(tagRoomId);
    }
}
