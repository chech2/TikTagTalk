package A109.TikTagTalk.domain.tagRoom.repository;

import A109.TikTagTalk.domain.tagRoom.entity.Comment;

import java.util.List;

public interface CommentRepositoryCustom{
    List<Comment> allComments(Long tagRoomId);
}
