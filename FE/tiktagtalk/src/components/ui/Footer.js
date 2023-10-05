import React, { useEffect, useState } from "react";
import "./Footer.css";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHouse, faUser, faUsersRays, faMoneyBillWave } from "@fortawesome/free-solid-svg-icons";

function Footer() {
  const navigate = useNavigate();
  const myimage = useSelector((state) => state.user.avatarType);
  const id = useSelector((state) => state.user.id);

  // mode 상태 변수 (기본값: 0)
  const [mode, setMode] = useState(0);

  // 추가: Drop Down 메뉴의 보이고/안 보이고 상태 (기본값: false)
  const [showDropDown, setShowDropDown] = useState(false);

  const handleConsumeClick = () => {
    navigate("./consume-pattern");
  };
  const handleFriendClick = () => {
    navigate("./friend-list");
  };
  const handleHomeClick = () => {
    navigate(`/main/${id}`);
  };
  const handleDebtClick = () => {
    navigate("./debts/list");
  };

  return (
    <>
      <nav className="footercontainer">
        <div className="nav-navigate">
          <FontAwesomeIcon icon={faHouse} onClick={handleHomeClick} />
        </div>

        <div className="nav-navigate">
          <FontAwesomeIcon icon={faUsersRays} onClick={handleFriendClick} />
        </div>

        <div className="nav-navigate">
          <FontAwesomeIcon icon={faUser} onClick={handleConsumeClick} />
        </div>

        <div className="nav-navigate">
          <FontAwesomeIcon icon={faMoneyBillWave} onClick={handleDebtClick} />
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
