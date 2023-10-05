package A109.TikTagTalk.global.schedule;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@RequiredArgsConstructor
public class MemberScheduler {
    private final MemberRepository memberRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetBoolean(){
        List<Member> memberList = memberRepository.findAll();
        for(int i = 0; i < memberList.size(); i++){
            Member member = memberList.get(0);
            member.setPointsAddedToday(false);
        }

    }
}
