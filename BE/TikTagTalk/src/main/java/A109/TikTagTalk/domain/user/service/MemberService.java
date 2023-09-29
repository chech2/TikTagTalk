package A109.TikTagTalk.domain.user.service;

import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.entity.ConsumeHistory;
import A109.TikTagTalk.domain.account.repository.AccountRepository;
import A109.TikTagTalk.domain.account.repository.ConsumeHistoryRepository;
import A109.TikTagTalk.domain.tagRoom.entity.TagRoom;
import A109.TikTagTalk.domain.tagRoom.repository.TagRoomRepository;
import A109.TikTagTalk.domain.user.dto.request.MemberOAuthSignUpDto;
import A109.TikTagTalk.domain.user.dto.request.MemberSignUpDto;
import A109.TikTagTalk.domain.user.dto.response.FindMemberResponseDto;
import A109.TikTagTalk.domain.user.dto.response.MemberLoginResponseDTO;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.entity.Role;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import A109.TikTagTalk.global.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final ConsumeHistoryRepository consumeHistoryRepository;
    private final PasswordEncoder passwordEncoder; // SecurityCongfig에서 Bean 등록해줌
    private final JwtService jwtService;
    private final TagRoomRepository tagRoomRepository;
    public void singUp(MemberSignUpDto memberSignUpDto) throws Exception{

        if(memberRepository.findByUserId(memberSignUpDto.getUserId()).isPresent()) {
            throw new Exception("이미 존재하는 회원 아이디입니다.");
        }

        /*
        * 자체 로그인이기 때문에 클라이언트 요청에서 추가 정보까지 다 받아와서
        * Builder로 추가 정보를 포함한 User Entity 생성 후 DB에 저장
        */
        //계좌번호 1002xxxxxxxxx로 랜덤 생성
        Random random = new Random();
        Long randomAccountNumber = 1002L * 1_000_000_000L + random.nextInt(900_000_000);
        Account account=Account.builder()
                .accountNumber(randomAccountNumber).build();
        accountRepository.save(account);

        //accountId가 1~10인 애들은 더미 데이터. 더미 데이터의 consumeHistory를 새로운 account에 복사
        Long min = 1L;
        Long max = 10L;
        long range = max - min + 1;
        Long dummyAccountId = (long) (random.nextDouble() * range + min);
        Account dummyAccount=accountRepository.findById(dummyAccountId).get();

        List<ConsumeHistory> copyConsumeHistoryList=consumeHistoryRepository.copyAllConsumeHistory(dummyAccount);
        List<ConsumeHistory> savedHistories = copyConsumeHistoryList.stream()
                .map(copyConsumeHistory -> {
                    return consumeHistoryRepository.save(ConsumeHistory.builder()
                            .tag(copyConsumeHistory.getTag())
                            .consumeTime(copyConsumeHistory.getConsumeTime())
                            .storeName(copyConsumeHistory.getStoreName())
                            .isManual(copyConsumeHistory.getIsManual())
                            .store(copyConsumeHistory.getStore())
                            .detail(copyConsumeHistory.getDetail())
                            .amount(copyConsumeHistory.getAmount())
                            .account(account)
                            .build());
                })
                .collect(Collectors.toList());

        Member member = Member.builder()
                .userId(memberSignUpDto.getUserId())
                .password(passwordEncoder.encode(memberSignUpDto.getPassword()))
                .name(memberSignUpDto.getName())
                .introduction(memberSignUpDto.getIntroduction())
                .avatarType(memberSignUpDto.getAvatarType())
                .role(Role.USER)
                .attendance(1)
                .coin(0)
                .point(0)
                .account(account)
                .build();
        TagRoom tagRoom=TagRoom.builder()
                .account(account)
                .member(member)
                .build();
        tagRoomRepository.save(tagRoom);
        memberRepository.save(member);
    }

    public MemberLoginResponseDTO oauthSignUp(HttpServletResponse response, Member member, MemberOAuthSignUpDto memberOAuthSignUpDto) throws Exception {

        // 받은 정보로 수정
        member.setUserId(memberOAuthSignUpDto.getUserId());
        member.setName(memberOAuthSignUpDto.getName());
        member.setIntroduction(memberOAuthSignUpDto.getIntroduction());
        member.setAvatarType(memberOAuthSignUpDto.getAvatarType());
        member.setRole(Role.USER);
        memberRepository.save(member);

        // accessToken과 refreshToken 재발급 후 보내주기
        String accessToken = jwtService.createAccessToken(member.getUserId());
        String refreshToken = jwtService.createRefreshToken();

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(member.getUserId(), refreshToken);

        return MemberLoginResponseDTO.toDTO(member);
    }

    public MemberLoginResponseDTO oauthLoginSuccess(HttpServletResponse response, Member member) {

        // accessToken과 refreshToken 재발급 후 보내주기
        String accessToken = jwtService.createAccessToken(member.getUserId());
        String refreshToken = jwtService.createRefreshToken();

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(member.getUserId(), refreshToken);

        return MemberLoginResponseDTO.toDTO(member);
    }

    public List<FindMemberResponseDto> findMemberByUserId(Long loginMemberId, String userId) {

        List<Member> bySubUserId = memberRepository.findBySubUserId(userId);

        List<FindMemberResponseDto> response = new ArrayList<>();
        for (Member member : bySubUserId) {
            log.info("member.userId={}", member.getUserId());
            if(member.getId() != loginMemberId) response.add(FindMemberResponseDto.toDTO(member));
        }
        return response;
    }

    public List<FindMemberResponseDto> recommendMemberList(Member member) {

        // 일단 모든 멤버 리스트 반환
        List<Member> all = memberRepository.findAll();
        List<FindMemberResponseDto> response = new ArrayList<>();
        for (Member m : all) {
            response.add(FindMemberResponseDto.toDTO(m));
        }
        return response;
    }
}
