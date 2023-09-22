package A109.TikTagTalk.domain.user.exception;

import A109.TikTagTalk.domain.user.exception.custom.ExpriedRefreshTokenException;
import A109.TikTagTalk.domain.user.exception.custom.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice("A109.TikTagTalk")
public class ExceptionController {

    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<String> handleNoSuchUserException (NoSuchUserException err) {
        return getResponseEntity(ExceptionCode.NO_SUCH_USER_EXCEPTION);
    }

    @ExceptionHandler(ExpriedRefreshTokenException.class)
    public ResponseEntity<String> handleExpiredRefreshTokenException (ExpriedRefreshTokenException err) {
        return getResponseEntity(ExceptionCode.EXPIRED_REFRESH_TOKEN_EXCEPTION);
    }

    public static ResponseEntity<String> getResponseEntity(ExceptionCode code){
        return ResponseEntity
                .status(code.getErrorCode())
                .body(code.getErrorMessage());
    }
}
