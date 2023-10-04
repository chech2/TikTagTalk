package A109.TikTagTalk.domain.debt.service;

import A109.TikTagTalk.domain.debt.dto.request.DebtRequestDto.PatchApprovalReqestDto;
import A109.TikTagTalk.domain.debt.dto.request.DebtRequestDto.PatchExtendReqestApprovedDto;
import A109.TikTagTalk.domain.debt.dto.request.DebtRequestDto.PatchExtendRequestDto;
import A109.TikTagTalk.domain.debt.dto.request.DebtRequestDto.PatchRepaymentReqestDto;
import A109.TikTagTalk.domain.debt.dto.request.DebtRequestDto.PostRequestDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.GetListResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.GetResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PatchApprovalResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PatchExtendResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PatchRepaymentResponseDto;
import A109.TikTagTalk.domain.debt.dto.response.DebtResponseDto.PostResponseDto;
import A109.TikTagTalk.domain.debt.pagination.MultiResponseDto;

public interface DebtService {

    PostResponseDto createDebt(PostRequestDto postRequestDto);

    MultiResponseDto<GetListResponseDto> findDebtList(Integer mode, Integer page, Integer size);

    GetResponseDto findDebt(Long id);

    PatchExtendResponseDto patchRequestExtendDebt(PatchExtendRequestDto patchExtendRequestDto);

    //차용증 상환일자 수정 요청 승인
    PatchExtendResponseDto patchResponseExtendDebt(PatchExtendReqestApprovedDto patchExtendReqestApprovedDto);

    //금액 상환
    PatchRepaymentResponseDto patchRepaymentDebt(PatchRepaymentReqestDto patchRepaymentReqestDto);

    //차용증 승인 여부
    PatchApprovalResponseDto patchApprovalDesbt(PatchApprovalReqestDto patchApprovalReqestDto);


}