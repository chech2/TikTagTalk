package A109.TikTagTalk.domain.user.exception.custom;

import A109.TikTagTalk.domain.user.exception.ExceptionCode;

public class OtherPartyAlreadySentRequestException extends RuntimeException{

    public OtherPartyAlreadySentRequestException() {
        super(ExceptionCode.OTHER_PARTY_ALREADY_SENT_REQUEST.getErrorMessage());
    }

    public OtherPartyAlreadySentRequestException(String message) {
        super(message);
    }
}
