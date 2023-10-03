package A109.TikTagTalk.domain.debt.mapper;

import A109.TikTagTalk.domain.debt.dto.request.DebtRequestDto.PatchExtendRequestDto;
import A109.TikTagTalk.domain.debt.dto.request.DebtRequestDto.PostRequestDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.GetListResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.GetResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PatchApprovalResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PatchExtendResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PatchRepaymentResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PostResponseDto;
import A109.TikTagTalk.domain.debt.entity.Debt;
import A109.TikTagTalk.domain.debt.entity.ExtendHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DebtMapper {
    // 차용증 생성 (Debt -> PostResponseDto)
    PostResponseDto DebtToPostResponseDto(Debt debt);

    // 차용증 생성 (PostRequestDto -> Debt)
    Debt PostRequestDtoToDebt(PostRequestDto postRequestDto);

    GetListResponseDto DebtToGetListResponseDto(Debt debt, String debtorName, String lenderName);
    GetResponseDto DebtToGetResponseDto(Debt debt, String debtorName, String lenderName);

    ExtendHistory PatchExtendRequestDtoToExtendHistory(PatchExtendRequestDto patchExtendRequestDto);

    PatchExtendResponseDto PatchExtendHistoryToExtendResponseDto(ExtendHistory extendHistory);

    PatchRepaymentResponseDto PathRepaymentResponseDtoSetting(Debt debt, String lenderName);

    PatchApprovalResponseDto debtToPatchApprovalResponseDto(Debt debt);
}
