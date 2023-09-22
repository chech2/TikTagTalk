import NavBarMain from "../components/ui/NavBarMain";
import { useState } from "react";
import './MainPage.css';
import SelectItemBox from "../components/SelectItemBox";
import TagRoom from '../TagRoom'



function MainPage() {
    // const [isItemBox,setIsItemBox] = useState(false)
    // const handleBox  = ()=>{
    //     setIsItemBox(!isItemBox)
    //     console.log(isItemBox)
    // }
    return (
        <>
        <div>
            <NavBarMain></NavBarMain>
            <h1>메인페이지 예정입니다.</h1>
            {/* <TagRoom></TagRoom> */}
        </div>
{/* 
        {!isItemBox && <div className="rectangle-false" onClick={handleBox}></div>}
        {isItemBox && 
        
        <div className='test1'>
            <div><SelectItemBox/></div>
            <div className="rectangle-true" onClick={handleBox}></div>
        </div>
        } */}
        </>
      );
    }

export default MainPage;