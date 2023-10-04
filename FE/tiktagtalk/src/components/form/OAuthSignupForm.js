import { useEffect, useState } from 'react';
import { customAxios } from '../../CustomAxios'
import { useNavigate, useParams } from 'react-router-dom'
import { useDispatch} from 'react-redux';
import { loginUser } from '../../redux/userSlice';
import axios from 'axios';

import { TextField } from '@mui/material';

import "./OAuthSignupForm.css"

function OauthSignupForm() {

    const avatars = [
        {name: 1, img: "/avatar/type1.jpg"},
        {name: 2, img: "/avatar/type2.jpg"},
        {name: 3, img: "/avatar/type3.jpg"},
        {name: 4, img: "/avatar/type4.jpg"},
        {name: 5, img: "/avatar/type5.jpg"},
        {name: 6, img: "/avatar/type6.jpg"},
        {name: 7, img: "/avatar/type7.jpg"},
        {name: 8, img: "/avatar/type8.jpg"}
    ]

    const navigate = useNavigate();
    const params = useParams();
    const dispatch = useDispatch();

    /* userId */
    const [userId, setUserId] = useState('');
    const [isEverTyped, setEverTyped] = useState(false);
    const [isValidUserId, setValidUserId] = useState(true);
    const [userIdHelperText, setUserIdHelperText] = useState('');
    /* name */
    const [name, setName] = useState('');
    /* introduction */
    const [introduction, setIntroduction] = useState('');
    /* avatarType */
    const [avatarType, setAvatarType] = useState(1);

    /* ---------- 아이디 유효성 검사 ---------- */
    useEffect(() => {

        // 정규표현식: 5~20자의 영문 소문자, 숫자와 특수기호(_),(.)
        const regex = /^[a-z0-9_.]{5,20}$/;
        const isValidFormat = regex.test(userId);

        if (userId !== '') {
            setEverTyped(true);

            if (!isValidFormat) { // 아이디 양식 오류
                setValidUserId(false);
                setUserIdHelperText('아이디는 5~20자의 영문 소문자, 숫자와 특수기호(_)(.)만 사용 가능합니다.');
                return;
            }

            axios.post(process.env.REACT_APP_BASE_URL + "/members/check-userId",
                {
                    "userId": userId,
                }
            ).then((res) => {
                if (res.status === 200) {
                    setValidUserId(true);
                    setUserIdHelperText('사용 가능한 아이디입니다.');
                }
            }).catch((err) => {
                if (err.response.status === 460) {
                    setValidUserId(false);
                    setUserIdHelperText('이미 사용 중인 아이디입니다.');
                }
            });
        }
        else if (isEverTyped && userId === '') {
            setValidUserId(false);
            setUserIdHelperText('아이디를 입력해 주세요.');
        }
    }, [userId]);

    /* 아바타 타입 변경 */
    const handleAvatarChange = (event) => {
        setAvatarType(event.target.value);
    };

    useEffect(() => {
        localStorage.setItem("accessToken", params.token);
    }, []);

    // ---------- OAuth 회원 가입 기능 구현 ---------- //
    const handleSignUp = async (e) => {

        // Reload 막기
        e.preventDefault();

        // 예외처리
        if (!isEverTyped && !isValidUserId) { // 아이디 입력이 제대로 되지 않았다면
            alert('아이디를 확인해 주세요.');
            return;
        }

        let body = {
            userId: userId,
            name : name,
            introduction : introduction,
            avatarType : avatarType
        };

        await customAxios.post(process.env.REACT_APP_BASE_URL + '/members/oauth/sign-up', body)
        .then((res) => {
            if(res.status === 200) {
                const accessToken = res.headers['authorization'];
                const refreshToken = res.headers['authorization-refresh'];
                
                // LocalStorage에 토큰 저장
                localStorage.setItem("accessToken", accessToken);
                localStorage.setItem("refreshToken", refreshToken);

                const data = res.data;
                dispatch(loginUser(data))
                navigate(`/main/${data.id}`);
                alert("회원 가입에 성공했습니다.");
            }
        })
        .catch((err) => {
            if (err.response && err.response.data && err.response.data.message) {
                const errorMessage = err.response.data.message;
                alert(errorMessage); // 에러 메시지 출력
            } else {
                console.log(err); // 기타 오류 처리
            }
        })
    }

    return (
        <div className="oauth-sign-form-container">
            <div>
                <img src="TikTagTalk_logo.png" alt="tiktagtalk logo" className="sign-form-logo animate__animated animate__pulse animate__slower animate__infinite"></img>
            </div>
            <form onSubmit={handleSignUp} className="oauth-sign-form">

                {/* 아이디 입력 */}
                <div className="sign-form-id-group">
                    <TextField
                        className="sign-form-id"
                        onChange={e => { setUserId(e.target.value) }}
                        inputProps={{ maxLength: 20 }}
                        label="아이디"
                        id="standard-basic"
                        sx={{
                            '& label.MuiFormLabel-root': {
                                color: isValidUserId ? '#FCF7FF' : '#D03B29'  // 라벨 텍스트의 색상 설정
                            },
                            '& .MuiInputBase-input': {
                                color: isValidUserId ? '#FCF7FF' : '#D03B29', // 입력 필드 텍스트의 색상 설정
                            },
                            '& .MuiInput-underline:before': {
                                borderBottomColor: isValidUserId ? '#FCF7FF' : '#D03B29', // 입력 필드 밑줄의 색상 설정 (기본 상태)
                            },
                            '& .MuiInput-underline:after': {
                                borderBottomColor: isValidUserId ? '#FCF7FF' : '#D03B29',
                            },
                            '& .MuiFormHelperText-root': {
                                color: isValidUserId ? '#FCF7FF' : '#D03B29',
                            }
                        }}
                        variant="standard"
                        required
                        error={!isValidUserId}
                        helperText={userIdHelperText}
                    />
                </div>

                {/* 닉네임 입력 */}
                <div className='oauth-sign-form-name-group'>
                    <TextField
                        className="sign-form-name"
                        id="outlined-helperText"
                        label="이름"
                        helperText="회원님을 알아볼 수 있는 이름을 입력해주세요."
                        onBlur={e => { setName(e.target.value) }}
                        sx={{
                            '& .MuiOutlinedInput-root': {
                                color: '#FCF7FF',
                                '& fieldset': {
                                    borderColor: '#FCF7FF',
                                },
                                '&:hover fieldset': {
                                    borderColor: '#FCF7FF', // hover 상태에서도 같은 색 유지
                                },
                            },
                            '& label.MuiFormLabel-root': {
                                color:'#FCF7FF'  // 기본 상태에서의 색상 설정
                            },
                            '& .MuiFormHelperText-root': {
                                color: '#fcf7ff',  // 도움말 텍스트의 색상 설정
                                margin: 0,         // 도움말 텍스트 마진 제거
                            }
                        }}
                    />
                </div>

                {/* 소개글 입력 */}
                <div className='sign-form-introduction-group'>
                    <TextField
                        className="sign-form-introduction"
                        id="outlined-multiline-static"
                        multiline
                        label="소개"
                        placeholder="회원님을 소개해주세요."
                        rows={4}
                        onBlur={e => {setIntroduction(e.target.value)}}
                        sx={{
                            '& .MuiOutlinedInput-root': {
                                color: '#FCF7FF',
                                '& fieldset': {
                                    borderColor: '#FCF7FF',
                                },
                                '&:hover fieldset': {
                                    borderColor: '#FCF7FF', // hover 상태에서도 같은 색 유지
                                },
                            },
                            '& label.MuiFormLabel-root': {
                                color:'#FCF7FF'  // 기본 상태에서의 색상 설정
                            },
                        }}
                    />
                </div>

                {/* 아바타 종류 고르기 */}
                <div className='sign-form-avatar-group'>
                    <div className="sign-form-avatar-title">프로필 사진 선택</div>
                    <div className="sign-form-avatar">
                    {avatars.map(avatar => (
                        <label key={avatar.name} className='avatar-label'>
                            <input 
                                type='radio'
                                value={avatar.name}
                                checked={avatarType == avatar.name}
                                onChange={handleAvatarChange}
                            />
                            <img src={avatar.img} alt={`아바타 타입 ${avatar.name}`} />
                        </label>
                    ))}
                    </div>
                </div>

                {/* Login 버튼 */}
                <button className='sign-up-submitButton' type='submit'>회원가입</button>
            </form>
        </div>
    )
}

export default OauthSignupForm;