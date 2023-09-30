import './StartPage.css';

import { useNavigate } from 'react-router-dom'
import 'animate.css';

import logo1 from "./logo/TikTagTalk_1.png";
import logo2 from "./logo/TikTagTalk_2.png";
import logo3 from "./logo/TikTagTalk_3.png";

function StartPage() {
    const navigator = useNavigate()
    const handleLogin = ()=>{
        navigator('/login')
    }
    return (
        <div className="start-page-container">
            <div className="start-logo-container">
                <img src={logo3} className="animate__animated animate__fadeInRight start-logo"></img>
                <img src={logo2} className="animate__animated animate__fadeInBottomLeft start-logo"></img>
                <img src={logo1} className="animate__animated animate__fadeInTopLeft start-logo"></img>
            </div>
            <button onClick={handleLogin} className="animate__animated animate__bounceIn animate__delay-1s start-login">로그인하러 가기</button>
        </div>
    );
}

export default StartPage;
