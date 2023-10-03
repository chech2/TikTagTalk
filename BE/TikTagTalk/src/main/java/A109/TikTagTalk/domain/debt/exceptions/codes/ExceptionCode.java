package A109.TikTagTalk.domain.debt.exceptions.codes;

import lombok.Getter;

public enum ExceptionCode {
    DEBTOR_NOT_FOUND(404, "Debtor not found"),
    LENDER_NOT_FOUND(404, "Lender not found"),

    DEBT_NOT_FOUND(404, "Debt not found"),
    INACCESSIBLE_DEBTOR(401, "You are not the debtor"),
    INACCESSIBLE_LENDER(401, "You are not the lender"),
    PARTIALPAY_IMPOSSIBLE(401, "Partial repayment is not possible."),
    INACCESSIBLE_DEBT(401, "You do not have permission to read the debt"),
    EXTENDHISTORY_NOT_FOUND(404, "ExtendHistory not found"),
    WRONG_EXTENDHISTORY_STATUS(400, "ExtendHistoryStaus is wrong"),
    WRONG_DEBT_STATUS(400, "DebtStaus is wrong");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}

