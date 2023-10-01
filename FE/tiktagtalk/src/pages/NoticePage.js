import { useSelector } from 'react-redux';
import AppBar from '../components/ui/AppBar';
import './NoticePage.css'
// import { useState } from "react";
// import { useSelector } from 'react-redux';
// import Modal from '../components/ui/Modal';
// import ItemModal from '../components/ItemModal';



// 내 소비태그에 변화가 생길 때 마다 axios 요청을 보내서 img url 따와서 박스 만들기
// 나를 추가한 것에 차이가 생길 때 마다 

function NoticePage(props) {
    const user = useSelector((state)=>state.user)
    const userid = user.id

    // useEffect(
    //     customAxios.post(process.env.REACT_APP_BASE_URL + '/talk-talks'          )
    //     .then((res)=>{
    //         console.log(res)
    //     })
    //     .catch((err)=>{
    //         // console.log(err)
    //     })
    // )

    return (
        <>
            <div>
                <AppBar title='알림 페이지'></AppBar>
            </div>
            <div>
                <p>알림 적어라</p>
            </div>

        </>
    );
}

export default NoticePage;
