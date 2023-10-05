import React, { useState } from 'react';

function DropdownSelect({onOptionSelect}) {
  const option = ['식비','편의점·마트·잡화','교통·자동차','쇼핑','카페·간식','보험·세금·기타금융','취미·여가','미용','의료·건강·피트니스','정기결제','반려동물','여행','기타']
  const [selectedOptionIndex, setSelectedOptionIndex] = useState(null);

  const handleChange = (event) => {
    const selectedIndex = event.target.selectedIndex;
    setSelectedOptionIndex(selectedIndex);
    onOptionSelect(selectedIndex)
  };

  return (
    <div>
      <select onChange={handleChange}>
        <option value="" disabled selected>카테고리를 선택하세요</option>
        {option.map((item, index) => (
          <option key={index} value={item}>
            {item}
          </option>
        ))}
      </select>
      {/* <p>Selected Value: {selectedOptionIndex !== null ? selectedOptionIndex : "No option selected"}</p> */}
    </div>
  );
}

export default DropdownSelect;