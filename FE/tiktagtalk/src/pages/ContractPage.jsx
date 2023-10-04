import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ContractPage.css'; 

function ContractItem({ contract }) {
  const [isPaid, setIsPaid] = useState(false);
  const [showPopup, setShowPopup] = useState(false);

  const handlePayClick = () => {
    setIsPaid(true);
    setShowPopup(false);
  };

  const handleCancelClick = () => {
    setShowPopup(false);
  };

  return (
    <div className="contract-box">
      <div 
        className={`contract-content ${isPaid ? 'paid' : ''}`} 
        onClick={() => !isPaid && setShowPopup(true)}
      >
        {contract.title}
        {isPaid && <div className="paid-label">납부완료</div>}
      </div>

      {showPopup && (
        <div className="popup">
          <button onClick={handlePayClick}>납부완료</button>
          <button onClick={handleCancelClick}>취소</button>
        </div>
      )}
    </div>
  );
}

function Contract() {
  const [contracts, setContracts] = useState([]);
  
  // API로부터 계약 정보 가져오기
  useEffect(() => {
    axios.get('http://localhost:8080/api/contract')  // 실제 API endpoint 필요
      .then(response => {
        setContracts(response.data);
      })
      .catch(error => {
        console.error('Error fetching contracts:', error);
      });
  }, []);

  return (
    <div className="Contract">
      {contracts.map((contract, index) => <ContractItem key={index} contract={contract} />)}
    </div>
  );
}

export default Contract;
