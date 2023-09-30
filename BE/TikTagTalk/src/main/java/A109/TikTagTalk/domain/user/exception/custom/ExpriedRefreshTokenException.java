package A109.TikTagTalk.domain.user.exception.custom;

import A109.TikTagTalk.domain.user.exception.ExceptionCode;

public class ExpriedRefreshTokenException extends RuntimeException {

    public ExpriedRefreshTokenException() {
        super(ExceptionCode.EXPIRED_REFRESH_TOKEN_EXCEPTION.getErrorMessage());
    }

    public ExpriedRefreshTokenException(String message) {
        super(message);
    }
}
