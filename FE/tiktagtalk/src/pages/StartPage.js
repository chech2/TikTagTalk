
// import './CoinPurchasePage.css'

import { useNavigate} from 'react-router-dom'

function StartPage() {
    const navigator = useNavigate()
    const handleLogin = ()=>{
        navigator('/login')
    }
    return (
        <>
            <div>
                <button onClick={handleLogin}>로그인하러가기</button>
            </div>

        </>
    );
}

export default StartPage;
