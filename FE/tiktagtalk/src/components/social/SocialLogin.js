import GoogelLogin from "./GoogleLogin";
import NaverLogin from "./NaverLogin";
import KakaoLogin from "./KakaoLogin";

const SocialLogin = () => {
    return (
        <div>
            <GoogelLogin></GoogelLogin>
            <NaverLogin></NaverLogin>
            <KakaoLogin></KakaoLogin>
        </div>
    )
}

export default SocialLogin;