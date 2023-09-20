import { useState } from "react";
import { useSelector } from 'react-redux';

import './MainPage.css';
import SelectItemBox from "../components/SelectItemBox";
import NavBarMain from "../components/ui/NavBarMain";

function MainPage() {
    const [isItemBox,setIsItemBox] = useState(false);
    let user = useSelector((state) => state.user);

    const handleBox  = ()=>{
        setIsItemBox(!isItemBox)
        console.log(isItemBox)
    }
    return (
        <>
            {
                !user.isLogin ? (
                    <div>
                        <NavBarMain></NavBarMain>
                        <h1>메인페이지 예정입니다.</h1>
                    </div>
                ) : (
                    <div>{user.userId}</div>
                )
            }

            {!isItemBox && <div className="rectangle-false" onClick={handleBox}></div>}
            {isItemBox && 
            
            <div className='test1'>
                <div><SelectItemBox/></div>
                <div className="rectangle-true" onClick={handleBox}></div>
            </div>
            }
        </>
    );
    }

export default MainPage;