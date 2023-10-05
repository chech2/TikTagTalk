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
    const handleContractClick=()=>{
        navigate('./contract')
    }


    
    return (
        <>
        <nav className="footercontainer">

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

            <div className="nav-navigate">
                <FontAwesomeIcon 
                    icon={faMoneyBillWave} 
                    onClick={handleContractClick}
                />
            </div>

        </nav>
        {/* <div className='footercontainer'>
            <img className="responsive-image" src="/Icon/마이페이지 아이콘.png" onClick={handleConsumeClick} alt="" />
            <img className="responsive-image" src="/Icon/Group.png" alt="" onClick={handleAchievementClick} />
            <img className="responsive-image" src="/Icon/Home.png" alt="" onClick={handleHomeClick} />
            <img className="responsive-image" src="/Icon/Social.png" alt="" onClick={handleFriendClick} />
        </div> */}
        </>
      );
    }

export default Footer;