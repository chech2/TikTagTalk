package A109.TikTagTalk.domain.tagRoom.controller;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.InsertCommentRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.response.AllCommentsResponseDto;
import A109.TikTagTalk.domain.tagRoom.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public ResponseDto insertComment(@RequestBody InsertCommentRequestDto requestDto){
        return commentService.insertComment(requestDto);
    }
    @GetMapping("/{tagRoomId}")
    public List<AllCommentsResponseDto> allComments(@PathVariable Long tagRoomId){
        return commentService.allComments(tagRoomId);
    }
}