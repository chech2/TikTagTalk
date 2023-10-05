import { useEffect,useState } from 'react';
import AppBar from '../components/ui/AppBar';
import './FilteredPurchaseListPage.css';
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

    const handlecost = ()=>{
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
    }
    const handlenew = ()=>{
        let body = {'yearAndMonth' : `${mymonth.mymonth}`}
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
    

    return (
        <>
            <div>
                <AppBar title= {month + '월 ' + tag +' 소비내역'}></AppBar>
                <div className='filter-infor'> 
                    {/* <div>{year}년 {month}월</div> */}
                    <div>{tag} 소비내역 총 금액</div>
                    <div>{mymonth.bill} 원</div>
                {/* <div>총 {}회 결제</div> */}
                </div>
                <div>거래내역</div>
                <div className='filter-chose'>
                    <button className='filter-button' onClick={handlecost}>금액순</button>
                    <button className='filter-button' onClick={handlenew}>최신순</button>
                </div>


                {dataset.map((item,index)=>{
                    if (item.tag.name === tag) {
                        return (
                            <div>
                                <div className='filter-store' key={index}>
                                    <div className='filter-date'>{item.consumeTime.slice(6, 7) +'월' + item.consumeTime.slice(8, 10)+'일'}</div>
                                    <div className='filter-fontcontainer'>
                                        <div className='filter-50'>
                                            <div className='filter-fontcolor2'>{item.store.name}</div>
                                            <div className='filter-fontcolor3'>{item.consumeTime.slice(11, 13)+'시'+item.consumeTime.slice(14,16)+'분'}</div>
                                        </div>
                                        <div className='filter-fontcolor1'>{item.amount}원</div>
                                    </div>
                                        

                                    
                                </div>
                            </div>
                        );
                    }
                return null
            })}
            </div>

        </>
    );
}

export default FilteredPurchaseListPage;
