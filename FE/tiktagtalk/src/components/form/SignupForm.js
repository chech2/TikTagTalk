import { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'

import "./SignupForm.css"
import 'animate.css';

import { TextField, InputLabel, FormControl, Input, InputAdornment, IconButton, FormHelperText } from '@mui/material';
import { Visibility, VisibilityOff } from '@mui/icons-material';

function SignupForm() {

    const avatars = [
        {name: 1, img: "./avatar/type1.jpg"},
        {name: 2, img: "./avatar/type2.jpg"},
        {name: 3, img: "./avatar/type3.jpg"},
        {name: 4, img: "./avatar/type4.jpg"},
        {name: 5, img: "./avatar/type5.jpg"},
        {name: 6, img: "./avatar/type6.jpg"},
        {name: 7, img: "./avatar/type7.jpg"},
        {name: 8, img: "./avatar/type8.jpg"}
    ]

    const navigate = useNavigate();

    /* userId */
    const [userId, setUserId] = useState('');
    const [isEverTyped, setEverTyped] = useState(false);
    const [isValidUserId, setValidUserId] = useState(true);
    const [userIdHelperText, setUserIdHelperText] = useState('');
    /* password1 */
    const [password1, setPassword1] = useState('');
    const [showPassword1, setShowPassword1] = useState(false);
    const handleClickShowPassword1 = () => setShowPassword1((show) => !show);
    const handleMouseDownPassword1 = (event) => {
        event.preventDefault();
    };
    const [isEverTypedPw1, setEverTypedPw1] = useState(false);
    const [isValidPw1, setValidPw1] = useState(true);
    const [pw1HelperText, setPw1HelperText] = useState('');
    /* password2 */
    const [password2, setPassword2] = useState('');
    const [showPassword2, setShowPassword2] = useState(false);
    const handleClickShowPassword2 = () => setShowPassword2((show) => !show);
    const handleMouseDownPassword2 = (event) => {
        event.preventDefault();
    };
    const [isEverTypedPw2, setEverTypedPw2] = useState(false);
    const [isValidPw2, setValidPw2] = useState(true);
    const [pw2HelperText, setPw2HelperText] = useState('');
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

    /* ---------- 비밀번호1 유효성 검사 ---------- */
    useEffect(() => {
        // 영문 대/소문자, 숫자, 특수문자를 포함하는 8~16자의 문자열
        const regex = /^(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d!@#$%^&*]{8,16}$/;
        const isValidFormat = regex.test(password1);

        if (password1 !== '') {
            setEverTypedPw1(true);

            if (!isValidFormat) { // 비밀번호1 양식 오류
                setValidPw1(false);
                setPw1HelperText('비밀번호는 8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.')
            }
            else {
                setValidPw1(true);
                setPw1HelperText('사용 가능한 비밀번호입니다.');
            }
        }
        else if (isEverTypedPw1 && password1 === '') {
            setValidPw1(false);
            setPw1HelperText('비밀번호를 입력해 주세요.');
        }
    }, [password1])

    /* ---------- 비밀번호2 유효성 검사 ---------- */
    useEffect(() => {

        if (password2 !== '') {
            setEverTypedPw2(true);

            if (password1 !== password2) { // 비밀번호1과 동일하지 않다면
                setValidPw2(false);
                setPw2HelperText('비밀번호가 일치하지 않습니다.')
            }
            else {
                setValidPw2(true);
                setPw2HelperText('비밀번호 확인 완료');
            }
        }
        else if (isEverTypedPw2 && password2 === '') {
            setValidPw2(false);
            setPw2HelperText('비밀번호를 다시 입력해 주세요.');
        }
    }, [password2])

    /* 아바타 타입 변경 */
    const handleAvatarChange = (event) => {
        setAvatarType(event.target.value);
    };

    // ---------- 회원 가입 기능 구현 ---------- //
    const handleSignUp = async (e) => {

        // Reload 막기
        e.preventDefault();

        // 예외처리
        if (!isEverTyped && !isValidUserId) { // 아이디 입력이 제대로 되지 않았다면
            alert('아이디를 확인해 주세요.');
            return;
        }
        if (!isEverTypedPw1 && !isValidPw1) { // 비밀번호 입력이 제대로 되지 않았다면
            alert('비밀번호를 확인해 주세요.');
            return;
        }
        if (!isEverTypedPw2 && !isValidPw2) { // 비밀번호 재입력이 제대로 되지 않았다면
            alert('비밀번호를 재입력 해주세요.');
            return;
        }

        let body = {
            userId : userId,
            password : password1,
            name : name,
            introduction : introduction,
            avatarType : avatarType
        };

        await axios.post(process.env.REACT_APP_BASE_URL + '/members/sign-up', body)
        .then((res) => {
            if(res.status === 201) {
                navigate('/login');
                alert("회원 가입에 성공했습니다.");
            }
        })
        .catch((err) => {
            alert(err.response.data);
        })
    }

    return (
        <div className="sign-form-container">
            <div className="animate__animated animate__bounceInUp">
                <img src="./TikTagTalk_logo.png" alt="tiktagtalk logo" className="sign-form-logo animate__animated animate__pulse animate__slower animate__infinite"></img>
            </div>
            <form onSubmit={handleSignUp} className="sign-form">

                {/* 아이디 입력 */}
                <div className="sign-form-id-group animate__animated animate__bounceInUp animate__slow">
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

                {/* 비밀번호1 입력 */}
                <div className='sign-form-pw1-group animate__animated animate__bounceInUp animate__slow'>
                    <FormControl
                        className="sign-form-pw1"
                        variant="standard"
                        sx={{
                            '& .MuiInput-underline:before': {
                                borderBottomColor: isValidPw1 ? '#FCF7FF' : '#D03B29', // 입력 필드 밑줄의 색상 설정 (기본 상태)
                            },
                            '& .MuiInput-underline:after': {
                                borderBottomColor: isValidPw1 ? '#FCF7FF' : '#D03B29', // 입력 필드 밑줄의 색상 설정 (호버 상태)
                            },
                        }}
                        required
                    >
                        <InputLabel
                            htmlFor="standard-adornment-password"
                            sx={{
                                color: isValidPw1 ? '#FCF7FF' : '#D03B29',
                                '&.Mui-focused': {
                                    color: isValidPw1 ? '#FCF7FF' : '#D03B29',  // label이 포커스된 상태에서의 색상 설정
                                },
                            }}
                        >
                            비밀번호
                        </InputLabel>
                        <Input
                            id="standard-adornment-password"
                            type={showPassword1 ? 'text' : 'password'}
                            inputProps={{ maxLength: 16 }}
                            endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowPassword1}
                                    onMouseDown={handleMouseDownPassword1}
                                    sx={{
                                        color: isValidPw1 ? '#FCF7FF' : '#D03B29',
                                    }}
                                >
                                    {showPassword1 ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                            </InputAdornment>
                            }
                            sx={{
                                '& .MuiInputBase-input': {
                                    color: isValidPw1 ? '#FCF7FF' : '#D03B29', // 입력 필드 텍스트의 색상 설정
                                },
                            }}
                            onBlur={e=>{setPassword1(e.target.value)}}
                        />
                        {pw1HelperText !== '' &&
                            <FormHelperText
                                id="component-error-text"
                                sx={{
                                    color: isValidPw1 ? '#FCF7FF' : '#D03B29'
                                }}
                            >
                                {pw1HelperText}
                            </FormHelperText>
                        }
                    </FormControl>
                </div>

                {/* 비밀번호2 입력 */}
                <div className='sign-form-pw2-group animate__animated animate__bounceInUp animate__slow'>
                    <FormControl
                        className="sign-form-pw2"
                        variant="standard"
                        sx={{
                            '& .MuiInput-underline:before': {
                                borderBottomColor: isValidPw2 ? '#FCF7FF' : '#D03B29', // 입력 필드 밑줄의 색상 설정 (기본 상태)
                            },
                            '& .MuiInput-underline:after': {
                                borderBottomColor: isValidPw2 ? '#FCF7FF' : '#D03B29', // 입력 필드 밑줄의 색상 설정 (호버 상태)
                            },
                        }}
                        required
                    >
                        <InputLabel
                            htmlFor="standard-adornment-password"
                            sx={{
                                color: isValidPw2 ? '#FCF7FF' : '#D03B29',
                                '&.Mui-focused': {
                                    color: isValidPw2 ? '#FCF7FF' : '#D03B29',  // label이 포커스된 상태에서의 색상 설정
                                },
                            }}
                        >
                            비밀번호 재입력
                        </InputLabel>
                        <Input
                            id="standard-adornment-password"
                            type={showPassword1 ? 'text' : 'password'}
                            inputProps={{ maxLength: 16 }}
                            endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowPassword2}
                                    onMouseDown={handleMouseDownPassword2}
                                    sx={{
                                        color: isValidPw2 ? '#FCF7FF' : '#D03B29',
                                    }}
                                >
                                    {showPassword2 ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                            </InputAdornment>
                            }
                            sx={{
                                '& .MuiInputBase-input': {
                                    color: isValidPw2 ? '#FCF7FF' : '#D03B29', // 입력 필드 텍스트의 색상 설정
                                },
                            }}
                            onBlur={e=>{setPassword2(e.target.value)}}
                        />
                        {pw2HelperText !== '' &&
                            <FormHelperText
                                id="component-error-text"
                                sx={{
                                    color: isValidPw2 ? '#FCF7FF' : '#D03B29'
                                }}
                            >
                                {pw2HelperText}
                            </FormHelperText>
                        }
                    </FormControl>
                </div>

                {/* 닉네임 입력 */}
                <div className='sign-form-name-group animate__animated animate__bounceInUp animate__slower'>
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
                <div className='sign-form-introduction-group animate__animated animate__bounceInUp animate__slower'>
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
                <div className='sign-form-avatar-group animate__animated animate__bounceInUp animate__slower'>
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
                <button className='sign-up-submitButton animate__animated animate__bounceInUp animate__slower' type='submit'>회원가입</button>
            </form>
        </div>
    )
}

export default SignupForm;