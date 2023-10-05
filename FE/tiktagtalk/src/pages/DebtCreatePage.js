import { useState } from "react";
import { customAxios } from "../CustomAxios";
import "./DebtCreatePage.css";

import { IconButton } from "@mui/material";
import { ArrowBackIosNew } from "@mui/icons-material";
import { useSelector } from "react-redux";

function DebtCreatePage() {
  const [money, setMoney] = useState("");
  const [lenderId, setLenderId] = useState("");
  const [repaymentTime, setRepaymentTime] = useState("");
  const [partialPay, setPartialPay] = useState("");
  const [id, setId] = useState(null);
  const userName = useSelector((state) => state.user.userId);

  function getToday() {
    const today = new Date();
    let month = "" + (today.getMonth() + 1);
    let day = "" + today.getDate();
    const year = today.getFullYear();

    if (month.length < 2) month = "0" + month;
    if (day.length < 2) day = "0" + day;

    return [year, month, day].join("-");
  }

  const goBack = () => {
    window.history.back();
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await customAxios
        .post(process.env.REACT_APP_BASE_URL + `/debts`, {
          // 엔드포인트에 맞게 수정해주세요
          money,
          lenderId,
          repaymentTime,
          partialPay,
        })
        .then((response) => {
          setId(response.data.id);
          console.log(response);
          window.history.back();
        });
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="debt-create-root-container">
      <div className="create-debt-appbar-container">
        <IconButton style={{ color: "00000" }} className="go-back-icon" onClick={goBack}>
          <ArrowBackIosNew />
        </IconButton>
      </div>
      <div
        className="debt-create-image-container"
        style={{ backgroundImage: "url(/Icon/차용증리스트.png)" }}
      ></div>
      <form onSubmit={handleSubmit}>
        <h1>차용증</h1>
        {userName}님이
        <label htmlFor="money">빌린 금액: </label>
        <input
          type="number"
          id="money"
          name="money"
          value={money}
          onChange={(e) => setMoney(e.target.value)}
        />
        <br />
        <label htmlFor="lenderId">채권자 ID:</label>
        <br />
        <input
          type="number"
          id="lenderId"
          name="lenderId"
          value={lenderId}
          onChange={(e) => setLenderId(e.target.value)}
        />
        <br />
        <label htmlFor="repaymentTime">상환일자:</label>
        <br />
        <input
          type="date"
          id="repaymentTime"
          name="repaymentTime"
          min={getToday()}
          value={repaymentTime}
          onChange={(e) => setRepaymentTime(e.target.value)}
        />
        <br />
        부분 상환 가능 여부
        <br />
        가능
        <input
          type="radio"
          id="partialPayYes"
          name="partialPay"
          value="1"
          checked={partialPay === "1"}
          onChange={(e) => setPartialPay(e.target.value)}
        />
        불가능
        <input
          type="radio"
          id="partialPayNo"
          name="partialPay"
          value="0"
          checked={partialPay === "0"}
          onChange={(e) => setPartialPay(e.target.value)}
        />
        <br />
        <input type="submit" value="Submit" />
      </form>
    </div>
  );
}

export default DebtCreatePage;
