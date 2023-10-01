import './LocalLoginForm.css'
import 'animate.css';

import { useNavigate } from 'react-router-dom'
import { useState } from 'react';
import { useDispatch} from 'react-redux';
import axios from 'axios';
import { loginUser } from '../../redux/userSlice';

import { TextField, InputLabel, FormControl, OutlinedInput, InputAdornment, IconButton } from '@mui/material';
import { Visibility, VisibilityOff } from '@mui/icons-material';

export default function LocalLoginForm() {

    const navigate = useNavigate();
    const dispatch = useDispatch();

    const [id, setId] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);
    const [showWarningId, setShowWarningId] = useState(false);
    const [showWarningPw, setShowWarningPw] = useState(false);

    const handleClickShowPassword = () => setShowPassword((show) => !show);

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    // ---------- 로그인 기능 구현 ---------- //
    const handleLogin = async (e) => {

        // Reload 막기
        e.preventDefault();

        // 빈 값 처리
        if (!id) { // 아이디를 입력하지 않았다면
            setShowWarningId(true);
            return;
        } else {
            setShowWarningId(false);
        }
        
        if (!password) { // 패스워드를 입력하지 않았다면
            setShowWarningPw(true);
            return;
        } else {
            setShowWarningPw(false);
        }

        // axios 요청 보내기
        let body = {
            userId : id,
            password : password
        }

        await axios.post(process.env.REACT_APP_BASE_URL + '/login', body)
        .then((res) => {
            if (res.status === 200) {
                const accessToken = res.headers['authorization'];
                const refreshToken = res.headers['authorization-refresh'];
                
                // LocalStorage에 토큰 저장
                localStorage.setItem("accessToken", accessToken);
                localStorage.setItem("refreshToken", refreshToken);
                
                const data = res.data;
                dispatch(loginUser(data))
                
                navigate(`/main/${data.id}`);
            }
        })
        .catch((res) => {
            alert("아이디나 비밀번호를 확인해주세요.");
        })
    }

    return (
        <div className="local-login-form-container">
            <div className="animate__animated animate__bounceInUp">
                <img src="./TikTagTalk_logo.png" className="login-form-logo animate__animated animate__pulse animate__slower animate__infinite"></img>
            </div>
            <form onSubmit={handleLogin} className="login-form animate__animated animate__bounceInUp animate__slow">

                {/* 아이디 입력 */}
                <div className='login-form-group'>
                    <TextField
                        className="login-form-id"
                        onChange={e=>{setId(e.target.value)}}
                        label="아이디"
                        id="outlined-size-normal"
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
                {showWarningId ? <div className="warning-id">* 아이디를 입력하세요.</div> : null}

                {/* 비밀번호 입력 */}
                <div className='login-form-group'>
                    <FormControl sx={{ width: '70%' }} variant="outlined" size="normal" className="custom-form-control-class">
                        <InputLabel htmlFor="outlined-adornment-password"
                            sx={{
                                color: '#FCF7FF',  // label의 기본 색상 설정
                                '&.Mui-focused': {
                                    color: '#FCF7FF',  // label이 포커스된 상태에서의 색상 설정
                                },
                            }}
                        >
                            비밀번호
                        </InputLabel>
                        <OutlinedInput
                            id="outlined-adornment-password formBasicPassword"
                            className="login-form-input"
                            type={showPassword ? 'text' : 'password'}
                            endAdornment={
                                <InputAdornment position="end">
                                    <IconButton
                                        aria-label="toggle password visibility"
                                        onClick={handleClickShowPassword}
                                        onMouseDown={handleMouseDownPassword}
                                        edge="end"
                                        sx={{
                                            color: '#FCF7FF',  // IconButton의 색상 설정 
                                        }}
                                    >
                                        {showPassword ? <VisibilityOff /> : <Visibility />}
                                    </IconButton>
                                </InputAdornment>
                            }
                            label="Password"
                            onChange={e => { setPassword(e.target.value) }}
                            sx={{
                                color: '#FCF7FF',  // 텍스트 색상 설정
                                '& .MuiOutlinedInput-notchedOutline': {
                                    borderColor: '#FCF7FF',
                                },
                                '&:hover .MuiOutlinedInput-notchedOutline': {
                                    borderColor: '#FCF7FF',
                                },
                            }}
                        />
                    </FormControl>
                </div>
                {showWarningPw ? <div className="warning-pw">* 비밀번호를 입력하세요.</div> : null}

                {/* Login 버튼 */}
                <button className='login-submitButton' type='submit'>Login</button>

                {/* 회원가입 */}
                <div className='login-form-group sing-up-component' id='navigateNewPage'>
                    <span onClick={() => { navigate('/sign-up') }} className="sign-up-button">회원가입</span>
                </div>


            </form>
        </div>
    )
}