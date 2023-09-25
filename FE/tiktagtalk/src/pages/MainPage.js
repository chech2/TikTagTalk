import { useEffect, useState } from "react";
import { useSelector,useDispatch } from 'react-redux';
import axios from 'axios';
import './MainPage.css';
import SelectItemBox from "../components/SelectItemBox";
import NavBarMain from "../components/ui/NavBarMain";
import {loginUser} from '../redux/userSlice';
import TagRoom from '../TagRoom'

function MainPage() {
    const myimage = useSelector((state) => state.user.avatarType);
    const dispatch = useDispatch();
    useEffect(() => {
        const fetchData = async () => {
          try {
            const res = await axios.get(process.env.REACT_APP_BASE_URL + '/api/members/sign-up');
            console.log('로그인정보', res);
            dispatch(loginUser(res));
          } catch (err) {
            console.error('에러 발생', err);
          }
        };
    
        fetchData(); // fetchData 함수를 호출하여 비동기 작업을 수행
      }, []); // 의존성 배열은 빈 배열로 설정
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

    // const handleBox  = ()=>{
    //     setIsItemBox(!isItemBox)
    //     console.log(isItemBox)
    // }
    return (
        <>
            {
                !user.isLogin ? (
                    <div>
                        <NavBarMain></NavBarMain>
                        <h1>메인페이지 예정입니다.</h1>
                        {/* <TagRoom></TagRoom> */}
                    </div>
                ) : (
                    <div>{user.userId}</div>
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