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
  console.log(useSelector((state) => state.user));
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
      <form onSubmit={handleSubmit} style={{ fontSize: "1.5em" }}>
        <h1 style={{ marginBottom: "30px" }}>차용증</h1> <br />
        <br />
        {userName}가<br></br>
        <label htmlFor="lenderId" style={{ marginTop: "20px" }}>
          채권자 ID:
        </label>
        <input
          type="number"
          id="lenderId"
          name="lenderId"
          value={lenderId}
          onChange={(e) => setLenderId(e.target.value)}
          style={{ margin: "10px 0", backgroundColor: "rgba(255,255,255,0.5)" }}
        />
        에게
        <br></br>
        {/* <label htmlFor="money">빌린 금액: </label> */}
        <input
          type="number"
          id="money"
          name="money"
          value={money}
          onChange={(e) => setMoney(e.target.value)}
          style={{ margin: "10px 0", backgroundColor: "rgba(255,255,255,0.5)" }}
        />
        원을 빌림
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <label htmlFor="repaymentTime" style={{ margin: "10px 0" }}>
          상환일:{" "}
        </label>
        <input
          type="date"
          id="repaymentTime"
          name="repaymentTime"
          min={getToday()}
          value={repaymentTime}
          onChange={(e) => setRepaymentTime(e.target.value)}
          style={{ margin: "10px 0", backgroundColor: "rgba(255,255,255,0.5)" }}
        />
        <br />
        <br />
        나눠서 갚아도 됨?ㅋ
        <br />
        가능
        <input
          type="radio"
          id="partialPayYes"
          name="partialPay"
          value="1"
          checked={partialPay === "1"}
          onChange={(e) => setPartialPay(e.target.value)}
          style={{ margin: "10px 0", backgroundColor: "rgba(255,255,255,0.5)" }}
        />{" "}
        불가능
        <input
          type="radio"
          id="partialPayNo"
          name="partialPay"
          value="0"
          checked={partialPay === "0"}
          onChange={(e) => setPartialPay(e.target.value)}
          style={{ margin: "10px 0", backgroundColor: "rgba(255,255,255,0.5)" }}
        />
        <br />
        <input
          type="submit"
          value="돈 빌리기"
          style={{
            backgroundColor: "rgba(255,255,255,0.5)",
            fontSize: "16px",
            fontFamily: "Her-Leeoksun",
          }}
        />
      </form>
    </div>
  );
}

export default DebtCreatePage;
