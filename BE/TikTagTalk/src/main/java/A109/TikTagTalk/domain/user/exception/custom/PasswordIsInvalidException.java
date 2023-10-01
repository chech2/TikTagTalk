package A109.TikTagTalk.domain.user.exception.custom;

import A109.TikTagTalk.domain.user.exception.ExceptionCode;

public class PasswordIsInvalidException extends RuntimeException{

    public PasswordIsInvalidException() {
        super(ExceptionCode.PASSWORD_IS_INVALID.getErrorMessage());
    }

    public PasswordIsInvalidException(String message) {
        super(message);
    }
}
