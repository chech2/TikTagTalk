package A109.TikTagTalk.domain.user.exception.custom;

import A109.TikTagTalk.domain.user.exception.ExceptionCode;

public class NotExistRequestException extends RuntimeException{

    public NotExistRequestException() {
        super(ExceptionCode.NOT_EXIST_REQUEST.getErrorMessage());
    }
    public NotExistRequestException(String message) {
        super(message);
    }
}
