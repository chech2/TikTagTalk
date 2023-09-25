package A109.TikTagTalk.domain.user.exception.custom;

import A109.TikTagTalk.domain.user.exception.ExceptionCode;

public class AlreadySentRequestException extends RuntimeException {

    public AlreadySentRequestException() {
        super(ExceptionCode.ALREADY_SENT_REQUEST.getErrorMessage());
    }

    public AlreadySentRequestException(String message) {
        super(message);
    }
}
