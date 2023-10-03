package A109.TikTagTalk.domain.debt.exceptions.business;

import A109.TikTagTalk.domain.debt.exceptions.codes.ExceptionCode;
import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException{

    private ExceptionCode exceptionCode;
    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }


}
