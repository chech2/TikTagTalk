import React from "react";
import "./Footer.css";
import { useNavigate} from 'react-router-dom'

function Footer() {
    const navigate = useNavigate()
    const handleHomeClick = ()=>{
        navigate('/')
    }
    return (
        <>
        <div className='footercontainer'>
            <img src="/Icon/Home.png" alt="" onClick={handleHomeClick} />
            <img src="/Icon/Group.png" alt="" />
            <img src="/Icon/Social.png" alt="" />
            <img src="/Icon/마이페이지 아이콘.png" alt="" />
        </div>
        </>
      );
    }

export default Footer;