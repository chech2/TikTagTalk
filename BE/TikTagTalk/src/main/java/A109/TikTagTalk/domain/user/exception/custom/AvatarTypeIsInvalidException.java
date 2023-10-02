package A109.TikTagTalk.domain.user.exception.custom;

import A109.TikTagTalk.domain.user.exception.ExceptionCode;

public class AvatarTypeIsInvalidException extends RuntimeException{

    public AvatarTypeIsInvalidException() {
        super(ExceptionCode.AVATARTYPE_IS_INVALID.getErrorMessage());
    }

    public AvatarTypeIsInvalidException(String message) {
        super(message);
    }
}
