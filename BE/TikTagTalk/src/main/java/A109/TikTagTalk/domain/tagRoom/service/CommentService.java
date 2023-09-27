package A109.TikTagTalk.domain.tagRoom.service;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.InsertCommentRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.ModifyCommentRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.response.AllCommentsResponseDto;
import A109.TikTagTalk.domain.user.entity.Member;

import java.util.List;

public interface CommentService {
    ResponseDto insertComment(InsertCommentRequestDto requestDto, Member member);
    List<AllCommentsResponseDto> allComments(Long tagRoomId);

    ResponseDto modifyComment(ModifyCommentRequestDto requestDto,Member member);

    ResponseDto deleteComment(Long commentId,Member member);
}
