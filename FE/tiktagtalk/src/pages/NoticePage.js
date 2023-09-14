import AppBar from '../components/ui/AppBar';
import './NoticePage.css'
// import { useState } from "react";
// import { useSelector } from 'react-redux';
// import Modal from '../components/ui/Modal';
// import ItemModal from '../components/ItemModal';

function NoticePage(props) {

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
