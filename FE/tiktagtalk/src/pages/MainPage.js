import { useEffect, useState } from "react";
import { useSelector,useDispatch } from 'react-redux';
import axios from 'axios';
import './MainPage.css';
import SelectItemBox from "../components/SelectItemBox";
import NavBarMain from "../components/ui/NavBarMain";
import {loginUser} from '../redux/userSlice';
import TagRoom from '../tagroom/TagRoom'
import { useParams } from "react-router-dom";

function MainPage() {
    const { id } = useParams();
    const dispatch = useDispatch();
    
    // 의존성 배열은 빈 배열로 설정
    // useEffect(()=>{
    //     axios.get(process.env.REACT_APP_BASE_URL + '/api/members/sign-up')
    //     .then((res)=>{
    //       console.log('로그인정보', res)
    //       dispatch(loginUser(res))

    //     })
    //     .catch((err)=>{
    //       // console.log('레시피 제목 못 가져옴', err)
    //     })
    //     // setIsModalOpen(params.open);
    //   },[])

    let user = useSelector((state) => state.user);
    console.log('리덕스유저',user)

    // const handleBox  = ()=>{
    //     setIsItemBox(!isItemBox)
    //     console.log(isItemBox)
    // }
    return (
        <>
            {
                user.isLogin ? (
                    <div className="tag-room-style">
                        <NavBarMain 
                            mainpage_id = {id}
                        ></NavBarMain>
                        <TagRoom></TagRoom>
                    </div>
                ) : (
                    <div>{user.userId},{user.isLogin}</div>
                )
            }
{/* 
            {!isItemBox && <div className="rectangle-false" onClick={handleBox}></div>}
            {isItemBox && 
            
            <div className='test1'>
                <div><SelectItemBox/></div>
                <div className="rectangle-true" onClick={handleBox}></div>
            </div>
            } */}
        </>
    );
    }

export default MainPage;