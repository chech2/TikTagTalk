import React from "react";
import axios from "axios";
import "./DebtListItem.css";

function DebtListItem({ contract, mode }) {
  console.log(mode);
  const statusMap = {
    REQUESTING: { label: "승인 대기", color: "#009999" },
    DENIED: { label: "승인 거부", color: "#FFCC00" },
    ACTIVE: { label: "상환 중", color: "#0033FF" },
    INAVTIVE: { label: "상환 완료", color: "#33FF00" },
    ARREARS: { label: "체납", color: "#FF3300" },
  };

  // const handleIdClick = async () => {
  //   try {
  //     const response = await axios.get(`http://localhost:8080/api/debts/${contract.id}`);
  //     console.log(response.data);
  //   } catch (error) {
  //     console.error("Error fetching contract:", error);
  //   }
  // };

  return (
    <div className="debt-list-item">
      <div
        className="debt-list-item-status-container"
        style={{ color: statusMap[contract.status].color }}
      >
        {statusMap[contract.status].label}
      </div>
      <br />
      <div className="debtors-lenders">
        {mode === 1 ? ( //채무자
          <div className="debt-list-content-container">
            {contract.lenderName}님께 잔액: {contract.remainingMoney}원을 갚아야 합니다.
          </div>
        ) : (
          <div className="debt-list-content-container">
            {contract.debtorName}님께 {contract.remainingMoney}원을 더 받아야 합니다.
          </div>
        )}
      </div>
      <div className="debt-list-remaining-container">남은 금액: {contract.remainingMoney}</div>
    </div>
  );
}

export default DebtListItem;
