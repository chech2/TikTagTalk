package A109.TikTagTalk.domain.user.exception.custom;

import A109.TikTagTalk.domain.user.exception.ExceptionCode;

public class NoSuchUserException extends RuntimeException{

    public NoSuchUserException() {
        super(ExceptionCode.NO_SUCH_USER_EXCEPTION.getErrorMessage());
    }

    public NoSuchUserException(String message) {
        super(message);
    }
}
