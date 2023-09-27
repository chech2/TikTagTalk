package A109.TikTagTalk.domain.user.exception.custom;

import A109.TikTagTalk.domain.user.exception.ExceptionCode;

public class SendTalktalkRequestYourself extends RuntimeException{

    public SendTalktalkRequestYourself() {
        super(ExceptionCode.SEND_TALKTALK_REQUEST_YOURSELF.getErrorMessage());
    }

    public SendTalktalkRequestYourself(String message) {
        super(message);
    }
}
