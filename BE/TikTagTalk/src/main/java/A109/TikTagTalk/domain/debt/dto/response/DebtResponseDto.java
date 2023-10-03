package A109.TikTagTalk.domain.debt.dto.response;

import A109.TikTagTalk.domain.debt.entity.ExtendHistory;
import A109.TikTagTalk.domain.debt.entity.RepaymentHistory;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DebtResponseDto {
    private PostResponseDto postDebtDto;
    private GetListResponseDto getListDebtDto;
    private GetResponseDto getDebtDto;
    private PatchExtendResponseDto patchExtendResponseDto;

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class PostResponseDto{

        //차용증 ID
        private long id;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class GetListResponseDto{
        //차용증 ID
        private long id;
        private String status;
        private String debtorName;
        private String lenderName;
        private long remainingMoney;
    }


    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class GetResponseDto{
        //차용증 ID
        private long id;
        private String status;
        private String debtorName;
        private String lenderName;
        private String money;
        private long remainingMoney;
        private int partialPay;
        private LocalDate createTime;
        private LocalDate repaymentTime;
        private List<RepaymentHistory> repaymentHistoryList;
        private List<ExtendHistory> extendHistoryList;
    }

//    @Getter
//    @Builder
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    @AllArgsConstructor
//    public static class PatchExtendResponseDto{
//        //차용증 ID
//        private long id;
//        private String status;
//        private String debtorName;
//        private String lenderName;
//        private String money;
//        private long remainingMoney;
//        private int partialPay;
//        private LocalDate createTime;
//        private LocalDate repaymentTime;
//    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class PatchExtendResponseDto {
        private long id;
        private String status;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class PatchRepaymentResponseDto {
        private long id;
        private long money;
        private long remainingMoney;
        private String lenderName;
        private String status;
    }
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class PatchApprovalResponseDto {
        private long id;
        private String status;
    }


///(debt) id:
//repaymentDate : datetime
}
