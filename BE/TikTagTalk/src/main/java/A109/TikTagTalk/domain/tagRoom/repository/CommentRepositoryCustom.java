package A109.TikTagTalk.domain.tagRoom.repository;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.tagRoom.entity.Comment;
import A109.TikTagTalk.domain.user.entity.Member;

import java.util.List;

public interface CommentRepositoryCustom{
    List<Comment> allComments(Long tagRoomId);
    Member findMemberByCommentId(Long commentId);

    ResponseDto modifyComment(Comment comment);
}
