package A109.TikTagTalk.domain.tagRoom.exception;

import lombok.Getter;
import org.springframework.security.access.AccessDeniedException;

@Getter
public class CustomAccessDeniedException extends AccessDeniedException {
    private int statusCode;
    public CustomAccessDeniedException(int statusCode, String errorMessage){
        super(errorMessage);
        this.statusCode=statusCode;
    }
}
