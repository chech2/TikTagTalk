import AppBar from "../components/ui/AppBar";
import "./ExchangePage.css";
import { customAxios } from "../CustomAxios";
import { useEffect } from "react";
// import { useState } from "react";
import { useSelector } from "react-redux";
import { useState } from "react";
// import Modal from '../components/ui/Modal';
// import ItemModal from '../components/ItemModal';

// 내 소비태그에 변화가 생길 때 마다 axios 요청을 보내서 img url 따와서 박스 만들기
// 나를 추가한 것에 차이가 생길 때 마다

function ExchangePage(props) {
  let user = useSelector((state) => state.user);
  const [Data, setData] = useState();
  const [inputValue, setinputValue] = useState("1");
  const handleInputChange = (e) => {
    setinputValue(e.target.value); // 입력 필드의 값 변경 시 상태 업데이트
  };
  const calculatedValue = parseFloat(inputValue) * 1344;
  const handleBuySkin = () => {
    console.log();
  };
  useEffect(() => {
    customAxios
      .get(process.env.REACT_APP_BASE_URL + "/exchange/buy/exchange-rates?receiveCountry=KRW")
      .then((response) => {
        setData(response.data);
        console.log(response);
        const data = response.data;
        console.log(data);
      })
      .catch((error) => {
        console.error("Error fetching contracts:", error);
      });
  }, [Data]);
  return (
    <>
      <div>
        <AppBar title="환전 페이지" id={user.id}></AppBar>
      </div>
      <div className="exchange-container">
        <div className="exchange-container-33">
          <img
            className="exchange-responsive-image"
            src={`/avatar/type${user.avatarType}.jpg`}
            alt=""
          />
        </div>
        <div className="exchange-container-33">{user.userId}</div>
        <div className="exchange-container-33">
          <div>
            <div className="exchange-container-33-2">
              <img className="exchange-responsive-image2" src="./Icon/포인트 아이콘.png" alt="" />
              <div>{user.point}</div>
            </div>
            <div className="exchange-container-33-2">
              <img className="exchange-responsive-image2" src="./Icon/Coin.png" alt="" />
              <div>{user.coin}</div>
              {/* <img className='exchange-responsive-image2' src="./Icon/코인 구매창.png" alt="" /> */}
            </div>
          </div>
        </div>
      </div>
      <div>오늘의 환율</div>
      <hr />
      <div className="exchange-coin-point">
        <input className="coin-input" value={inputValue} onChange={handleInputChange} min="1" />
        <img className="exchange-responsive-image2" src="./Icon/Coin.png" alt="" />=
        <div style={{ color: "000000" }} className="point-output">
          {isNaN(calculatedValue) ? Data : calculatedValue}
        </div>
        <img className="exchange-responsive-image2" src="./Icon/포인트 아이콘.png" alt="" />
        <button className="coin-button" onClick={handleBuySkin}>
          {" "}
          구매{" "}
        </button>
      </div>

      <hr />
    </>
  );
}

export default ExchangePage;
