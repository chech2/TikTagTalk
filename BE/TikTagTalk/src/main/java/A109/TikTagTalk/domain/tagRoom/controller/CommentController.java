package A109.TikTagTalk.domain.tagRoom.controller;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseUtil;
import A109.TikTagTalk.domain.account.exception.NotExistException;
import A109.TikTagTalk.domain.tagRoom.dto.request.InsertCommentRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.ModifyCommentRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.response.AllCommentsResponseDto;
import A109.TikTagTalk.domain.tagRoom.exception.CustomAccessDeniedException;
import A109.TikTagTalk.domain.tagRoom.service.CommentService;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public ResponseDto insertComment(@RequestBody InsertCommentRequestDto requestDto){
        Member member= SecurityUtil.getCurrentLoginMember();
        return commentService.insertComment(requestDto,member);
    }
    @GetMapping("/{tagRoomId}")
    public List<AllCommentsResponseDto> allComments(@PathVariable Long tagRoomId){
        return commentService.allComments(tagRoomId);
    }
    @PutMapping("")
    public ResponseDto modifyComment(@RequestBody ModifyCommentRequestDto requestDto){
        Member member=SecurityUtil.getCurrentLoginMember();
        try {
            return commentService.modifyComment(requestDto, member);
        }catch(CustomAccessDeniedException ae){
            return ResponseUtil.Failure("권한이 없습니다");
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseDto deleteComment(@PathVariable Long commentId){
        Member member=SecurityUtil.getCurrentLoginMember();
        try {
            return commentService.deleteComment(commentId, member);
        }catch (CustomAccessDeniedException ae){
            return ResponseUtil.Failure("권한이 없습니다.");
        }catch(NotExistException ne){
            return ResponseUtil.Failure("해당 comment을 찾을 수 없습니다.");
        }
    }

}