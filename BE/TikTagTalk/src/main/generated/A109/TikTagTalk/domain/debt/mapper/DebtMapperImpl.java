package A109.TikTagTalk.domain.debt.mapper;

import A109.TikTagTalk.domain.debt.dto.request.DebtRequestDto.PatchExtendRequestDto;
import A109.TikTagTalk.domain.debt.dto.request.DebtRequestDto.PostRequestDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.GetListResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.GetListResponseDto.GetListResponseDtoBuilder;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.GetResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.GetResponseDto.GetResponseDtoBuilder;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PatchApprovalResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PatchApprovalResponseDto.PatchApprovalResponseDtoBuilder;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PatchExtendResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PatchExtendResponseDto.PatchExtendResponseDtoBuilder;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PatchRepaymentResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PatchRepaymentResponseDto.PatchRepaymentResponseDtoBuilder;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PostResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PostResponseDto.PostResponseDtoBuilder;
import A109.TikTagTalk.domain.debt.entity.Debt;
import A109.TikTagTalk.domain.debt.entity.Debt.DebtBuilder;
import A109.TikTagTalk.domain.debt.entity.ExtendHistory;
import A109.TikTagTalk.domain.debt.entity.ExtendHistory.ExtendHistoryBuilder;
import A109.TikTagTalk.domain.debt.entity.RepaymentHistory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-06T03:35:05+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class DebtMapperImpl implements DebtMapper {

    @Override
    public PostResponseDto DebtToPostResponseDto(Debt debt) {
        if ( debt == null ) {
            return null;
        }

        PostResponseDtoBuilder postResponseDto = PostResponseDto.builder();

        if ( debt.getId() != null ) {
            postResponseDto.id( debt.getId() );
        }

        return postResponseDto.build();
    }

    @Override
    public Debt PostRequestDtoToDebt(PostRequestDto postRequestDto) {
        if ( postRequestDto == null ) {
            return null;
        }

        DebtBuilder debt = Debt.builder();

        debt.money( postRequestDto.getMoney() );
        debt.repaymentTime( postRequestDto.getRepaymentTime() );
        debt.partialPay( postRequestDto.getPartialPay() );

        return debt.build();
    }

    @Override
    public GetListResponseDto DebtToGetListResponseDto(Debt debt, String debtorName, String lenderName) {
        if ( debt == null && debtorName == null && lenderName == null ) {
            return null;
        }

        GetListResponseDtoBuilder getListResponseDto = GetListResponseDto.builder();

        if ( debt != null ) {
            if ( debt.getId() != null ) {
                getListResponseDto.id( debt.getId() );
            }
            if ( debt.getStatus() != null ) {
                getListResponseDto.status( debt.getStatus().name() );
            }
            if ( debt.getRemainingMoney() != null ) {
                getListResponseDto.remainingMoney( debt.getRemainingMoney() );
            }
        }
        if ( debtorName != null ) {
            getListResponseDto.debtorName( debtorName );
        }
        if ( lenderName != null ) {
            getListResponseDto.lenderName( lenderName );
        }

        return getListResponseDto.build();
    }

    @Override
    public GetResponseDto DebtToGetResponseDto(Debt debt, String debtorName, String lenderName) {
        if ( debt == null && debtorName == null && lenderName == null ) {
            return null;
        }

        GetResponseDtoBuilder getResponseDto = GetResponseDto.builder();

        if ( debt != null ) {
            if ( debt.getId() != null ) {
                getResponseDto.id( debt.getId() );
            }
            if ( debt.getStatus() != null ) {
                getResponseDto.status( debt.getStatus().name() );
            }
            if ( debt.getMoney() != null ) {
                getResponseDto.money( String.valueOf( debt.getMoney() ) );
            }
            if ( debt.getRemainingMoney() != null ) {
                getResponseDto.remainingMoney( debt.getRemainingMoney() );
            }
            getResponseDto.partialPay( debt.getPartialPay() );
            getResponseDto.createTime( debt.getCreateTime() );
            getResponseDto.repaymentTime( debt.getRepaymentTime() );
            List<RepaymentHistory> list = debt.getRepaymentHistoryList();
            if ( list != null ) {
                getResponseDto.repaymentHistoryList( new ArrayList<RepaymentHistory>( list ) );
            }
            List<ExtendHistory> list1 = debt.getExtendHistoryList();
            if ( list1 != null ) {
                getResponseDto.extendHistoryList( new ArrayList<ExtendHistory>( list1 ) );
            }
        }
        if ( debtorName != null ) {
            getResponseDto.debtorName( debtorName );
        }
        if ( lenderName != null ) {
            getResponseDto.lenderName( lenderName );
        }

        return getResponseDto.build();
    }

    @Override
    public ExtendHistory PatchExtendRequestDtoToExtendHistory(PatchExtendRequestDto patchExtendRequestDto) {
        if ( patchExtendRequestDto == null ) {
            return null;
        }

        ExtendHistoryBuilder extendHistory = ExtendHistory.builder();

        extendHistory.extendTime( patchExtendRequestDto.getExtendTime() );

        return extendHistory.build();
    }

    @Override
    public PatchExtendResponseDto PatchExtendHistoryToExtendResponseDto(ExtendHistory extendHistory) {
        if ( extendHistory == null ) {
            return null;
        }

        PatchExtendResponseDtoBuilder patchExtendResponseDto = PatchExtendResponseDto.builder();

        if ( extendHistory.getId() != null ) {
            patchExtendResponseDto.id( extendHistory.getId() );
        }
        if ( extendHistory.getStatus() != null ) {
            patchExtendResponseDto.status( extendHistory.getStatus().name() );
        }

        return patchExtendResponseDto.build();
    }

    @Override
    public PatchRepaymentResponseDto PathRepaymentResponseDtoSetting(Debt debt, String lenderName) {
        if ( debt == null && lenderName == null ) {
            return null;
        }

        PatchRepaymentResponseDtoBuilder patchRepaymentResponseDto = PatchRepaymentResponseDto.builder();

        if ( debt != null ) {
            if ( debt.getId() != null ) {
                patchRepaymentResponseDto.id( debt.getId() );
            }
            if ( debt.getMoney() != null ) {
                patchRepaymentResponseDto.money( debt.getMoney() );
            }
            if ( debt.getRemainingMoney() != null ) {
                patchRepaymentResponseDto.remainingMoney( debt.getRemainingMoney() );
            }
            if ( debt.getStatus() != null ) {
                patchRepaymentResponseDto.status( debt.getStatus().name() );
            }
        }
        if ( lenderName != null ) {
            patchRepaymentResponseDto.lenderName( lenderName );
        }

        return patchRepaymentResponseDto.build();
    }

    @Override
    public PatchApprovalResponseDto debtToPatchApprovalResponseDto(Debt debt) {
        if ( debt == null ) {
            return null;
        }

        PatchApprovalResponseDtoBuilder patchApprovalResponseDto = PatchApprovalResponseDto.builder();

        if ( debt.getId() != null ) {
            patchApprovalResponseDto.id( debt.getId() );
        }
        if ( debt.getStatus() != null ) {
            patchApprovalResponseDto.status( debt.getStatus().name() );
        }

        return patchApprovalResponseDto.build();
    }
}
