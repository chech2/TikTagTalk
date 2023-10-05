import React, { useState, useEffect } from "react";
import { customAxios } from "../CustomAxios";
import { useParams } from "react-router-dom";
import "./DebtDetailPage.css";
import AppBar from "../components/ui/AppBar";

/*

private long id;
        private String status;
        private String debtorName;
        private String lenderName;
        private String money;
        private long remainingMoney;
        private int partialPay;
        private LocalDate createTime;
        private LocalDate repaymentTime;
        private List<RepaymentHistory> repaymentHistoryList;
        private List<ExtendHistory> extendHistoryList;
    }
    id, status, debtorName, lenderName, money, remainingMoney, partialPay, createTime, repaymentTime, repaymentHistoryList, extendHistoryList
*/

function DebtDetailItem() {
  const [Data, setData] = useState();
  const { id } = useParams();

  // useEffect를 사용하여 모달 창이 열릴 때마다 GET 요청을 보냅니다.
  useEffect(() => {
    customAxios
      .get(process.env.REACT_APP_BASE_URL + `/debts/detail?debtId=${id}`)
      .then((response) => {
        setData(response.data);
        console.log("디테일 확인: ", response);
        console.log("디테일 확인2: ", response.data);
      })
      .catch((error) => {
        console.error("Error fetching contracts:", error);
        console.log(id);
        console.log(error);
      });
  }, [id]); // page나 size가 바뀔 때마다 API 요청을 다시 보냅니다

  return (
    <div style={{ width: "100vw", display: "flex" }}>
      <AppBar title="차용증 상세내역"></AppBar>

      <div
        className="debt-list-image-container"
        style={{ backgroundImage: "url(/Icon/차용증상세내역.png)" }}
      >
        {Data && (
          <div>
            <p>ID: {Data.id}</p>
            <p>Status: {Data.status}</p>
            <p>Debtor Name: {Data.debtorName}</p>
            {/* 나머지 데이터도 이런 식으로 표시 */}
            {/* ... */}
          </div>
        )}
      </div>
    </div>
  );
}

export default DebtDetailItem;
