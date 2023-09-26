package A109.TikTagTalk.domain.user.exception.custom;

import A109.TikTagTalk.domain.user.exception.ExceptionCode;

public class AlreadyExistingTalkTalkException extends RuntimeException {

    public AlreadyExistingTalkTalkException() {
        super(ExceptionCode.ALREADY_EXISTING_TALKTALK.getErrorMessage());
    }

    public AlreadyExistingTalkTalkException(String message) {
        super(message);
    }
}
