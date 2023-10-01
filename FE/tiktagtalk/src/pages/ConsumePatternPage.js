import { useSelector } from 'react-redux';
import AppBar from '../components/ui/AppBar';
import './ConsumePatternPage.css'
import DropdownMenu from '../components/ui/DropdownMenu';
import { useState,useEffect } from 'react';
import { customAxios } from '../CustomAxios';
import styled from 'styled-components';
// import { useState } from "react";
// import { useSelector } from 'react-redux';
// import Modal from '../components/ui/Modal';
// import ItemModal from '../components/ItemModal';

function ConsumePatternPage() {
    let user = useSelector((state)=>state.user)
    const [mymonth, setmymonth] = useState('0월');
    const [requestmonth, setrequestmonth] = useState({yearAndMonth:'2023-08'})
    const [totalamount, settotalamount] = useState('0원')
    // console.log('redux임',user)
    const handleData = (data) =>{
        console.log(data)
        setmymonth(data)
        console.log('월데이터?', data)
        // setrequestmonth(data.slice(0,4)+'-0'+data.slice(6,7))
        // console.log('payload:',requestmonth)
    }

    useEffect(()=>{
        setmymonth(`${new Date().getFullYear()}-${new Date().getMonth()+1}`)
        console.log(mymonth)
        customAxios.get(process.env.REACT_APP_BASE_URL + '/consume/checkaccount',{ 'yearAndMonth' : `${mymonth}` },
          )
        .then((res)=>{
            console.log('거래내역',res)
            settotalamount(res.totalamount)
        })
        .catch((err)=>{
            console.log('거래내역에러',err)
        })
    },
        customAxios.get(process.env.REACT_APP_BASE_URL + '/consume/highest',{ 'yearAndMonth' : `${mymonth}` })
        .then((res)=>{
            console.log(res)
            //  높은순 
        })
        .catch((err)=>{
            console.log(err)
        })
    
    [requestmonth])




    // 동그라미

    const CircleIcon = styled.div`
    width: 5vw; /* 동그라미 아이콘의 너비 */
    height: 2vh; /* 동그라미 아이콘의 높이 */
    background-color: #007bff; /* 동그라미의 배경 색상 */
    border-radius: 50%; /* 원 모양으로 만듭니다. */
    display: flex;
    justify-content: center;
    align-items: center;
    color: white; /* 아이콘 색상 */
    font-size: 24px; /* 아이콘 크기 */
    `;



    return (
        <>
            <div className='appbar-container'>
                <div>
                    <img src="/Icon/뒤로가기.png" alt="" />
                </div>
                <div>
                    <DropdownMenu monthData={handleData}></DropdownMenu>
                </div>
            </div>
            <div className='consume-pattern'>
                <div>
                    <img src="/Character/1.jpg" alt="" />
                </div>
                <div>
                    {user.userId}
                </div>
                <div>{mymonth}</div>
                <div>{totalamount}</div>
                <div>
                    <div className='consume-container'>
                        <div><CircleIcon></CircleIcon></div>
                        <div>종목이름</div>
                        <div>종목금액</div>
                    </div>
                    <div className='consume-container'>
                        <div><CircleIcon></CircleIcon></div>
                        <div>종목이름</div>
                        <div>종목금액</div>
                    </div>                    <div className='consume-container'>
                        <div><CircleIcon></CircleIcon></div>
                        <div>종목이름</div>
                        <div>종목금액</div>
                    </div>                    <div className='consume-container'>
                        <div><CircleIcon></CircleIcon></div>
                        <div>종목이름</div>
                        <div>종목금액</div>
                    </div>
                </div>



            </div>
        </>
    );
}

export default ConsumePatternPage;
