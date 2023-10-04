import React, { useEffect, useState } from "react";
import axios from "axios";
import './DebtPage.css'; 
import DebtLsitItem from "../components/DebtlistItem";  // 컴포넌트 import 필요

function DebtListPage() {
  const [contracts, setContracts] = useState([]);
  const [page, setPage] = useState(0);  // 페이지 번호 (기본값: 0)
  const [size, setSize] = useState(10); // 한 페이지에 보여줄 항목 수 (기본값: 10)  
  const [mode, setMode] = useState(''); // 모드 상태 변수 추가

  // 유저의 차용증 내역 불러오기
  useEffect(() => {
    axios.get(`http://localhost:8080/api/debts/list?mode=${mode}&page=${page}&size=${size}`)  
      .then(response => {
        setContracts(response.data);
      })
      .catch(error => {
        console.error('Error fetching contracts:', error);
      });
    }, [page, mode]);   // page나 size가 바뀔 때마다 API 요청을 다시 보냅니다

    const handleNextPage = () => {   // 다음 페이지로 이동하는 함수
        setPage(prevPage => prevPage + 1);
    }

    const handlePrevPage = () => {   // 이전 페이지로 이동하는 함수
        setPage(prevPage => Math.max(prevPage - 1, 0)); 
        /* page 값이 음수가 되지 않도록 Math.max 함수 사용 */
    }

    return (
        <div className="Contract">
            {contracts.map((contract, index) =>
                <DebtLsitItem key={index} contract={contract} />
            )}
            <button onClick={handlePrevPage}>이전</button>
            <button onClick={handleNextPage}>다음</button>
        </div>
    );
}

export default DebtListPage;
