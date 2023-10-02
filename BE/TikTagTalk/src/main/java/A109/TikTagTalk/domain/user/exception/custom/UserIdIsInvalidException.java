package A109.TikTagTalk.domain.user.exception.custom;

import A109.TikTagTalk.domain.user.exception.ExceptionCode;

public class UserIdIsInvalidException extends RuntimeException{

    public UserIdIsInvalidException() {
        super(ExceptionCode.USERID_IS_INVALID.getErrorMessage());
    }

    public UserIdIsInvalidException(String message) {
        super(message);
    }
}
