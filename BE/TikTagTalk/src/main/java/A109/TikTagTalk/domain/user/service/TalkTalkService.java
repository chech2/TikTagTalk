package A109.TikTagTalk.domain.user.service;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.entity.TalkTalk;
import A109.TikTagTalk.domain.user.entity.TalkTalkStatus;
import A109.TikTagTalk.domain.user.exception.custom.AlreadyExistingTalkTalkException;
import A109.TikTagTalk.domain.user.exception.custom.AlreadySentRequestException;
import A109.TikTagTalk.domain.user.exception.custom.NoSuchUserException;
import A109.TikTagTalk.domain.user.exception.custom.OtherPartyAlreadySentRequestException;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import A109.TikTagTalk.domain.user.repository.TalkTalkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TalkTalkService {

    private final MemberRepository memberRepository;
    private final TalkTalkRepository talkTalkRepository;

    @Transactional
    public void talkTalkRequest(Member member1, Long member2Id) {

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
}
