package A109.TikTagTalk.domain.tagRoom.service;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseUtil;
import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.repository.AccountRepository;
import A109.TikTagTalk.domain.tagRoom.dto.request.InsertCommentRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.response.AllCommentsResponseDto;
import A109.TikTagTalk.domain.tagRoom.entity.Comment;
import A109.TikTagTalk.domain.tagRoom.entity.TagRoom;
import A109.TikTagTalk.domain.tagRoom.repository.CommentRepository;
import A109.TikTagTalk.domain.tagRoom.repository.TagRoomRepository;
import A109.TikTagTalk.domain.user.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final TagRoomRepository tagRoomRepository;
    @Override
    @Transactional
    public ResponseDto insertComment(InsertCommentRequestDto requestDto, Member member) {
        TagRoom tagRoom=tagRoomRepository.findById(requestDto.getTagRoom().getId()).get();
        Comment comment=Comment.builder()
                .member(member)
                .tagRoom(tagRoom)
                .content(requestDto.getContent())
                .writtenTime(LocalDateTime.now())
                .build();
        comment.mappingMemberAndTagRoomAndComment(member,tagRoom);
        commentRepository.save(comment);
        return ResponseUtil.Success("댓글 삽입 성공");
    }

    @Override
    @Transactional(readOnly = true)
    public List<AllCommentsResponseDto> allComments(Long tagRoomId) {
        List<Comment> commentList=commentRepository.allComments(tagRoomId);
        return commentList.stream()
                .map(comment -> {
                    AllCommentsResponseDto.TagRoomDto tagRoomDto=AllCommentsResponseDto.TagRoomDto.builder()
                            .id(comment.getTagRoom().getId()).build();
                    AllCommentsResponseDto.MemberDto memberDto=AllCommentsResponseDto.MemberDto.builder()
                            .id(comment.getMember().getId()).build();
                    return AllCommentsResponseDto.builder()
                            .tagRoom(tagRoomDto)
                            .member(memberDto)
                            .writtenTime(comment.getWrittenTime())
                            .content(comment.getContent())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
