import React,{useEffect} from "react";
import "./Footer.css";
import { useNavigate} from 'react-router-dom'
import { useSelector } from "react-redux";

function Footer() {
    const navigate = useNavigate()
    const myimage = useSelector((state)=>state.user.avatarType);
    const id = useSelector((state)=>state.user.id);
    
    const handleConsumClick = ()=>{
        navigate('./consume-pattern')
    }
    const handleFriendClick =()=>{
        navigate('./friend-list')
    }
    const handleHomeClick = ()=>{
        navigate(`/main/${id}`)
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
            <img className="responsive-image" src="/Icon/마이페이지 아이콘.png" onClick={handleConsumClick} alt="" />
            <img className="responsive-image" src="/Icon/Group.png" alt="" onClick={handleAchievementClick} />
            <img className="responsive-image" src="/Icon/Home.png" alt="" onClick={handleHomeClick} />
            <img className="responsive-image" src="/Icon/Social.png" alt="" onClick={handleFriendClick} />
            <img className="responsive-image" src="/Icon/마이페이지 아이콘.png" alt="" onClick={handleExchangeClick}/>
            {/* <img src={myimage} alt="" /> */}
        </div>
        </>
      );
    }

export default Footer;