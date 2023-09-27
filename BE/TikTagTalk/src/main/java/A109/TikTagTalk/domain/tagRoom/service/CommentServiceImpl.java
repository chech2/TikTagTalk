package A109.TikTagTalk.domain.tagRoom.service;

import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseUtil;
import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.repository.AccountRepository;
import A109.TikTagTalk.domain.tagRoom.dto.request.InsertCommentRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.request.ModifyCommentRequestDto;
import A109.TikTagTalk.domain.tagRoom.dto.response.AllCommentsResponseDto;
import A109.TikTagTalk.domain.tagRoom.entity.Comment;
import A109.TikTagTalk.domain.tagRoom.entity.TagRoom;
import A109.TikTagTalk.domain.tagRoom.repository.CommentRepository;
import A109.TikTagTalk.domain.tagRoom.repository.TagRoomRepository;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
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

    @Override
    @Transactional
    public ResponseDto modifyComment(ModifyCommentRequestDto requestDto, Member member) {
        //현재 로그인된 member와 해당 comment 작성 member가 다르면 에러!!
        Member writer=commentRepository.findMemberByCommentId(requestDto.getId());
        if(member.getId()!=writer.getId()){
            return ResponseUtil.Failure("권한이 없습니다.");
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
        Comment comment=commentRepository.findById(commentId).get();
        if(comment==null){
            return ResponseUtil.Failure("댓글이 존재하지 않습니다.");
        }
        Member writer=comment.getMember();
        if(member.getId()!=writer.getId()){
            return ResponseUtil.Failure("권한이 없습니다.");
        }
        commentRepository.delete(comment);
        return ResponseUtil.Success("댓글 삭제 성공");
    }
}
