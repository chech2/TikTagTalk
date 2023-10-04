import React, { useState, useEffect } from 'react';
import axios from 'axios'; 

function DebtLsitItem({ contract }) {
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
  
  export default DebtLsitItem;