
import React, {useState, useEffect, useRef } from 'react';
import { useDispatch,useSelector  } from 'react-redux';
import {loginUser} from '../redux/userSlice'

function TestCom() {
    const dispatch = useDispatch()
    const userId = useSelector((state) => state.user.id);
    const handletest = ()=>{
        dispatch(loginUser(inputValue))
    }
    const [inputValue, setInputValue] = useState('');
    const handleInputChange = (e) => {
        setInputValue(e.target.value);
      };
    
    return (
        <>
        <div>
            <input
          type="text"
          placeholder="사용자 ID 입력"
          value={inputValue}
          onChange={handleInputChange}
            />  
            <button onClick={handletest}>테스트 버튼</button>
            <p>현재 사용자 : {userId}</p>
        </div>
        </>
      );
    }

export default TestCom;