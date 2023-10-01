import "./LoginPage.css"
import LocalLoginForm from "../components/form/LocalLoginForm";
import SocialLoginForm from "../components/form/SocialLoginForm";


function LoginPage() {
    return (
        <div className="login-page-container">
            <LocalLoginForm></LocalLoginForm>
            <SocialLoginForm></SocialLoginForm>
        </div>
    );
}

export default LoginPage;