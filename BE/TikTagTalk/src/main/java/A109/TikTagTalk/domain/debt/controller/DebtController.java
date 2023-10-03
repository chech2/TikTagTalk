package A109.TikTagTalk.domain.debt.controller;

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
import A109.TikTagTalk.domain.debt.exceptions.business.BusinessLogicException;
import A109.TikTagTalk.domain.debt.exceptions.dto.ErrorResponse;
import A109.TikTagTalk.domain.debt.pagination.MultiResponseDto;
import A109.TikTagTalk.domain.debt.service.DebtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j @Validated
@Tag(name="Debt Controller", description = "차용증 API")
@RequestMapping("/api/debts")
public class DebtController {
    private final DebtService debtService;

    //차용증 생성
    @PostMapping
    @Operation(summary = "차용증 생성", description = "로그인한 유저가 채무자로 차용증 생성")
    public ResponseEntity<PostResponseDto> postDebt(@Validated @RequestBody PostRequestDto postRequestDto){
        PostResponseDto response = debtService.createDebt(postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 차용증 목록 보기 (채권자용, 채무자용 나누기)
    @GetMapping("/list")
    @Operation(summary = "차용증 목록 조회", description = "mode = 0인 경우, 로그인 유저가 채권자인 차용증 목록 조회 & mode = 1인 경우, 로그인 유저가 채무자인 차용증 목록 조회 ")
    public ResponseEntity<MultiResponseDto<GetListResponseDto>> getDebtlist(@RequestParam @PositiveOrZero Integer mode,
            @RequestParam(value = "page", defaultValue = "1") @Positive Integer page,
            @RequestParam(value = "size", defaultValue = "10") @Positive Integer size){

        MultiResponseDto<GetListResponseDto> response = debtService.findDebtList(mode, page, size);
        return ResponseEntity.ok().body(response);
    }

    // 차용증 상세 조회, History 추가하기
    @GetMapping("detail")
    public ResponseEntity<GetResponseDto> getDebt(@RequestParam Long debtId){
        GetResponseDto response = debtService.findDebt(debtId);
        return ResponseEntity.ok().body(response);
    }

    //아래 3개의 경우 차용증 id값, 결과(status)만 보내줘도 될듯
    // 차용증 상환일자 수정 요청
    @PatchMapping("extend-request")
    public ResponseEntity<PatchExtendResponseDto> patchRequestExtendDebt(@Validated @RequestBody PatchExtendRequestDto patchExtendRequestDto){
        PatchExtendResponseDto response = debtService.patchRequestExtendDebt(patchExtendRequestDto);
        return ResponseEntity.ok().body(response);
    }

    //차용증 상환일자 수정 요청 승인
    @PatchMapping("extend-response")
    public ResponseEntity<PatchExtendResponseDto> patchResponseExtendDebt(@Validated @RequestBody PatchExtendReqestApprovedDto patchExtendReqestApprovedDto){
        PatchExtendResponseDto response = debtService.patchResponseExtendDebt(patchExtendReqestApprovedDto);
        return ResponseEntity.ok().body(response);
    }

    //차용증 상환
    @PatchMapping("repayment")
    public ResponseEntity<PatchRepaymentResponseDto> patchRepaymentDebt(@Validated @RequestBody
            PatchRepaymentReqestDto patchRepaymentReqestDto){
        PatchRepaymentResponseDto response = debtService.patchRepaymentDebt(patchRepaymentReqestDto);
        return ResponseEntity.ok().body(response);
    }


    // API 명세서에 없는 추가 API

    // 차용증 생성 승인
    @PatchMapping("debt-approval")
    public ResponseEntity<PatchApprovalResponseDto> patchApprovalDebt( @Validated @RequestBody PatchApprovalReqestDto patchApprovalReqestDto){
        PatchApprovalResponseDto response = debtService.patchApprovalDesbt(patchApprovalReqestDto);
        return ResponseEntity.ok().body(response);
    }

    // TODO 에러 핸들링 관련 작성

    //비지니스 로직 에러 핸들링
    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {
        final ErrorResponse response = ErrorResponse.of(e.getExceptionCode());

        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getExceptionCode()
                .getStatus()));
    }
}
