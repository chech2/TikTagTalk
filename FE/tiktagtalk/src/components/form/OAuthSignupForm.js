import { useEffect, useState } from 'react';
import { customAxios } from '../../CustomAxios'
import { useNavigate, useParams } from 'react-router-dom'
import { useDispatch} from 'react-redux';
import { loginUser } from '../../redux/userSlice';

import "./SignupForm.css"

function SignupForm() {

    const avatars = [
        {name: 1, img: "./avatar/type1.jpg"},
        {name: 2, img: "./avatar/type2.jpg"},
        {name: 3, img: "./avatar/type3.jpg"},
        {name: 4, img: "./avatar/type4.jpg"},
        {name: 5, img: "./avatar/type5.jpg"},
        {name: 6, img: "./avatar/type6.jpg"}
    ]

    const navigate = useNavigate();
    const params = useParams();
    const dispatch = useDispatch();

    const [userId, setUserId] = useState('');
    const [name, setName] = useState('');
    const [introduction, setIntroduction] = useState('');
    const [avatarType, setAvatarType] = useState('');

    const handleAvatarChange = (event) => {
        setAvatarType(event.target.value);
    };

    useEffect(() => {
        localStorage.setItem("accessToken", params.token);
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
                
                // LocalStorage에 토큰 저장
                localStorage.setItem("accessToken", accessToken);
                localStorage.setItem("refreshToken", refreshToken);

                const data = res.data;
                dispatch(loginUser(data))
                navigate(`/main/${data.id}`);
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