import { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'

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

    const [userId, setUserId] = useState('');
    const [password1, setPassword1] = useState('');
    const [password2, setPassword2] = useState('');
    const [name, setName] = useState('');
    const [introduction, setIntroduction] = useState('');
    const [avatarType, setAvatarType] = useState('');

    const handleAvatarChange = (event) => {
        setAvatarType(event.target.value);
    };

    useEffect(() => {
        console.log(avatarType);
    }, [avatarType]);

    // ---------- 회원 가입 기능 구현 ---------- //
    const handleSubmit = async (e) => {
        e.preventDefault();

        // 예외처리 필요

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
        .catch((res) => {
            alert("회원가입에 실패했습니다.");
        })
    }

    return (
        <div className="sign-form-container">
            <form className="sign-form">
                <div className="sign-form-title">회원가입</div>

                {/* 아이디 입력 */}
                {/* 아이디 입력 시 중복 체크 필요 */}
                <div className="sign-form-group">
                    <input className="sign-form-input" onChange={e=>{setUserId(e.target.value)}} type='id' id='form-userId' placeholder='아이디를 입력해주세요.' required ></input>
                </div>

                {/* 비밀번호1 입력 */}
                {/* 비밀번호1 입력 시 양식 체크 필요 */}
                <div className='sign-form-group'>
                    <input className='sign-form-input' onChange={e=>{setPassword1(e.target.value)}} type='password' id='form-password1' placeholder='비밀번호를 입력해주세요.'></input>
                </div>

                {/* 비밀번호2 입력 */}
                {/* 비밀번호1과 동일한지 체크 필요 */}
                <div className='sign-form-group'>
                    <input className='sign-form-input' onChange={e=>{setPassword2(e.target.value)}} type='password' id='form-password2' placeholder="비밀번호를 다시 입력해주세요."></input>
                </div>

                {/* 닉네임 입력 */}
                {/* nullable */}
                <div className='sign-form-group'>
                    <input className='sign-form-input' onChange={e=>{setName(e.target.value)}} type='text' id='form-name' placeholder='회원님을 알아볼 수 있는 이름을 입력해주세요.'></input>
                </div>

                {/* 소개글 입력 */}
                {/* nullable */}
                <div className='sign-form-group'>
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

                {/* Signup 버튼 */}
                <button onClick={handleSubmit} className='signup-submit-button' type='submit'>
                    SIGNUP
                </button>
            </form>
        </div>
    )
}

export default SignupForm;