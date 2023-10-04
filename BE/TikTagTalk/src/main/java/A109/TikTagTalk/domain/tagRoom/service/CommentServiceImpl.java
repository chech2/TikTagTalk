package A109.TikTagTalk.domain.tagRoom.service;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseUtil;
import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.exception.NotExistException;
import A109.TikTagTalk.domain.account.repository.AccountRepository;
import A109.TikTagTalk.domain.tagRoom.dto.request.InsertCommentRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.ModifyCommentRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.response.AllCommentsResponseDto;
import A109.TikTagTalk.domain.tagRoom.entity.Comment;
import A109.TikTagTalk.domain.tagRoom.entity.TagRoom;
import A109.TikTagTalk.domain.tagRoom.exception.CustomAccessDeniedException;
import A109.TikTagTalk.domain.tagRoom.repository.CommentRepository;
import A109.TikTagTalk.domain.tagRoom.repository.TagRoomRepository;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final TagRoomRepository tagRoomRepository;
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public ResponseDto insertComment(InsertCommentRequestDto requestDto, Member loginMember) {
        TagRoom tagRoom=tagRoomRepository.findById(requestDto.getTagRoom().getId()).get();
        Member member=memberRepository.findById(loginMember.getId()).get();
        Comment comment=Comment.builder()
                .member(member)
                .tagRoom(tagRoom)
                .content(requestDto.getContent())
                .writtenTime(LocalDateTime.now())
                .build();
        commentRepository.save(comment);
        comment.mappingMemberAndTagRoomAndComment(member,tagRoom);

        return ResponseUtil.Success("댓글 삽입 성공");
    }

    @Override
    @Transactional(readOnly = true)
    public List<AllCommentsResponseDto> allComments(Long tagRoomId) {
        List<Comment> commentList=commentRepository.allComments(tagRoomId);
        return commentList.stream()
                .map(comment -> {
                    Member writer=memberRepository.findById(comment.getMember().getId()).get();
                    AllCommentsResponseDto.TagRoomDto tagRoomDto=AllCommentsResponseDto.TagRoomDto.builder()
                            .id(comment.getTagRoom().getId()).build();
                    AllCommentsResponseDto.MemberDto memberDto=AllCommentsResponseDto.MemberDto.builder()
                            .id(comment.getMember().getId())
                            .avatarType(writer.getAvatarType())
                            .name(comment.getMember().getName()).build();

                    return AllCommentsResponseDto.builder()
                            .id(comment.getId())
                            .tagRoom(tagRoomDto)
                            .member(memberDto)
                            .writtenTime(comment.getWrittenTime())
                            .content(comment.getContent())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseDto modifyComment(ModifyCommentRequestDto requestDto, Member member) {

        Member writer=commentRepository.findMemberByCommentId(requestDto.getId());
        if(member.getId()!=writer.getId()){
            throw new CustomAccessDeniedException(HttpStatus.SC_FORBIDDEN,"권한이 없습니다.");
        }
        Comment comment=Comment.builder()
                .id(requestDto.getId())
                .writtenTime(LocalDateTime.now())
                .content(requestDto.getContent())
                .build();
        return commentRepository.modifyComment(comment);
    }

    @Override
    @Transactional
    public ResponseDto deleteComment(Long commentId, Member member) {
        if(!(commentRepository.findById(commentId).isPresent())){
            throw new NotExistException(HttpStatus.SC_INTERNAL_SERVER_ERROR,"해당 comment을 찾을 수 없습니다.");
        }
        Comment comment=commentRepository.findById(commentId).get();
        System.out.println("commentRepository : "+commentRepository.findById(commentId)+"!!!");
        if(comment==null){
            throw new NotExistException(HttpStatus.SC_INTERNAL_SERVER_ERROR,"해당 comment을 찾을 수 없습니다.");
        }
        Member writer=comment.getMember();
        if(member.getId()!=writer.getId()){
            throw new CustomAccessDeniedException(HttpStatus.SC_FORBIDDEN,"권한이 없습니다.");
        }
        commentRepository.delete(comment);
        return ResponseUtil.Success("댓글 삭제 성공");
    }
}
