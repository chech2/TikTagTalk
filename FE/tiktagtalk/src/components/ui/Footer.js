import React from "react";
import "./Footer.css";
import { useNavigate} from 'react-router-dom'

function Footer() {
    const navigate = useNavigate()
    const handleHomeClick = ()=>{
        navigate('/')
    }
    const handleFriendClick =()=>{
        navigate('./friend-list')
    }
    const handleAchievementClick=()=>{
        navigate('./achievement')
    }
    const handleExchangeClick =()=>{
        navigate('./exchange')
    }
    return (
        <>
        <div className='footercontainer'>
            <img className="responsive-image" src="/Icon/Home.png" alt="" onClick={handleHomeClick} />
            <img className="responsive-image" src="/Icon/Group.png" alt="" onClick={handleAchievementClick} />
            <img className="responsive-image" src="/Icon/Social.png" alt="" onClick={handleFriendClick} />
            <img className="responsive-image" src="/Icon/마이페이지 아이콘.png" alt="" onClick={handleExchangeClick}/>
        </div>
        </>
      );
    }

export default Footer;