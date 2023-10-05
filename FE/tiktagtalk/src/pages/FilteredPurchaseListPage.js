import { useEffect,useState } from 'react';
import AppBar from '../components/ui/AppBar';
import './FilteredPurchaseListPage.css'
import { useParams, useLocation } from "react-router-dom";
import { customAxios } from '../CustomAxios';
// import { useState } from "react";
// import { useSelector } from 'react-redux';
// import Modal from '../components/ui/Modal';
// import ItemModal from '../components/ItemModal';

function FilteredPurchaseListPage(props) {

    const location = useLocation();
    const mymonth  = location.state || {};
    const {tag} = useParams();
    const [dataset,setdataset] = useState([])
    const year = mymonth.mymonth.slice(0,4)
    const month = mymonth.mymonth.slice(5,7)


    const currentDate = new Date();

    // 선택할 수 없는 최소 날짜와 최대 날짜 계산
    const minDate = new Date(currentDate.getFullYear(), month - 1, 1); // 선택 가능한 최소 날짜 (targetMonth - 1은 배열과 월의 차이를 조정합니다)
    const maxDate = new Date(currentDate.getFullYear(), month, 0); // 선택 가능한 최대 날짜 (0은 targetMonth 이전 달의 마지막 날을 의미합니다)

    // React 상태를 사용하여 최소 날짜와 최대 날짜 설정
    const [minDateValue, setMinDateValue] = useState(minDate.toISOString().slice(0, 10));
    const [maxDateValue, setMaxDateValue] = useState(maxDate.toISOString().slice(0, 10));



    useEffect(()=>{

        // console.log('filter창 month', mymonth.mymonth)
        // console.log('bill',mymonth.bill)
        let body = {'yearAndMonth' : `${mymonth.mymonth}`}
        customAxios
        .post(process.env.REACT_APP_BASE_URL + '/consume/highest', body)
        .then((res) => {
            console.log('데이터셋예정임',res.data)
            setdataset(res.data)

        })
        .catch((error) => {
          console.log('거래내역 에러', error);
        });
    },[])
    const handleAddDeal = ()=>{
        console.log(1)
    }

    return (
        <>
            <div>
                <AppBar title= {tag +' 소비내역'}></AppBar>
                <div>{year}년 {month}월</div>
                <div>{tag} 소비내역 총 금액</div>
                <div>{mymonth.bill} 원</div>
                {/* <div>총 {}회 결제</div> */}
                <div>거래내역 <button onClick={handleAddDeal}> + </button> </div>
                
                <div className='buy-regist'>
                    <div>
                        <label htmlFor="selectDate">날짜 선택:</label>
                        <input type="date" id="selectDate" name="selectDate" min={minDateValue} max={maxDateValue} />
                    </div>
                    <div>
                        <label htmlFor="textInput">소비내역</label>
                        <input type="text" id="textInput" name="textInput" />
                    </div>
                    <div>
                        <label htmlFor="numberInput">금액</label>
                        <input type="number" id="numberInput" name="numberInput" />
                    </div>
                </div>

                {dataset.map((item,index)=>{
                    if (item.tag.name === tag) {
                        return (
                          <div key={index}>{item.tag.name}</div>
                        );
                    }
                return null
            })}

            </div>

        </>
    );
}

export default FilteredPurchaseListPage;
