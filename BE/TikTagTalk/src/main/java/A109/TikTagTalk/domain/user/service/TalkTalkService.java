package A109.TikTagTalk.domain.user.service;

import A109.TikTagTalk.domain.user.dto.response.FindTalkTalkListResponseDto;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.entity.TalkTalk;
import A109.TikTagTalk.domain.user.entity.TalkTalkStatus;
import A109.TikTagTalk.domain.user.exception.custom.*;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import A109.TikTagTalk.domain.user.repository.TalkTalkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TalkTalkService {

    private final MemberRepository memberRepository;
    private final TalkTalkRepository talkTalkRepository;

    @Transactional
    public void talkTalkRequest(Member member1, Long member2Id) {

        // 본인에게 톡톡 요청을 보낸 경우 예외처리
        if(member1.getId() == member2Id) {
            throw new SendTalktalkRequestYourself();
        }

        // 신청하려는 member가 없는 멤버라면 Exception 발생
        Member member2 = memberRepository.findById(member2Id).orElseThrow(() -> new NoSuchUserException());

        log.info("member1 id={}", member1.getId());
        log.info("member2 id={}", member2.getId());

        // 예외처리
        Optional<TalkTalk> optionalTalkTalk = talkTalkRepository.findBySenderIdAndReceiverId(member1.getId(), member2.getId());

        if(optionalTalkTalk.isPresent()) {
            TalkTalk talkTalk = optionalTalkTalk.get();

            if(talkTalk.getStatus().equals(TalkTalkStatus.REQUESTING)) { // 이미 보낸 요청이라면 455 에러
                throw new AlreadySentRequestException();
            } else if(talkTalk.getStatus().equals(TalkTalkStatus.TALK_TALK)) { // 이미 톡톡 관계라면 454 에러
                throw new AlreadyExistingTalkTalkException();
            }
        }

        optionalTalkTalk = talkTalkRepository.findBySenderIdAndReceiverId(member2.getId(), member1.getId());

        if(optionalTalkTalk.isPresent()) {
            TalkTalk talkTalk = optionalTalkTalk.get();

            if(talkTalk.getStatus().equals(TalkTalkStatus.REQUESTING)) { // 상대방이 이미 요청을 보냈다면 456 에러
                throw new OtherPartyAlreadySentRequestException();
            } else if(talkTalk.getStatus().equals(TalkTalkStatus.TALK_TALK)) { // 이미 톡톡 관계라면 454 에러
                throw new AlreadyExistingTalkTalkException();
            }
        }

        // 톡톡 신청 보내기
        TalkTalk talkTalk = TalkTalk.builder()
                .sender(member1)
                .receiver(member2)
                .status(TalkTalkStatus.REQUESTING)
                .build();

        talkTalkRepository.save(talkTalk);
    }

    @Transactional
    public void agreeRequest(Member loginMember, Long id) {

        // talktalk 관계 찾아오기(해당 talktalk이 없다면 에러)
        TalkTalk talkTalk = talkTalkRepository.findById(id).orElseThrow(() -> new NotExistRequestException());

        // 내가 받은 요청이 아니라면 요청을 수락할 권한이 없음
        if(talkTalk.getReceiver().getId() != loginMember.getId()) {
            throw new DoNotHavePremissionException();
        }

        // 이미 톡톡 친구 관계라면 예외 발생
        if(talkTalk.getStatus() == TalkTalkStatus.TALK_TALK) {
            throw new AlreadyExistingTalkTalkException();
        }

        // 톡톡 친구 수락
        talkTalk.setStatus(TalkTalkStatus.TALK_TALK);
    }

    public List<FindTalkTalkListResponseDto> findTalkTalkList(Member loginMember) {

        List<FindTalkTalkListResponseDto> response = new ArrayList<>();

        List<TalkTalk> talkTalkList = talkTalkRepository.findBySenderIdAndReceiverId(loginMember.getId());
        for (TalkTalk talktalk : talkTalkList) {
            if(talktalk.getSender().getId() == loginMember.getId()) { // loginMember가 sender인 경우
                response.add(FindTalkTalkListResponseDto.toDTO(talktalk.getId(), talktalk.getStatus(), true, talktalk.getReceiver()));
            } else { // loginMember가 receiver인 경우
                response.add(FindTalkTalkListResponseDto.toDTO(talktalk.getId(), talktalk.getStatus(), false, talktalk.getSender()));
            }
        }

        return response;
    }
}
