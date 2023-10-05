import './EntirePurchaseListPage.css'
import { useState } from 'react';
import { customAxios } from '../CustomAxios';
import DropdownSelect from '../components/ui/DropdownSelect';
import AppBar from '../components/ui/AppBar';


function EntirePurchaseListPage() {
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


    return (
    <>
        <div>                
            <AppBar title='전체소비내역'></AppBar>

            <div className='buy-regist'>
                <div className='filter-dropdown'>
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
            </div>
            <button onClick={handleSubmit}>제출</button>
        </div>
    </>
        )

    }
export default EntirePurchaseListPage