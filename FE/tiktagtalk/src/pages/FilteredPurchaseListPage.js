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
        console.log('filter창 month', mymonth.mymonth)
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

    return (
        <>
            <div>
                <AppBar title= {tag +' 소비내역'}></AppBar>
                
            </div>

        </>
    );
}

export default FilteredPurchaseListPage;
