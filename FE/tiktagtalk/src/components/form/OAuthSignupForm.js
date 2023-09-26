import { useEffect, useState } from 'react';
import { customAxios } from '../../CustomAxios'
import { useNavigate, useParams } from 'react-router-dom'
import { useCookies } from 'react-cookie';
import { useDispatch} from 'react-redux';
import { loginUser } from '../../redux/userSlice';

import "./SignupForm.css"
import type1 from "./avatar/type1.jfif"
import type2 from "./avatar/type2.jfif"
import type3 from "./avatar/type3.jfif"
import type4 from "./avatar/type4.jfif"
import type5 from "./avatar/type5.jfif"
import type6 from "./avatar/type6.jfif"

function SignupForm() {

    const avatars = [
        {name: 1, img: type1},
        {name: 2, img: type2},
        {name: 3, img: type3},
        {name: 4, img: type4},
        {name: 5, img: type5},
        {name: 6, img: type6}
    ]

    const navigate = useNavigate();
    const params = useParams();
    const dispatch = useDispatch();

    const [userId, setUserId] = useState('');
    const [name, setName] = useState('');
    const [introduction, setIntroduction] = useState('');
    const [avatarType, setAvatarType] = useState('');
    const [cookie, setCookie] = useCookies(['accessToken', 'refreshToken']);

    const handleAvatarChange = (event) => {
        setAvatarType(event.target.value);
    };

    useEffect(() => {
        setCookie('accessToken', params.token, { path: '/' });
    }, []);

    // ---------- 회원 가입 기능 구현 ---------- //
    const handleSubmit = async (e) => {

        // Reload 막기
        e.preventDefault();

        // 예외처리 필요

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
                
                // 쿠키에 토큰 저장
                setCookie('accessToken', accessToken, { path: '/' });
                setCookie('refreshToken', refreshToken, { path: '/' });

                const data = res.data;
                dispatch(loginUser(data))
                navigate('/');
                alert("정보 입력이 완료되었습니다.");
            }
        })
        .catch((res) => {
            alert("정보 입력에 실패했습니다.");
        })
    }

    return (
        <div className="oauth-sign-form-container">
            <form className="oauth-sign-form">
                <div className="oauth-sign-form-title">회원가입</div>

                {/* 아이디 입력 */}
                {/* 아이디 입력 시 중복 체크 필요 */}
                <div className="sign-form-group">
                    <input className="sign-form-input" onChange={e=>{setUserId(e.target.value)}} type='id' id='form-userId' placeholder='아이디를 입력해주세요.' required ></input>
                </div>

                {/* 닉네임 입력 */}
                {/* nullable */}
                <div className='oauth-sign-form-group'>
                    <input className='pauth-sign-form-input' onChange={e=>{setName(e.target.value)}} type='text' id='form-name' placeholder='회원님을 알아볼 수 있는 이름을 입력해주세요.'></input>
                </div>

                {/* 소개글 입력 */}
                {/* nullable */}
                <div className='oauth-sign-form-group'>
                    <input className='sign-form-input' onChange={e=>{setIntroduction(e.target.value)}} type='text' id='form-introduction' placeholder='회원님을 소개해주세요.'></input>
                </div>

                {/* 아바타 종류 고르기 */}
                <div className='sign-form-group'>
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

                {/* Submit 버튼 */}
                <button onClick={handleSubmit} className='save-submit-button' type='submit'>
                    저장
                </button>
            </form>
        </div>
    )
}

export default SignupForm;