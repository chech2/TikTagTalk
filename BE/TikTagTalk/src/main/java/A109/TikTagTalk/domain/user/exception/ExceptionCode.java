package A109.TikTagTalk.domain.user.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    NO_SUCH_USER_EXCEPTION(452, "존재하지 않는 회원입니다."),
    EXPIRED_REFRESH_TOKEN_EXCEPTION(453, "갱신 토큰이 만료되었습니다.");


    private final Integer errorCode;
    private final String errorMessage;

    ExceptionCode(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
