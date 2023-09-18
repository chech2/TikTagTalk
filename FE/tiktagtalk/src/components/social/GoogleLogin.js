import google_login_button from "./button/google_login_button.png"

function GoogelLogin() {

    return (
        <div>
            <a href= {process.env.REACT_APP_BASE_URL + "/oauth2/authorization/google"}>
                <img className="login-button" src={google_login_button} alt="Google 계정으로 로그인"></img>
            </a>
        </div>
    )
}

export default GoogelLogin;