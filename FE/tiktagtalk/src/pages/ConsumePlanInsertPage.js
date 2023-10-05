import AppBar from '../components/ui/AppBar'
import './ConsumePlanInsertPage.css'
import React, {useState,useEffect} from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router';
import { customAxios } from '../CustomAxios';
import { TextField,FormControl,InputLabel,Input,InputAdornment} from '@mui/material';


function ConsumePlanInsertPage(props) {
    const specificDate = new Date('2023-09-27'); //specificDate.getMonth()를 하면 9월이면 8이 나옴. 근데 여기서 테스트 용으로 걍 현재 9월인 걸로 생각하자

    const nowYear=specificDate.getFullYear();
    const nowMonth = specificDate.getMonth() + 2; //현재가 9월이라고 생각하니까 consumeplan을 넣어야 하는건 그 다음달이니까 이렇게 해야됨
    
    const yearAndMonthForm=(nowYear,nowMonth)=>{
        if(nowMonth<10){
            return `${nowYear}-0${nowMonth}`;
        }
        return `${nowYear}-${nowMonth}`;
    }
    const yearAndMonth=yearAndMonthForm(nowYear,nowMonth);


    const [totalAmount, setTotalAmount] = useState(0);

    // const handleTotalAmountChange = (e) => {
    //     // 입력된 값을 숫자로 변환 (문자열에서 숫자만 추출)
    //     const numericValue = e.target.innerText.replace(/[^0-9]/g, '');

    //     // 숫자만 남긴 후 입력란에 반영
    //     e.target.innerText = numericValue;

    //     // 변환된 숫자를 상태로 설정
    //     setTotalAmount(parseFloat(numericValue));
    // };

    return (
        <>
            <AppBar></AppBar>
            <div className='consumeplan-form'>
                <div className='consumeplan-nowyear-nowmonth'>
                    {nowYear}년 {nowMonth}월 예산
                </div>
                <div className="consumplan-amount-input">
                    <textarea
                        value={totalAmount}
                        onChange={(e) => setTotalAmount(() => {
                            const newTotalAmount =  e.target.value;
                            return newTotalAmount;
                        })}
                    />
                    <span>원</span>
                </div>
                
                {/* <FormControl>
                    <Input
                        endAdornment={<InputAdornment position="end">원</InputAdornment>}
                    />
                </FormControl> */}
                
            </div>

        </>
    );
}
export default ConsumePlanInsertPage;
