package A109.TikTagTalk.domain.user.service;

import A109.TikTagTalk.domain.account.dto.request.ConsumeHistoryRequestDto;
import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.account.entity.ConsumeHistory;
import A109.TikTagTalk.domain.account.repository.AccountRepository;
import A109.TikTagTalk.domain.account.repository.ConsumeHistoryRepository;
import A109.TikTagTalk.domain.account.service.ConsumeHistoryService;
import A109.TikTagTalk.domain.tagRoom.entity.TagRoom;
import A109.TikTagTalk.domain.tagRoom.repository.TagRoomRepository;
import A109.TikTagTalk.domain.user.dto.request.CheckUserIdRequestDto;
import A109.TikTagTalk.domain.user.dto.request.MemberOAuthSignUpDto;
import A109.TikTagTalk.domain.user.dto.request.MemberSignUpDto;
import A109.TikTagTalk.domain.user.dto.response.FindMemberResponseDto;
import A109.TikTagTalk.domain.user.dto.response.MemberLoginResponseDTO;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.entity.Role;
import A109.TikTagTalk.domain.user.exception.custom.AvatarTypeIsInvalidException;
import A109.TikTagTalk.domain.user.exception.custom.DuplicateUserIdException;
import A109.TikTagTalk.domain.user.exception.custom.PasswordIsInvalidException;
import A109.TikTagTalk.domain.user.exception.custom.UserIdIsInvalidException;
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
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;
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
    private final ConsumeHistoryService consumeHistoryService;

    public void singUp(MemberSignUpDto memberSignUpDto) throws Exception{


        // 아이디 양식이 올바르지 않을 경우(5~20자의 영문 소문자, 숫자와 특수기호(_),(.))
        String regex = "^[a-z0-9_.]{5,20}$";
        Pattern pattern = Pattern.compile(regex);
        if(!pattern.matcher(memberSignUpDto.getUserId()).matches()) {
            throw new UserIdIsInvalidException();
        }
        // 아이디 중복일 경우
        if(memberRepository.findByUserId(memberSignUpDto.getUserId()).isPresent()) {
            throw new DuplicateUserIdException();
        }
        // 비밀번호 양식이 올바르지 않을 경우(영문 대/소문자, 숫자, 특수문자를 포함하는 8~16자의 문자열)
        regex = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d!@#$%^&*]{8,16}$";
        pattern = Pattern.compile(regex);
        if(!pattern.matcher(memberSignUpDto.getPassword()).matches()) {
            throw new PasswordIsInvalidException();
        }
        // avartar type 범위 내인지 확인
        if(memberSignUpDto.getAvatarType() < 1 || memberSignUpDto.getAvatarType() > 8) {
            throw new AvatarTypeIsInvalidException();
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
                .point(5000)
                .account(account)
                .build();
        TagRoom tagRoom=TagRoom.builder()
                .account(account)
                .member(member)
                .build();
        tagRoomRepository.save(tagRoom);
        memberRepository.save(member);

        //더미 데이터로 얻은 consumeHistory에서부터 tag 얻기
//        ConsumeHistoryRequestDto requestDto=ConsumeHistoryRequestDto.builder().yearAndMonth("2023-09").build();
//        consumeHistoryService.makeMemberTags(requestDto,member);
//        requestDto=ConsumeHistoryRequestDto.builder().yearAndMonth("2023-09").build();
//        consumeHistoryService.makeMemberTags(requestDto,member);
    }

    public void checkUserId(CheckUserIdRequestDto checkUserIdRequestDto) throws Exception {

        String checkUserId = checkUserIdRequestDto.getUserId();

        Optional<Member> byUserId = memberRepository.findByUserId(checkUserId);

        if(byUserId.isPresent() == true) { // 이미 사용 중인 userId라면
            throw new DuplicateUserIdException();
        }

        // 아니라면 return
    }

    public MemberLoginResponseDTO oauthSignUp(HttpServletResponse response, Member member, MemberOAuthSignUpDto memberOAuthSignUpDto) throws Exception {

        // 예외 처리
        // 아이디 양식이 올바르지 않을 경우(5~20자의 영문 소문자, 숫자와 특수기호(_),(.))
        String regex = "^[a-z0-9_.]{5,20}$";
        Pattern pattern = Pattern.compile(regex);
        if(!pattern.matcher(memberOAuthSignUpDto.getUserId()).matches()) {
            throw new UserIdIsInvalidException();
        }
        // 아이디 중복일 경우
        if(memberRepository.findByUserId(memberOAuthSignUpDto.getUserId()).isPresent()) {
            throw new DuplicateUserIdException();
        }
        // avartar type 범위 내인지 확인
        if(memberOAuthSignUpDto.getAvatarType() < 1 || memberOAuthSignUpDto.getAvatarType() > 8) {
            throw new AvatarTypeIsInvalidException();
        }

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

        // 받은 정보로 수정
        member.setUserId(memberOAuthSignUpDto.getUserId());
        member.setName(memberOAuthSignUpDto.getName());
        member.setIntroduction(memberOAuthSignUpDto.getIntroduction());
        member.setAvatarType(memberOAuthSignUpDto.getAvatarType());
        member.setRole(Role.USER);
        member.setAttendance(1);
        member.setPoint(5000);
        member.setCoin(0);
        member.setAccount(account);
        memberRepository.save(member);

        TagRoom tagRoom=TagRoom.builder()
                .account(account)
                .member(member)
                .build();
        tagRoomRepository.save(tagRoom);

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
