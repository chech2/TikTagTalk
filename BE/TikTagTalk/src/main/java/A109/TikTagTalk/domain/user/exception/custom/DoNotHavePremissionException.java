package A109.TikTagTalk.domain.user.exception.custom;

import A109.TikTagTalk.domain.user.exception.ExceptionCode;

public class DoNotHavePremissionException extends RuntimeException {

    public DoNotHavePremissionException() {
        super(ExceptionCode.DO_NOT_HAVE_PREMISSION.getErrorMessage());
    }

    public DoNotHavePremissionException(String message) {
        super(message);
    }
}
