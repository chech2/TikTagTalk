import AppBar from '../components/ui/AppBar';
import './AchievementsPage.css'
import { useState } from "react";
import { useSelector } from 'react-redux';
import AchievementBox from '../components/AchievementBox';
import axios from 'axios';



function AchievementsPage(props) {
    const mycategory = ['category1','category2','category3']   // 리덕스에서 정보 뺴오는게 날려나 ? 
    // axios 요청 정보 받기 
    return (
        <>
            <div>
                <AppBar title='업적'></AppBar>
            </div>
            {mycategory.map((category, index) => (
                <AchievementBox key={index} category={category} />
            ))}

        </>
    );
}

export default AchievementsPage;
