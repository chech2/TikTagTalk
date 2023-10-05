import React, { useState,useEffect } from 'react';
import './DropdownMenu.css'; // 스타일 파일을 import

import { IconButton } from '@mui/material';
import { ArrowLeft, ArrowRight } from '@mui/icons-material';

function DropdownMenu({monthData}) {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedOption, setSelectedOption] = useState('Select an option');

  const currentDate = new Date();
  const year = currentDate.getFullYear();
  const month = currentDate.getMonth() + 1;
  
  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const handleOptionSelect = (option) => {
    setSelectedOption(option);
    const month = option
    monthData(month);
    setIsOpen(false);
  };

  return (
    <div className="year-month-selector-container">
      {/* <button className="dropdown-button" onClick={toggleDropdown}>
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
      )} */}
    </div>
  );
}

export default DropdownMenu;
