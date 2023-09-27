import React,{useEffect} from "react";
import "./Footer.css";
import { useNavigate} from 'react-router-dom'
import { useSelector } from "react-redux";

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHouse, faUser, faUsersRays, faAward, faMoneyBillWave } from "@fortawesome/free-solid-svg-icons";


function Footer() {
    const navigate = useNavigate()
    const myimage = useSelector((state)=>state.user.avatarType);
    const id = useSelector((state)=>state.user.id);
    
    const handleConsumeClick = ()=>{
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
        <nav className="footercontainer">
            <div className="nav-navigate">
                <FontAwesomeIcon 
                    icon={faMoneyBillWave} 
                    onClick={handleExchangeClick}
                />
            </div>

            <div className="nav-navigate">
                <FontAwesomeIcon 
                    icon={faAward} 
                    onClick={handleAchievementClick}
                />
            </div>

            <div className="nav-navigate">
                <FontAwesomeIcon 
                    icon={faHouse} 
                    onClick={handleHomeClick}
                />
            </div>

            <div className="nav-navigate">
                <FontAwesomeIcon 
                    icon={faUsersRays} 
                    onClick={handleFriendClick}
                />
            </div>

            <div className="nav-navigate">
                <FontAwesomeIcon 
                    icon={faUser} 
                    onClick={handleConsumeClick}
                />
            </div>
        </nav>
        </>
      );
    }

export default Footer;