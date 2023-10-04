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

                <div>{mymonth.mymonth}</div>
                <div>{mymonth.bill} 총 금액</div>
                {/* <div>총 {}회 결제</div> */}
                <div>거래내역 <button onClick={handleAddDeal}> + </button> </div>
                

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
