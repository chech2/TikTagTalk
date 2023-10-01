package A109.TikTagTalk.domain.user.exception.custom;

import A109.TikTagTalk.domain.user.exception.ExceptionCode;

public class DuplicateUserIdException extends RuntimeException{

    public DuplicateUserIdException() {
        super(ExceptionCode.DUPLICATE_USERID.getErrorMessage());
    }

    public DuplicateUserIdException(String message) {
        super(message);
    }
}
