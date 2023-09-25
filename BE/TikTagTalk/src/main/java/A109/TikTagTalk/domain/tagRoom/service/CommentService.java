package A109.TikTagTalk.domain.tagRoom.service;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.InsertCommentRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.response.AllCommentsResponseDto;

import java.util.List;

public interface CommentService {
    ResponseDto insertComment(InsertCommentRequestDto requestDto);
    List<AllCommentsResponseDto> allComments(Long tagRoomId);
}
