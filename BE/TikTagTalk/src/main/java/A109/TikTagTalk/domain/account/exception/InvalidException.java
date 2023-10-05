package A109.TikTagTalk.domain.account.exception;

import lombok.Getter;

@Getter
public class InvalidException extends Exception{
    private int statusCode;
    public InvalidException(int statusCode,String errorMessage){
        super(errorMessage);
        this.statusCode=statusCode;
    }
}
