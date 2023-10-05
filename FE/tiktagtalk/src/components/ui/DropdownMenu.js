import React, { useState,useEffect } from 'react';
import './DropdownMenu.css'; // 스타일 파일을 import

function DropdownMenu({monthData}) {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedOption, setSelectedOption] = useState('');
  const [options,setoptions] = useState([])

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const handleOptionSelect = (option) => {
    setSelectedOption(option);
    const month = option
    monthData(month);
    setIsOpen(false);
  };
  useEffect(() => {
    const currentDate = new Date();


    for (let i = 0; i < 5; i++) {
      const year = currentDate.getFullYear();
      const month = currentDate.getMonth() + 1; // 월은 0부터 시작하므로 1을 더해줍니다.
      
      // 월을 두 자리 숫자로 포맷팅합니다.
      const formattedMonth = String(month).padStart(2, '0');

      options.push(`${year}-${formattedMonth}`);
      
      // 현재 날짜를 1개월 전으로 설정합니다.
      currentDate.setMonth(currentDate.getMonth() - 1);
    }
    // mymonth 상태 업데이트 후에 axios 요청을 보내도록 처리합니다.
    setoptions(options);
    setSelectedOption(`${new Date().getFullYear()}-${String(new Date().getMonth()).padStart(2, '0')}`);
  }, []); // 빈 배열을 전달하여 이펙트가 한 번만 실행되도록 합니다.


  // const options = ['2023-09','2023-08', '2023-07', '2023-06', '2023-05'];

  return (
    <div className="dropdown-container">
      <button className="dropdown-button" onClick={toggleDropdown}>
        {selectedOption}
        <span className={isOpen ? 'arrow-up' : 'arrow-down'}></span>
      </button>
      {isOpen && (
        <ul className="dropdown-menu">
          {options.map((option, index) => (
            <li
              key={index}
              className="dropdown-item"
              onClick={() => handleOptionSelect(option)}
            >
              {option}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default DropdownMenu;
