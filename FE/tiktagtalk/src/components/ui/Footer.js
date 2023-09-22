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
    return (
        <>
        <div className='footercontainer'>
            <img src="/Icon/Home.png" alt="" onClick={handleHomeClick} />
            <img src="/Icon/Group.png" alt="" onClick={handleAchievementClick} />
            <img src="/Icon/Social.png" alt="" onClick={handleFriendClick} />
            <img src="/Icon/마이페이지 아이콘.png" alt="" />
        </div>
        </>
      );
    }

export default Footer;