# TIKTAGTALK
<img src="/images/tiktagtalklogo.png">

# 목차
#### 1. [프로젝트 개요](#프로젝트-개요)
#### 2. [기술 스택](#기술-스택)
#### 3. [주요 기능](#주요-기능)
#### 4. [화면 구성](#화면-구성)
#### 5. [아키텍쳐](#아키텍쳐)
#### 6. [디렉토리 구조](#디렉토리-구조)
#### 7. [팀 정보](#팀-정보)

# 프로젝트 개요
>#### 프로젝트 명
>TIKTAGTALK
>
>#### 개발 기간
>2023.08.21 - 2023.10.06
>
>#### 프로젝트 소개
>소비 내역에 따른 소비 태그를 이용한 마이룸 꾸미기 SNS서비스 입니다.

# 기술 스택

#### Front End
<img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=white"> <img src="https://img.shields.io/badge/three js-000000?style=for-the-badge&logo=threedotjs&logoColor=white"><br>

#### Back End
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/MySql-4478A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/spring data jpa-F80000?style=for-the-badge&logo=jpa&logoColor=white">
<br>

#### Infra
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"> <img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white"> <img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=white">
<br>

# 주요 기능
### 소비 태그 카테고리화
소비처에 따른 태그를 분류하여 카테고리화<br>

>#### 소비 태그 카테고리 <br>
>`식비`, `편의점/마트/잡화`, `교통/자동차`, `쇼핑`, <br>
>`카페/간식`,`보험/세금/기타금융`, `취미/여가`, `미용` <br>
>`의료/건강/피트니스`,`정기결제`, `여행`, `반려동물` <br>

### 소비 태그에 따른 태그룸(마이룸)을 꾸밀 수 있는 아이템 획득

소비 횟수 및 금액 초과하는 각 소비 카테고리에 따라 아이템 획득<br>

### 현재 환율을 적용한 포인트 환전
실시간 달러 환율을 적용하여 코인 → 포인트 환전<br>

>#### 환전 포인트로 아이템에 대한 스킨 구매 가능 <br>

### SNS의 팔로우 기능인 톡톡 친구 기능 도입
톡톡 관계를 맺고 있는 친구의 태그룸 구경 및 방명록 작성 가능<br>

# 화면 구성

# 아키텍쳐
<img src="/images/tiktagtalk.시스템 아키텍처.png"><br><br><br>

# 디렉토리 구조
```bash
📦TikTagTalk
 ┣ 📂domain
 ┃ ┣ 📂account
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┣ 📜ConsumeHistoryController.java
 ┃ ┃ ┃ ┗ 📜ConsumePlanController.java
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┣ 📜AddConsumeHistoryRequestDto.java
 ┃ ┃ ┃ ┃ ┣ 📜AllConsumePlanRequestDto.java
 ┃ ┃ ┃ ┃ ┣ 📜ConsumeHistoryRequestDto.java
 ┃ ┃ ┃ ┃ ┣ 📜ConsumePlanRequestDto.java
 ┃ ┃ ┃ ┃ ┗ 📜ModifyConsumeHistoryRequestDto.java
 ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┣ 📜AllConsumeHistoryResponseDto.java
 ┃ ┃ ┃ ┃ ┣ 📜AllConsumePlanResonseDto.java
 ┃ ┃ ┃ ┃ ┣ 📜CheckAccountResponseDto.java
 ┃ ┃ ┃ ┃ ┣ 📜CheckMemberTagResponseDto.java
 ┃ ┃ ┃ ┃ ┣ 📜ResponseDto.java
 ┃ ┃ ┃ ┃ ┣ 📜ResponseStatus.java
 ┃ ┃ ┃ ┃ ┗ 📜ResponseUtil.java
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┣ 📜Account.java
 ┃ ┃ ┃ ┣ 📜ConsumeHistory.java
 ┃ ┃ ┃ ┗ 📜ConsumePlan.java
 ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┣ 📜InvalidException.java
 ┃ ┃ ┃ ┗ 📜NotExistException.java
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┣ 📜AccountRepository.java
 ┃ ┃ ┃ ┣ 📜AccountRepositoryCustom.java
 ┃ ┃ ┃ ┣ 📜AccountRepositoryImpl.java
 ┃ ┃ ┃ ┣ 📜ConsumeHistoryRepository.java
 ┃ ┃ ┃ ┣ 📜ConsumeHistoryRepositoryCustom.java
 ┃ ┃ ┃ ┣ 📜ConsumeHistoryRepositoryImpl.java
 ┃ ┃ ┃ ┣ 📜ConsumePlanRepository.java
 ┃ ┃ ┃ ┣ 📜ConsumePlanRepositoryCustom.java
 ┃ ┃ ┃ ┗ 📜ConsumePlanRepositoryImpl.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┣ 📜ConsumeHistoryServcieImpl.java
 ┃ ┃ ┃ ┣ 📜ConsumeHistoryService.java
 ┃ ┃ ┃ ┣ 📜ConsumePlanService.java
 ┃ ┃ ┃ ┗ 📜ConsumePlanServiceImpl.java
 ┃ ┣ 📂debt
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┗ 📜DebtController.java
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┗ 📜DebtRequestDto.java
 ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┗ 📜DebtResponseDto.java
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┣ 📜Debt.java
 ┃ ┃ ┃ ┣ 📜ExtendHistory.java
 ┃ ┃ ┃ ┣ 📜ExtendStatus.java
 ┃ ┃ ┃ ┗ 📜RepaymentHistory.java
 ┃ ┃ ┣ 📂exceptions
 ┃ ┃ ┃ ┣ 📂business
 ┃ ┃ ┃ ┃ ┗ 📜BusinessLogicException.java
 ┃ ┃ ┃ ┣ 📂codes
 ┃ ┃ ┃ ┃ ┗ 📜ExceptionCode.java
 ┃ ┃ ┃ ┗ 📂dto
 ┃ ┃ ┃ ┃ ┗ 📜ErrorResponse.java
 ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┗ 📜DebtMapper.java
 ┃ ┃ ┣ 📂pagination
 ┃ ┃ ┃ ┣ 📜MultiResponseDto.java
 ┃ ┃ ┃ ┗ 📜PageInfo.java
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┣ 📜DebtRepository.java
 ┃ ┃ ┃ ┣ 📜DebtRepositoryCustom.java
 ┃ ┃ ┃ ┣ 📜DebtRepositoryImpl.java
 ┃ ┃ ┃ ┣ 📜ExtendHistoryRepository.java
 ┃ ┃ ┃ ┗ 📜RepaymentHistoryRepository.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┣ 📜DebtService.java
 ┃ ┃ ┃ ┗ 📜DebtServiceImpl.java
 ┃ ┣ 📂skin
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┣ 📜MemberSkinController.java
 ┃ ┃ ┃ ┗ 📜SkinController.java
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┗ 📜BuyRequest.java
 ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┣ 📜AllSkinResponse.java
 ┃ ┃ ┃ ┃ ┣ 📜BuyResponse.java
 ┃ ┃ ┃ ┃ ┗ 📜MemberSkinListResponse.java
 ┃ ┃ ┃ ┗ 📜skinDto.java
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┣ 📜MemberSkin.java
 ┃ ┃ ┃ ┗ 📜Skin.java
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┣ 📜MemberSkinRepository.java
 ┃ ┃ ┃ ┣ 📜SkinRepository.java
 ┃ ┃ ┃ ┣ 📜SkinRepositoryCustom.java
 ┃ ┃ ┃ ┗ 📜SkinRepositoryImpl.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┣ 📜MemberSkinService.java
 ┃ ┃ ┃ ┣ 📜MemberSkinServiceImpl.java
 ┃ ┃ ┃ ┣ 📜SkinService.java
 ┃ ┃ ┃ ┗ 📜SkinServiceImpl.java
 ┃ ┣ 📂tag
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┗ 📜MemberTagController.java
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┗ 📜CheckMemberTagRequestDto.java
 ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┗ 📜CheckMemberTagResponseDto.java
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┣ 📜MemberTag.java
 ┃ ┃ ┃ ┣ 📜Store.java
 ┃ ┃ ┃ ┗ 📜Tag.java
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┣ 📜MemberTagRepository.java
 ┃ ┃ ┃ ┣ 📜MemberTagRepositoryCustom.java
 ┃ ┃ ┃ ┣ 📜MemberTagRepositoryImpl.java
 ┃ ┃ ┃ ┣ 📜StoreRepository.java
 ┃ ┃ ┃ ┗ 📜TagRepository.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┣ 📜MemberTagService.java
 ┃ ┃ ┃ ┗ 📜MemberTagServiceImpl.java
 ┃ ┣ 📂tagRoom
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┣ 📜CommentController.java
 ┃ ┃ ┃ ┣ 📜ItemController.java
 ┃ ┃ ┃ ┣ 📜MemberItemController.java
 ┃ ┃ ┃ ┗ 📜TagRoomController.java
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┣ 📜InitMemberItemRequestDto.java
 ┃ ┃ ┃ ┃ ┣ 📜InsertCommentRequestDto.java
 ┃ ┃ ┃ ┃ ┣ 📜ModifyCommentRequestDto.java
 ┃ ┃ ┃ ┃ ┗ 📜UpdateMemberItemRequestDto.java
 ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┣ 📜AllCommentsResponseDto.java
 ┃ ┃ ┃ ┃ ┣ 📜InitMemberItemResponseDto.java
 ┃ ┃ ┃ ┃ ┗ 📜TagRoomResponseDto.java
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┣ 📜Comment.java
 ┃ ┃ ┃ ┣ 📜Item.java
 ┃ ┃ ┃ ┣ 📜MemberItem.java
 ┃ ┃ ┃ ┣ 📜TagRoom.java
 ┃ ┃ ┃ ┗ 📜TagRoomItem.java
 ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┗ 📜CustomAccessDeniedException.java
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┣ 📜CommentRepository.java
 ┃ ┃ ┃ ┣ 📜CommentRepositoryCustom.java
 ┃ ┃ ┃ ┣ 📜CommentRepositoryImpl.java
 ┃ ┃ ┃ ┣ 📜ItemRepository.java
 ┃ ┃ ┃ ┣ 📜ItemRepositoryCustom.java
 ┃ ┃ ┃ ┣ 📜ItemRespositoryImpl.java
 ┃ ┃ ┃ ┣ 📜MemberItemRepository.java
 ┃ ┃ ┃ ┣ 📜MemberItemRepositoryCustom.java
 ┃ ┃ ┃ ┣ 📜MemberItemRepositoryImpl.java
 ┃ ┃ ┃ ┣ 📜TagRoomRepository.java
 ┃ ┃ ┃ ┣ 📜TagRoomRepositoryCustom.java
 ┃ ┃ ┃ ┗ 📜TagRoomRepositoryImpl.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┣ 📜CommentService.java
 ┃ ┃ ┃ ┣ 📜CommentServiceImpl.java
 ┃ ┃ ┃ ┣ 📜ItemService.java
 ┃ ┃ ┃ ┣ 📜ItemServiceImpl.java
 ┃ ┃ ┃ ┣ 📜MemberItemService.java
 ┃ ┃ ┃ ┣ 📜MemberItemServiceImpl.java
 ┃ ┃ ┃ ┣ 📜TagRoomService.java
 ┃ ┃ ┃ ┗ 📜TagRoomServiceImpl.java
 ┃ ┣ 📂user
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┣ 📜MemberController.java
 ┃ ┃ ┃ ┗ 📜TalkTalkController.java
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┣ 📜CheckUserIdRequestDto.java
 ┃ ┃ ┃ ┃ ┣ 📜FindMemberRequestDto.java
 ┃ ┃ ┃ ┃ ┣ 📜MemberOAuthSignUpDto.java
 ┃ ┃ ┃ ┃ ┣ 📜MemberSignUpDto.java
 ┃ ┃ ┃ ┃ ┗ 📜TalkTalkRequestDto.java
 ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┣ 📜ExceptionResponseDto.java
 ┃ ┃ ┃ ┃ ┣ 📜FindMemberResponseDto.java
 ┃ ┃ ┃ ┃ ┣ 📜FindTalkTalkListResponseDto.java
 ┃ ┃ ┃ ┃ ┣ 📜MemberLoginResponseDTO.java
 ┃ ┃ ┃ ┃ ┗ 📜ResponseDto.java
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┣ 📜Member.java
 ┃ ┃ ┃ ┣ 📜Role.java
 ┃ ┃ ┃ ┣ 📜SocialType.java
 ┃ ┃ ┃ ┣ 📜TalkTalk.java
 ┃ ┃ ┃ ┗ 📜TalkTalkStatus.java
 ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┣ 📂custom
 ┃ ┃ ┃ ┃ ┣ 📜AlreadyExistingTalkTalkException.java
 ┃ ┃ ┃ ┃ ┣ 📜AlreadySentRequestException.java
 ┃ ┃ ┃ ┃ ┣ 📜AvatarTypeIsInvalidException.java
 ┃ ┃ ┃ ┃ ┣ 📜DoNotHavePremissionException.java
 ┃ ┃ ┃ ┃ ┣ 📜DuplicateUserIdException.java
 ┃ ┃ ┃ ┃ ┣ 📜ExpriedRefreshTokenException.java
 ┃ ┃ ┃ ┃ ┣ 📜NoSuchUserException.java
 ┃ ┃ ┃ ┃ ┣ 📜NotExistRequestException.java
 ┃ ┃ ┃ ┃ ┣ 📜OtherPartyAlreadySentRequestException.java
 ┃ ┃ ┃ ┃ ┣ 📜PasswordIsInvalidException.java
 ┃ ┃ ┃ ┃ ┣ 📜SendTalktalkRequestYourself.java
 ┃ ┃ ┃ ┃ ┗ 📜UserIdIsInvalidException.java
 ┃ ┃ ┃ ┗ 📜ExceptionCode.java
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┣ 📜MemberRepository.java
 ┃ ┃ ┃ ┗ 📜TalkTalkRepository.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┣ 📜MemberService.java
 ┃ ┃ ┃ ┗ 📜TalkTalkService.java
 ┃ ┗ 📂wallet
 ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┣ 📜CoinHistoryController.java
 ┃ ┃ ┃ ┣ 📜CurrencyConverterAPIController.java
 ┃ ┃ ┃ ┣ 📜ExchangeHistoryController.java
 ┃ ┃ ┃ ┗ 📜PointHistoryController.java
 ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┗ 📜ExchangeRequest.java
 ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┣ 📜CoinListResponse.java
 ┃ ┃ ┃ ┃ ┣ 📜ExchangeResponse.java
 ┃ ┃ ┃ ┃ ┗ 📜PointListResponse.java
 ┃ ┃ ┃ ┣ 📜ConvertInfoDto.java
 ┃ ┃ ┃ ┗ 📜CurrencyDto.java
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┣ 📜CoinHistory.java
 ┃ ┃ ┃ ┣ 📜ExchangeHistory.java
 ┃ ┃ ┃ ┗ 📜PointHistory.java
 ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┣ 📜CoinHistoryRepository.java
 ┃ ┃ ┃ ┣ 📜ExchangeHistoryRepository.java
 ┃ ┃ ┃ ┗ 📜PointHistoryRepository.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┣ 📜CoinHistoryService.java
 ┃ ┃ ┃ ┣ 📜CoinHistoryServiceImpl.java
 ┃ ┃ ┃ ┣ 📜CurrencyAPIService.java
 ┃ ┃ ┃ ┣ 📜CurrencyAPIServiceImpl.java
 ┃ ┃ ┃ ┣ 📜CurrencyConverterService.java
 ┃ ┃ ┃ ┣ 📜CurrencyConverterServiceImpl.java
 ┃ ┃ ┃ ┣ 📜ExchangeHistoryService.java
 ┃ ┃ ┃ ┣ 📜ExchangeHistoryServiceImpl.java
 ┃ ┃ ┃ ┣ 📜PointHistoryService.java
 ┃ ┃ ┃ ┗ 📜PointHistoryServiceImpl.java
 ┣ 📂global
 ┃ ┣ 📂config
 ┃ ┃ ┗ 📜SecurityConfig.java
 ┃ ┣ 📂entity
 ┃ ┃ ┗ 📜CustomUserDetails.java
 ┃ ┣ 📂jwt
 ┃ ┃ ┣ 📂filter
 ┃ ┃ ┃ ┗ 📜JwtAuthenticationProcessingFilter.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜JwtService.java
 ┃ ┣ 📂login
 ┃ ┃ ┣ 📂filter
 ┃ ┃ ┃ ┗ 📜CustomJsonUsernamePasswordAuthenticationFilter.java
 ┃ ┃ ┣ 📂handler
 ┃ ┃ ┃ ┣ 📜LoginFailureHandler.java
 ┃ ┃ ┃ ┗ 📜LoginSuccessHandler.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜LoginService.java
 ┃ ┣ 📂oauth2
 ┃ ┃ ┣ 📂handler
 ┃ ┃ ┃ ┣ 📜OAuth2LoginFailureHandler.java
 ┃ ┃ ┃ ┗ 📜OAuth2LoginSuccessHandler.java
 ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┗ 📜CustomOAuth2UserService.java
 ┃ ┃ ┣ 📂userInfo
 ┃ ┃ ┃ ┣ 📜GoogleOAuth2UserInfo.java
 ┃ ┃ ┃ ┣ 📜KakaoOAuth2UserInfo.java
 ┃ ┃ ┃ ┣ 📜NaverOAuth2UserInfo.java
 ┃ ┃ ┃ ┗ 📜OAuth2UserInfo.java
 ┃ ┃ ┣ 📜CustomOAuth2User.java
 ┃ ┃ ┗ 📜OAuthAttributes.java
 ┃ ┣ 📂s3
 ┃ ┃ ┗ 📜AwsS3Config.java
 ┃ ┣ 📂schedule
 ┃ ┃ ┗ 📜MemberScheduler.java
 ┃ ┣ 📂util
 ┃ ┃ ┣ 📜PasswordUtil.java
 ┃ ┃ ┗ 📜SecurityUtil.java
 ┃ ┗ 📜SwaggerController.java
 ┣ 📜AppConfig.java
 ┗ 📜TikTagTalkApplication.java
```

# 팀 정보
<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/tjsguddl96"><img src="https://avatars.githubusercontent.com/u/58164681?v=4" width="150px;" alt=""/><br /><b>김선형</b></a> <br>BE </b><br /></td>
      <td align="center"><a href="https://github.com/juneheyoung"><img src="https://avatars.githubusercontent.com/u/88702363?v=4" width="150px;" alt=""/><br /><b>박준형</b></a> <br>FE </b><br /></td>
      <td align="center"><a href="https://github.com/SeungAh-Yoo99"><img src="https://avatars.githubusercontent.com/u/68517303?v=4" width="150px;" alt=""/><br /><b>유승아</b></a> <br>BE (팀장) </b><br /></td>
    <tr/>
      <td align="center"><a href="https://github.com/chech2"><img src="https://avatars.githubusercontent.com/u/90683516?v=4" width="150px;" alt=""/><br /><b>이채림</b></a> <br>BE </b><br /></td>
      <td align="center"><a href="https://github.com/juuyoungjeon"><img src="https://avatars.githubusercontent.com/u/44489852?v=4" width="150px;" alt=""/><br /><b>전주영</b></a> <br>BE </b><br /></td>
      <td align="center"><a href="https://github.com/HJH13579"><img src="https://avatars.githubusercontent.com/u/58537329?v=4" width="150px;" alt=""/><br /><b>허주혁</b></a> <br>FE </b><br /></td>
    </tr>
  </tbody>
</table>
