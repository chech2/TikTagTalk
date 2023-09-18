import naver_login_button from "./button/naver_login_button.png"

function NaverLogin() {
    return (
        <div>
            <a href = {process.env.REACT_APP_BASE_URL + "/oauth2/authorization/naver"}>
                <img className="login-button" src={naver_login_button} alt="네이버 계정으로 로그인"></img>
            </a>
        </div>
    )
}

export default NaverLogin;