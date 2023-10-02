package A109.TikTagTalk.domain.debt.dto.request;

import java.time.LocalDate;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class DebtRequestDto {
    private PostRequestDto debtDto;
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class PostRequestDto{
        //빌린 금액
        private long money;
        //채권자 ID
        private long lenderId;
        //상환일자
        private LocalDate repaymentTime;
        //부분 상환 가능 여부
        private int partialPay;

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class PatchExtendRequestDto{
        private long debtId;

        //상환일자
        private LocalDate extendTime;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class PatchExtendReqestApprovedDto{
        private long id;
        private long debtId;
        private String status;

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class PatchRepaymentReqestDto{
        private long id;
        private long repaymentMoney;
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public static class PatchApprovalReqestDto{
        private long id;
        private String status;

    }
}
