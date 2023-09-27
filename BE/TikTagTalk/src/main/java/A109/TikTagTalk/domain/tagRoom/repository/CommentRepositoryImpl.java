package A109.TikTagTalk.domain.tagRoom.repository;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseUtil;
import A109.TikTagTalk.domain.tagRoom.entity.Comment;
import A109.TikTagTalk.domain.tagRoom.entity.QComment;
import A109.TikTagTalk.domain.user.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private QComment comment=new QComment("comment");
    @Override
    public List<Comment> allComments(Long tagRoomId) {
        List<Comment> commentList=queryFactory
                .selectFrom(comment)
                .where(comment.tagRoom.id.eq(tagRoomId))
                .fetch();
        return commentList;
    }

    @Override
    public Member findMemberByCommentId(Long commentId) {
        return queryFactory.select(comment.member)
                .from(comment)
                .where(comment.id.eq(commentId))
                .fetchOne();
    }

    @Override
    public ResponseDto modifyComment(Comment newComment) {
        queryFactory.update(comment)
                .set(comment.content,newComment.getContent())
                .set(comment.writtenTime,newComment.getWrittenTime())
                .where(comment.id.eq(newComment.getId()))
                .execute();
        return ResponseUtil.Success("댓글 수정 성공");
    }
}
