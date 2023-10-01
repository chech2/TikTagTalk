import { useSelector } from 'react-redux';
import AppBar from '../components/ui/AppBar';
import './ConsumePatternPage.css'
import DropdownMenu from '../components/ui/DropdownMenu';
import { useState,useEffect } from 'react';
import { customAxios } from '../CustomAxios';
// import { useState } from "react";
// import { useSelector } from 'react-redux';
// import Modal from '../components/ui/Modal';
// import ItemModal from '../components/ItemModal';

function ConsumePatternPage() {
    let user = useSelector((state)=>state.user)
    const [mymonth, setmymonth] = useState('');
    const [requestmonth, setrequestmonth] = useState({yearAndMonth:''})
    console.log('redux임',user)
    const handleData = (data) =>{
        setmymonth(data)
        setrequestmonth(data.slice(0,4)+'-0'+data.slice(6,7))
        console.log('payload:',requestmonth)
    }

    useEffect(()=>{
        customAxios.get(process.env.REACT_APP_BASE_URL + '/api/consume/checkaccount/',{ yearAndMonth : requestmonth },
          )
        .then((res)=>{
            console.log('거래내역',res)
        })
        .catch((err)=>{
            console.log('거래내역에러',err)
        })
    },[requestmonth])




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



            </div>
        </>
    );
}

export default ConsumePatternPage;
