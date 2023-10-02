package A109.TikTagTalk.domain.account.exception;

import lombok.Getter;

@Getter
public class NotExistException extends RuntimeException{
    private int statusCode;
    public NotExistException(int statusCode, String errorMessage){
        super(errorMessage);
        this.statusCode=statusCode;
    }
}
