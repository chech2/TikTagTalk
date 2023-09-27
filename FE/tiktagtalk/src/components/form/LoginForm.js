import { useNavigate} from 'react-router-dom'
import { useState } from 'react';
import { useDispatch} from 'react-redux';
import axios from 'axios';
import { loginUser } from '../../redux/userSlice';

import SocialLogin from '../social/SocialLogin';

function LoginForm() {

    const navigate = useNavigate();
    const dispatch = useDispatch();

    const [id, setId] = useState('');
    const [password, setPassword] = useState('');

    // ---------- 로그인 기능 구현 ---------- //
    const handleLogin = async (e) => {

        // Reload 막기
        e.preventDefault();

        // 빈 값 처리
        if(!id) {
            return alert("아이디를 입력해주세요")
        } else if(!password) {
            return alert("비밀번호를 입력해주세요")
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
        <div>
            <form onSubmit={handleLogin} className="login-form">
                <div className="login-form-title">로그인</div>

                {/* 아이디 입력 */}
                <div className="login-form-group">
                    <input className="login-form-input" onChange={e=>{setId(e.target.value)}} type='id' id='formBasicUserId' placeholder='아이디를 입력해주세요.' />
                </div>

                {/* 비밀번호 입력 */}
                <div className='login-form-group'>
                    <input className='login-form-input' onChange={e=>{setPassword(e.target.value)}} type='password' id='formBasicPassword' placeholder='비밀번호를 입력해주세요.'/>
                </div>

                <br></br>

                {/* 회원가입 */}
                <div className='login-form-group' id='navigateNewPage'>
                    <span onClick={()=>{navigate('/sign-up')}}>회원가입</span>
                </div>

                <br></br>

                {/* Login 버튼 */}
                <button className='submitButton' type='submit'>Login</button>

                {/* 소셜 로그인 */}
                <div className='login-form-group'>
                    <SocialLogin></SocialLogin>
                </div>
            </form>
        </div>
    )
}

export default LoginForm;