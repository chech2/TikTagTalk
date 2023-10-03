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
import A109.TikTagTalk.domain.debt.entity.Debt;
import A109.TikTagTalk.domain.debt.entity.Debt.DebtStatus;
import A109.TikTagTalk.domain.debt.entity.ExtendHistory;
import A109.TikTagTalk.domain.debt.entity.ExtendStatus;
import A109.TikTagTalk.domain.debt.entity.RepaymentHistory;
import A109.TikTagTalk.domain.debt.exceptions.business.BusinessLogicException;
import A109.TikTagTalk.domain.debt.exceptions.codes.ExceptionCode;
import A109.TikTagTalk.domain.debt.mapper.DebtMapper;
import A109.TikTagTalk.domain.debt.pagination.MultiResponseDto;
import A109.TikTagTalk.domain.debt.repository.DebtRepository;
import A109.TikTagTalk.domain.debt.repository.ExtendHistoryRepository;
import A109.TikTagTalk.domain.debt.repository.RepaymentHistoryRepository;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.repository.MemberRepository;
import A109.TikTagTalk.global.util.SecurityUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {
    private final DebtMapper debtMapper;
    private final DebtRepository debtRepository;
    private final MemberRepository memberRepository;
    private final ExtendHistoryRepository extendHistoryRepository;
    private final RepaymentHistoryRepository repaymentHistoryRepository;


    @Override
    @Transactional
    public PostResponseDto createDebt(PostRequestDto postRequestDto) {
        //현재 로그인한 유저이자 채무자
        Member debtor= SecurityUtil.getCurrentLoginMember();

        Member lender = findVerifiedLender(postRequestDto.getLenderId());

        Debt debt = debtMapper.PostRequestDtoToDebt(postRequestDto);
        debt.setStatus(DebtStatus.REQUESTING);
        debt.setDebtor(debtor);
        debt.setLender(lender);
        debt.setRemainingMoney(debt.getMoney());

        debtRepository.save(debt);
        PostResponseDto response = debtMapper.DebtToPostResponseDto(debt);
        return response;
    }

    @Override
    @Transactional
    public MultiResponseDto<GetListResponseDto> findDebtList(Integer mode, Integer page,
            Integer size) {
        Member member= SecurityUtil.getCurrentLoginMember();
        Page<Debt> debtPage;
        List<Debt> debts;
        List<GetListResponseDto> responses = new ArrayList<>();

        //로그인 유저가 채권자 -> lender
        if(mode == 0){
            debtPage = debtRepository.findAllByLenderId(member.getId(),
                    PageRequest.of(page - 1, size, Sort.by("id").descending()));
        }
        //로그인 유저가 채무자 -> debtor
        else{
            debtPage = debtRepository.findAllByDebtorId(member.getId(),
                    PageRequest.of(page - 1, size, Sort.by("id").descending()));
        }
        debts = debtPage.getContent();

        for (Debt d : debts) {

            //로그인 유저가 채권자 -> lender
            if(mode == 0) {
                Member debtor = findVerifiedDebtor(d.getDebtor().getId());
                responses.add(debtMapper.DebtToGetListResponseDto(d, debtor.getName(), member.getName()));

            }
            //로그인 유저가 채무자 -> debtor
            else{
                Member lender = findVerifiedLender(d.getLender().getId());
                responses.add(debtMapper.DebtToGetListResponseDto(d, member.getName(),
                        lender.getName()));

            }
        }
        return new MultiResponseDto<GetListResponseDto>(responses, debtPage);
    }

    @Override
    @Transactional
    public GetResponseDto findDebt(Long id) {
        Member member= SecurityUtil.getCurrentLoginMember();

        Debt debt = findVerifiedDebt(id);
        if(!debt.getLender().getId().equals(member.getId()) && !debt.getDebtor().getId().equals(member.getId()))
            throw new BusinessLogicException(ExceptionCode.INACCESSIBLE_DEBT);

        GetResponseDto response = debtMapper.DebtToGetResponseDto(debt, debt.getDebtor().getName(), debt.getLender().getName() );
        return response;
    }


    @Override
    @Transactional
    public PatchExtendResponseDto patchRequestExtendDebt(PatchExtendRequestDto patchExtendRequestDto) {
        Member member= SecurityUtil.getCurrentLoginMember();

        Debt debt = findVerifiedDebt(patchExtendRequestDto.getDebtId());

        if(member.getId() != debt.getDebtor().getId()) throw new BusinessLogicException(
                ExceptionCode.INACCESSIBLE_DEBTOR);
        ExtendHistory extendHistory = new ExtendHistory();

        extendHistory.setDebt(debt);
        extendHistory.setExtendTime(patchExtendRequestDto.getExtendTime());
        extendHistory.setStatus(ExtendStatus.REQUESTING);

        extendHistoryRepository.save(extendHistory);

        PatchExtendResponseDto response = debtMapper.PatchExtendHistoryToExtendResponseDto(extendHistory);

        return response;
    }
    //연장 요청에 대한 승인
    @Override
    @Transactional
    public PatchExtendResponseDto patchResponseExtendDebt(PatchExtendReqestApprovedDto patchExtendReqestApprovedDto) {
        Member member= SecurityUtil.getCurrentLoginMember();

        Debt debt = findVerifiedDebt(patchExtendReqestApprovedDto.getDebtId());

        if(member.getId() != debt.getLender().getId()) throw new BusinessLogicException(
                ExceptionCode.INACCESSIBLE_LENDER);
        checkExtendHistoryStatus(patchExtendReqestApprovedDto.getStatus());

        //해당 연장 요청 찾기
        ExtendHistory extendHistory = findVerifiedExtendHistory(patchExtendReqestApprovedDto.getId());
        extendHistory.setStatus(ExtendStatus.valueOf(patchExtendReqestApprovedDto.getStatus()));
        extendHistoryRepository.save(extendHistory);
        if(patchExtendReqestApprovedDto.getStatus().equals(ExtendStatus.APPROVED)){
            debt.setRepaymentTime(extendHistory.getExtendTime());
        }

        PatchExtendResponseDto response = debtMapper.PatchExtendHistoryToExtendResponseDto(extendHistory);

        return response;
    }

    @Override
    @Transactional
    public PatchRepaymentResponseDto patchRepaymentDebt(
            PatchRepaymentReqestDto patchRepaymentReqestDto) {
        Member member= SecurityUtil.getCurrentLoginMember();

        Debt debt = findVerifiedDebt(patchRepaymentReqestDto.getId());
        //채무자와 로그인 유저가 일치하는지 확인
        if(member.getId() != debt.getDebtor().getId()) throw new BusinessLogicException(ExceptionCode.INACCESSIBLE_DEBTOR);

        //부분상환 불가능
        if(debt.getPartialPay() == 0){
            if(debt.getMoney() != patchRepaymentReqestDto.getRepaymentMoney())  throw new BusinessLogicException(
                    ExceptionCode.PARTIALPAY_IMPOSSIBLE);
        }
        long money = debt.getRemainingMoney() - patchRepaymentReqestDto.getRepaymentMoney();
        debt.setRemainingMoney(money);

        RepaymentHistory repaymentHistory = new RepaymentHistory();
        repaymentHistory.setDebt(debt);
        repaymentHistory.setMoney(patchRepaymentReqestDto.getRepaymentMoney());
        repaymentHistoryRepository.save(repaymentHistory);

        PatchRepaymentResponseDto response = debtMapper.PathRepaymentResponseDtoSetting(debt, debt.getLender().getName());
        return response;
    }

    @Override
    @Transactional
    public PatchApprovalResponseDto patchApprovalDesbt(PatchApprovalReqestDto patchApprovalReqestDto) {
        Member member= SecurityUtil.getCurrentLoginMember();

        Debt debt = findVerifiedDebt(patchApprovalReqestDto.getId());

        if(member.getId() != debt.getLender().getId()) throw new BusinessLogicException(
                ExceptionCode.INACCESSIBLE_LENDER);
        checkDebtStatus(patchApprovalReqestDto.getStatus());
        debt.setStatus(DebtStatus.valueOf(patchApprovalReqestDto.getStatus()));
        PatchApprovalResponseDto response = debtMapper.debtToPatchApprovalResponseDto(debt);
        return response;

    }
    // TODO 여기서부터 유효성 검증 함수
    //채권자 유효성 검증
    public Member findVerifiedLender(Long id) {
        Member lender = memberRepository.findById(id).orElseThrow(() -> new BusinessLogicException(ExceptionCode.LENDER_NOT_FOUND));
        return lender;
    }
    //채무자 유효성 검증
    public Member findVerifiedDebtor(Long id) {
        Member debtor = memberRepository.findById(id).orElseThrow(() -> new BusinessLogicException(ExceptionCode.DEBTOR_NOT_FOUND));
        return debtor;
    }

    //차용증 유효성 검증
    public Debt findVerifiedDebt(Long id) {
        Debt debt = debtRepository.findById(id).orElseThrow(() -> new BusinessLogicException(ExceptionCode.DEBT_NOT_FOUND));
        return debt;
    }

    public ExtendHistory findVerifiedExtendHistory(Long id) {
        ExtendHistory extendHistory = extendHistoryRepository.findById(id).orElseThrow(() -> new BusinessLogicException(ExceptionCode.EXTENDHISTORY_NOT_FOUND));
        return extendHistory;
    }

    public void checkExtendHistoryStatus(String status){
        try{
            ExtendStatus.valueOf(status);
        }catch (Exception e) {
            throw new BusinessLogicException(ExceptionCode.WRONG_EXTENDHISTORY_STATUS);
        }

    }
    public void checkDebtStatus(String status){
        try{
            DebtStatus.valueOf(status);
        }catch (Exception e) {
            throw new BusinessLogicException(ExceptionCode.WRONG_DEBT_STATUS);
        }

    }

}