package A109.TikTagTalk.domain.user.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    NO_SUCH_USER_EXCEPTION(452, "존재하지 않는 회원입니다."),
    EXPIRED_REFRESH_TOKEN_EXCEPTION(453, "갱신 토큰이 만료되었습니다."),
    ALREADY_EXISTING_TALKTALK(454, "이미 톡톡 친구 관계입니다."),
    ALREADY_SENT_REQUEST(455, "이미 톡톡 친구 요청을 보냈습니다."),
    OTHER_PARTY_ALREADY_SENT_REQUEST(456, "상대방이 이미 톡톡 친구 요청을 보냈습니다."),
    NOT_EXIST_REQUEST(457, "존재하지 않는 톡톡 친구 요청입니다."),
    DO_NOT_HAVE_PREMISSION(458, "톡톡 친구 신청 수락 권한이 없습니다."),
    SEND_TALKTALK_REQUEST_YOURSELF(459, "본인에게 톡톡 친구 요청을 보낼 수 없습니다."),
    DUPLICATE_USERID(460, "이미 사용 중인 아이디입니다."),
    USERID_IS_INVALID(461, "아이디 양식이 잘못되었습니다."),
    PASSWORD_IS_INVALID(462, "비밀번호 양식이 잘못되었습니다."),
    AVATARTYPE_IS_INVALID(463, "프로필은 1~8 사이의 정수 값만 가능합니다.");


    private final Integer errorCode;
    private final String errorMessage;

    ExceptionCode(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
