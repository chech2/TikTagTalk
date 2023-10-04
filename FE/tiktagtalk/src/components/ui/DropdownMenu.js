import React, { useState,useEffect } from 'react';
import './DropdownMenu.css'; // 스타일 파일을 import

function DropdownMenu({monthData}) {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedOption, setSelectedOption] = useState('Select an option');

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const handleOptionSelect = (option) => {
    setSelectedOption(option);
    const month = option
    monthData(month);
    setIsOpen(false);
  };



  const options = ['2023-8', '2023-7', '2023-6', '2023-5'];

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
