import AppBar from '../components/ui/AppBar';
import './ExchangePage.css'

// import { useState } from "react";
import { useSelector } from 'react-redux';
// import Modal from '../components/ui/Modal';
// import ItemModal from '../components/ItemModal';



// 내 소비태그에 변화가 생길 때 마다 axios 요청을 보내서 img url 따와서 박스 만들기
// 나를 추가한 것에 차이가 생길 때 마다 

function ExchangePage(props) {
    let user = useSelector((state) => state.user);


    return (
        <>
            <div>
                <AppBar title='환전 페이지'></AppBar>
            </div>
            <div className='exchange-container'>
                <div className='exchange-container-33'>
                    <img className='exchange-responsive-image' src="Icon/마이페이지 아이콘.png" alt="" />
                </div>
                <div className='exchange-container-33'>
                    허주혁(이름) 
                    {/* {user.userId} */}
                </div>
                <div className='exchange-container-33'>
                    <div>
                        <div className='exchange-container-33-2' >
                            <img className='exchange-responsive-image2' src="./Icon/포인트 아이콘.png" alt="" />
                            <div>{user.point}</div>
                        </div>
                        <div className='exchange-container-33-2' >
                            <img className='exchange-responsive-image2' src="./Icon/Coin.png" alt="" />
                            <div>{user.coin}</div>
                            {/* <img className='exchange-responsive-image2' src="./Icon/코인 구매창.png" alt="" /> */}
                        </div>
                    </div>
                </div>
            </div>
        <div>
            오늘의 환율
        </div>

        </>
    );
}

export default ExchangePage;
