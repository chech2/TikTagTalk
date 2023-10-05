import './EntirePurchaseListPage.css'
import { useState,useEffect } from 'react';
import { customAxios } from '../CustomAxios';
import DropdownSelect from '../components/ui/DropdownSelect';
import AppBar from '../components/ui/AppBar';
import { useLocation } from 'react-router';

function EntirePurchaseListPage(props) {
    const location = useLocation();
    const { year, month } = location.state2 || {};
    const mymonth  = location.state || {};
    const [dataset,setdataset] = useState([])
    const [selectedDate, setSelectedDate] = useState('');
    const [textInput, setTextInput] = useState('');
    const [numberInput, setNumberInput] = useState(null);
    const [optionIndex,setoptionIndex] = useState(null);
    const handleDateChange = (e) => {
        setSelectedDate(e.target.value);
        console.log('날짜변경:',e.target.value)
    }

    const handleTextChange = (e) => {
        setTextInput(e.target.value);
        console.log('내용변경:',e.target.value)
    }

    const handleNumberChange = (e) => {
        setNumberInput(e.target.value);
        console.log('금액변경:',e.target.value)
    }
    const handleSubmit =()=>{
        // console.log(selectedDate)
        let body = {
            'amount' :  parseInt(numberInput),
            'tag' : { 'id' : optionIndex },
            'storeName' : textInput,
            'detail' : null,
            'consumeTime' : selectedDate,
            // selectedDate
    }
        console.log(body)
        customAxios.post(process.env.REACT_APP_BASE_URL + '/consume/register',body,{
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then((res) => {
          console.log('제출데이터:', res)
        })
        .catch((error) => {
          console.log('제출데이터 에러', error);
        });
    }

    const handleData =(e)=>{
        // console.log('셀렉트', e);
        setoptionIndex(e);
  };

    const handlecost = ()=>{
        let body = {'yearAndMonth' : `${year}-${month}`}
        customAxios
        .post(process.env.REACT_APP_BASE_URL + '/consume/highest', body)
        .then((res) => {
            console.log('데이터셋예정임',res.data)
            setdataset(res.data)

        })
        .catch((error) => {
          console.log('거래내역 에러', error);
        });
    }
    const handlenew = ()=>{
        let body = {'yearAndMonth' : `${year}-${month}`}
        customAxios
        .post(process.env.REACT_APP_BASE_URL + '/consume', body)
        .then((res) => {
            console.log('데이터셋예정임',res.data)
            setdataset(res.data)
        })
        .catch((error) => {
          console.log('거래내역 에러', error);
        });
    }

    useEffect(()=>{
        // console.log('filter창 month', mymonth.mymonth)
        // console.log('bill',mymonth.bill)
        let body = {'yearAndMonth' : `${year}-${month}`}

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

    return (
    <>
        <div>                
            <AppBar title='전체소비내역'></AppBar>
            {/* 소비내역 제출 폼  */}
            <div className='regist-form'>
                <div className='entire-dropdown'>
                    <label htmlFor="">카테고리 선택</label>
                    <DropdownSelect onOptionSelect={handleData}></DropdownSelect>
                </div>
                <div>
                    <label htmlFor="selectDate">날짜 및 시간 선택:</label>
                    <input type="datetime-local" id="selectDate" name="selectDate" value={selectedDate} onChange={handleDateChange} />
                </div>
                <div>
                    <label htmlFor="textInput">소비내역</label>
                    <input type="text" id="textInput" name="textInput" value={textInput} onChange={handleTextChange} />
                </div>
                <div>
                    <label htmlFor="numberInput">금액</label>
                    <input type="number" id="numberInput" name="numberInput" value={numberInput} onChange={handleNumberChange} />
                </div>
                <button onClick={handleSubmit}>제출</button>
            </div>
            {/* 소비내역 제출 폼 끝 */}

            {/* 전체내역 조회 시작 */}
            <div className='filter-chose'>
                <button onClick={handlecost}>금액순</button>
                <button onClick={handlenew}>최신순</button>
            </div>

            {dataset.map((item, index) => (
            <div key={index}>
                <div className='filter-store'>
                    <div>
                        {item.consumeTime.slice(6, 7) + '월' + item.consumeTime.slice(8, 10) + '일'}
                    </div>
                    <div className='filter-fontcontainer'>
                        <div className='filter-50'>
                            <div className='filter-fontcolor2'>{item.store.name}</div>
                            <div className='filter-fontcolor3'>
                            {item.consumeTime.slice(11, 13) + '시' + item.consumeTime.slice(14, 16) + '분'}
                        </div>
                    </div>
                    <div className='filter-fontcolor1'>{item.amount}원</div>
                </div>
            </div>
            </div>
            ))}
            {/* 전체내역 조회 끝 */}
        
        
        
        
        </div>
    </>
        )

    }
export default EntirePurchaseListPage