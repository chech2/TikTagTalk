import React, { useState, useEffect } from "react";
import { customAxios } from "../CustomAxios";
import { useParams } from "react-router-dom";
import "./DebtDetailPage.css";
import AppBar from "../components/ui/AppBar";

function DebtDetailItem() {
  const [Data, setData] = useState();
  const { id, mode } = useParams();
  const statusMap = {
    REQUESTING: { label: "승인 대기", color: "#009999" },
    DENIED: { label: "승인 거부", color: "#FFCC00" },
    ACTIVE: { label: "상환 중", color: "#0033FF" },
    INAVTIVE: { label: "상환 완료", color: "#33FF00" },
    ARREARS: { label: "체납", color: "#FF3300" },
  };

  console.log(id, " ", mode);
  // useEffect를 사용하여 모달 창이 열릴 때마다 GET 요청을 보냅니다.
  useEffect(() => {
    customAxios
      .get(process.env.REACT_APP_BASE_URL + `/debts/detail?debtId=${id}`)
      .then((response) => {
        setData(response.data);
      })
      .catch((error) => {
        console.error("Error fetching contracts:", error);
      });
  }, [id]); // page나 size가 바뀔 때마다 API 요청을 다시 보냅니다

  return (
    <div>
      <AppBar title="차용증 상세내역" style={{ zIndex: 1 }}></AppBar>

      <div className="debt-detail-root-container">
        <div
          className="debt-list-image-container"
          style={{ backgroundImage: "url(/Icon/차용증리스트.png)" }}
        >
          {Data && (
            <div>
              <div className="debt-detail-status" style={{ color: statusMap[Data.status].color }}>
                <p>{statusMap[Data.status].label}</p>
                {Data.status === "차용증 상태값" ? (
                  <button
                    className="debt-status-button"
                    onClick={() => console.log("차용증 붙이러 가기")}
                  >
                    차용증 붙이러 가기
                  </button>
                ) : (
                  <button className="debt-status-button" onClick={() => console.log("상환하기")}>
                    상환하기
                  </button>
                )}
              </div>
              <h1 className="debt-detail-name ">차용증</h1>

              <p>
                {Data.debtorName}님이 {Data.lenderName}님께 {Data.money}원을 빌리셨습니다.
              </p>

              <div className="debt-content-container">
                <div>
                  <p>생성일: {Data.createTime}</p>
                  <p>만기일: {Data.repaymentTime}</p>
                </div>

                <div>
                  <p>채무자: {Data.debtorName}</p>
                  <p>채권자: {Data.lenderName}</p>
                  <p>남은 상환금: &#8361;{Data.remainingMoney}</p>
                </div>
              </div>

              {/* Repayment History */}
              {Data.repaymentHistoryList.map((history, index) => (
                <div key={index}>
                  {/* 필요한 정보를 여기에 출력하세요. 예: */}
                  <p>{history.date}</p>
                  <p>{history.amount}</p>
                </div>
              ))}

              {/* Extend History */}
              {Data.extendHistoryList.map((history, index) => (
                <div key={index}>
                  <p>{history.date}</p>
                  <p>{history.extensionLength}</p>
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default DebtDetailItem;
// {Data && (
//   <div>
//     <p>{Data.status}</p>
//     <p>Debtor Name: {Data.debtorName}</p>
//     <p>lenderName Name: {Data.lenderName}</p>
//     <p>빌린 금액: {Data.money}</p>
//     <p>상환 예정 금액: {Data.remainingMoney}원s</p>
//     <p>부분 상환 가능 여부: {Data.partialPay}</p>
//     <p>생성일자: {Data.createTime}</p>
//     <p>만기일: {Data.repaymentTime}</p>

//     {Data.repaymentHistoryList.map((history, index) => (
//       <div key={index}>
//         {/* 필요한 정보를 여기에 출력하세요. 예: */}
//         <p>{history.date}</p>
//         <p>{history.amount}</p>
//       </div>
//     ))}

//     <h2>Extend History</h2>
//     {Data.extendHistoryList.map((history, index) => (
//       <div key={index}>
//         <p>{history.date}</p>
//         <p>{history.extensionLength}</p>
//       </div>
//     ))}
//   </div>
// )}
