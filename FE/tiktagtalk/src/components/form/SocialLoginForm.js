import "./SocialLoginForm.css"
import 'animate.css';

import google_logo from "./button/google_logo.png"
import naver_logo from "./button/naver_logo.png"
import kakao_logo from "./button/kakao_logo.png"

const SocialLoginForm = () => {
    return (
        <div className="social-login-form animate__animated animate__bounceInUp animate__slower">
            <button className="social-login-button google-login-button" onClick={() => window.location.href = `${process.env.REACT_APP_BASE_URL}/oauth2/authorization/google`}>
                <img src={google_logo}></img>
                <span>구글로 로그인</span>
            </button>
            <button className="social-login-button naver-login-button" onClick={() => window.location.href = `${process.env.REACT_APP_BASE_URL}/oauth2/authorization/naver`}>
                <img src={naver_logo}></img>
                <span>네이버로 로그인</span>
            </button>
            <button className="social-login-button kakao-login-button" onClick={() => window.location.href = `${process.env.REACT_APP_BASE_URL}/oauth2/authorization/kakao`}>
                <img src={kakao_logo}></img>
                <span>카카오로 로그인</span>
            </button>
        </div>
    )
}

export default SocialLoginForm;