import kakao_login_button from "./button/kakao_login_button.png"

function KakaoLogin() {
    return (
        <div>
            <a href = {process.env.REACT_APP_BASE_URL + "/oauth2/authorization/kakao"}>
                <img className="login-button" src={kakao_login_button} alt="카카오 계정으로 로그인"></img>
            </a>
        </div>
    )
}

export default KakaoLogin;