package A109.TikTagTalk.domain.tagRoom.repository;

import A109.TikTagTalk.domain.tagRoom.entity.Comment;
import A109.TikTagTalk.domain.tagRoom.entity.QComment;
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
}
