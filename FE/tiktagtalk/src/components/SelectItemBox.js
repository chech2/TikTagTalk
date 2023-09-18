import React from 'react';
import './SelectItemBox.css';

function SelectItemBox(props) {
  const items = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13];

  return (
    <div className='select4'>
      {items.map((item, index) => (
        <p key={index}>{item}</p>
      ))}
    </div>
  );
}

export default SelectItemBox;
