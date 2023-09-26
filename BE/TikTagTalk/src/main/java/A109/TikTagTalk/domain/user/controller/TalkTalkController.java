package A109.TikTagTalk.domain.user.controller;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.exception.ExceptionCode;
import A109.TikTagTalk.domain.user.exception.custom.AlreadyExistingTalkTalkException;
import A109.TikTagTalk.domain.user.exception.custom.AlreadySentRequestException;
import A109.TikTagTalk.domain.user.exception.custom.NoSuchUserException;
import A109.TikTagTalk.domain.user.exception.custom.OtherPartyAlreadySentRequestException;
import A109.TikTagTalk.domain.user.service.TalkTalkService;
import A109.TikTagTalk.global.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/talk-talks")
@Tag(name="TalkTalk Controller", description = "톡톡 친구 API")
public class TalkTalkController {

    private final TalkTalkService talkTalkService;

    @PostMapping
    @Operation(summary = "talk-talk request", description = "톡톡 친구 요청")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "452", description = "존재하지 않는 사용자입니다."),
            @ApiResponse(responseCode = "454", description = "이미 톡톡 친구 관계입니다."),
            @ApiResponse(responseCode = "455", description = "이미 요청을 보냈습니다."),
            @ApiResponse(responseCode = "456", description = "상대방이 이미 요청을 보냈습니다.")
    })
    public ResponseEntity<String> talkTalkRequest(@RequestBody Map<String, Long> request) {

        Member member = SecurityUtil.getCurrentLoginMember();

        try {
            talkTalkService.talkTalkRequest(member, request.get("memberId"));
            return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
        } catch (NoSuchUserException e) {
            return new ResponseEntity<>(ExceptionCode.NO_SUCH_USER_EXCEPTION.getErrorMessage(), HttpStatusCode.valueOf(ExceptionCode.NO_SUCH_USER_EXCEPTION.getErrorCode()));
        } catch (AlreadyExistingTalkTalkException e) {
            return new ResponseEntity<>(ExceptionCode.ALREADY_EXISTING_TALKTALK.getErrorMessage(), HttpStatusCode.valueOf(ExceptionCode.ALREADY_EXISTING_TALKTALK.getErrorCode()));
        } catch (AlreadySentRequestException e) {
            return new ResponseEntity<>(ExceptionCode.ALREADY_SENT_REQUEST.getErrorMessage(), HttpStatusCode.valueOf(ExceptionCode.ALREADY_SENT_REQUEST.getErrorCode()));
        } catch (OtherPartyAlreadySentRequestException e) {
            return new ResponseEntity<>(ExceptionCode.OTHER_PARTY_ALREADY_SENT_REQUEST.getErrorMessage(), HttpStatusCode.valueOf(ExceptionCode.OTHER_PARTY_ALREADY_SENT_REQUEST.getErrorCode()));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "agree to talk-talk request", description = "톡톡 친구 요청 수락")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "457", description = "존재하지 않는 요청입니다."),
            @ApiResponse(responseCode = "458", description = "권한이 없습니다."),
            @ApiResponse(responseCode = "454", description = "이미 톡톡 친구 관계입니다.")
    })
    public ResponseEntity<String> agreeRequest(@PathVariable(name = "id") Long id) {
        return null;
    }
}
