import AppBar from '../components/ui/AppBar'
import './ConsumePlanInsertPage.css'
import React, {useState,useEffect} from 'react';
import { useSelector } from 'react-redux';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router';
import { customAxios } from '../CustomAxios';
import { TextField,FormControl,InputLabel,Input,InputAdornment, IconButton} from '@mui/material';
import {LuggageRounded,PetsRounded,SubscriptionsRounded,FastfoodRounded,CommuteRounded,FitnessCenterRounded,ContentCutRounded,SportsEsportsRounded,DiningRounded,StorefrontRounded,DirectionsBusRounded,ShoppingBagRounded,LocalCafeRounded,RequestQuoteRounded} from '@mui/icons-material';

function ConsumePlanInsertPage(props) {
    const {id}=useParams();
    const user = useSelector((state)=> state.user)

    const specificDate = new Date('2023-09-27'); //specificDate.getMonth()를 하면 9월이면 8이 나옴. 근데 여기서 테스트 용으로 걍 현재 9월인 걸로 생각하자

    const nowYear=specificDate.getFullYear();
    const nowMonth = specificDate.getMonth() + 2; //현재가 9월이라고 생각하니까 consumeplan을 넣어야 하는건 그 다음달이니까 이렇게 해야됨
    
    const yearAndMonthForm=(nowYear,nowMonth)=>{
        if(nowMonth<10){
            return `${nowYear}-0${nowMonth}`;
        }
        return `${nowYear}-${nowMonth}`;
    }
    const yearAndMonth=yearAndMonthForm(nowYear,nowMonth);


    const [totalAmount, setTotalAmount] = useState();
    const [eatAmount,setEatAmount]=useState();
    const [eatPercent,setEatPercent]=useState();
    const [groceryPercent,setGroceryPercent]=useState();
    const [ridePercent,setRidePercent]=useState();
    const [shoppingPercent,setShoppingPercent]=useState();
    const [snackPercent,setSnackPercent]=useState();
    const [insurancePercent,setInsurancePercent]=useState();
    const [hobbyPercent,setHobbyPercent]=useState();
    const [hairPercent,setHairPercent]=useState();
    const [healthPercent,setHealthPercent]=useState();
    const [ottPercent,setOttPercent]=useState();
    const [petPercent,setPetPercent]=useState();
    const [travelPercent,setTravelPercent]=useState();


    const handleSubmitConsumePlan=async(event)=>{
        event.preventDefault();
        alert('등록 완료');
        //홈으로 네비게이트


        // const totalAmountLong=totalAmount;
        // // const totalAmountReal=Long.fromValue(totalAmount);
        // const consumeplan={
        //     yearAndMonth:yearAndMonth,
        //     totalAmount:totalAmountLong,
        //     eatPercent:parseInt(eatPercent,10),
        //     groceryPercent:parseInt(groceryPercent,10),
        //     ridePercent:parseInt(ridePercent,10),
        //     shoppingPercent:parseInt(shoppingPercent,10),
        //     snackPercent:parseInt(snackPercent,10),
        //     insurancePercent:parseInt(insurancePercent,10),
        //     hobbyPercent:parseInt(hobbyPercent,10),
        //     hairPercent:parseInt(hairPercent,10),
        //     healthPercent:parseInt(healthPercent,10),
        //     ottPercent:parseInt(ottPercent,10),
        //     petPercent:parseInt(petPercent,10),
        //     travelPercent:parseInt(travelPercent,10)
        // }
        // console.log('consumeplan 객체:', consumeplan);
        // try{
        //     const response=await axios.post(
        //         `${process.env.REACT_APP_BASE_URL}/register`,
        //         JSON.stringify(consumeplan),
        //         {
        //             headers: {
        //                 'Content-Type': 'application/json; charset=UTF-8',
        //                 'Authorization' : 'Bearer '+localStorage.getItem("accessToken")
        //             }
        //         }
        //     );
        //     if(response.status===200){
        //         alert('consumeplan 삽입 성공');
        //     }
        // }catch(error){
        //     console.error('에러발생',error);
        // }
    }

    
    const handleTotalAmountChange = (e) => {
        // 입력값을 문자열로 얻습니다
        let inputValue = e.target.value;

        // 입력값이 숫자가 아닌 경우, 또는 빈 문자열인 경우 0으로 설정합니다
        if (!/^\d+$/.test(inputValue)){
            alert('입력값을 확인하세요.');
            inputValue = '';
        }
        if(inputValue === '') {
            inputValue = '';
        }

        // 숫자 값으로 변환합니다
        const numericValue = parseInt(inputValue, 10);

        // 입력값이 9999999999999999보다 큰 경우 경고창을 띄우고 0으로 설정합니다.
        if (numericValue > 9999999999999999) {
            alert('9999999999999999보다 큰 값을 입력할 수 없습니다.');
            inputValue = '';
        }

        // 숫자 값으로 상태를 업데이트합니다
        setTotalAmount(inputValue);
    };

    return (
        <>
            <AppBar></AppBar>
            <div className='consumeplan-form'>
                <div className='consumeplan-nowyear-nowmonth'>
                    {nowYear}년 {nowMonth}월 예산
                </div>
                <div className="consumplan-amount-input">
                    <textarea
                        placeholder='0'
                        value={totalAmount}
                        onChange={handleTotalAmountChange}
                    />
                    <div className='consumeplan-amount-won'>
                        원
                    </div>
                
                </div>
            </div>
            <hr/>
            <div className='consumeplan-form-bottom'>
                <div className='consumeplan-category'>                                                              
                    <div className='consumeplan-icon-container'>
                        <FastfoodRounded style={{ color: 'white', fontSize: '30px' }}/>
                    </div>
                    <div className='consumeplan-category-detail'>
                        <div className='consumeplan-title'>
                            식비
                        </div>
                        <div className='consumeplan-category-amount'>
                            예상 소비 금액 : {eatAmount}
                        </div>
                    </div>
                    <div className='consumeplan-percent'>
                        <textarea
                            value={eatPercent}
                            onChange={(e) => setEatPercent(() => {
                                const newEatPercent =  e.target.value;
                                return parseInt(newEatPercent, 10);
                            })}
                        />
                        <div className='consumeplan-category-percent'>
                            %
                        </div>
                    </div>
                </div>

                <div className='consumeplan-category'>                                                              
                    <div className='consumeplan-icon-container'>
                        <StorefrontRounded style={{ color: 'white', fontSize: '30px' }}/>
                    </div>
                    <div className='consumeplan-category-detail'>
                        <div className='consumeplan-title'>
                            편의점·마트·잡화
                        </div>
                        <div className='consumeplan-category-amount'>
                            예상 소비 금액 : {eatAmount}
                        </div>
                    </div>
                    <div className='consumeplan-percent'>
                        <textarea
                            value={groceryPercent}
                            onChange={(e) => setGroceryPercent(() => {
                                const newGroceryPercent =  e.target.value;
                                return newGroceryPercent;
                            })}
                        />
                        <div className='consumeplan-category-percent'>
                            %
                        </div>
                    </div>
                </div>

                <div className='consumeplan-category'>                                                              
                    <div className='consumeplan-icon-container'>
                        <CommuteRounded style={{ color: 'white', fontSize: '30px' }}/>
                    </div>
                    <div className='consumeplan-category-detail'>
                        <div className='consumeplan-title'>
                            교통·자동차
                        </div>
                        <div className='consumeplan-category-amount'>
                            예상 소비 금액 : {eatAmount}
                        </div>
                    </div>
                    <div className='consumeplan-percent'>
                        <textarea
                            value={ridePercent}
                            onChange={(e) => setRidePercent(() => {
                                const newRidePercent =  e.target.value;
                                return newRidePercent;
                            })}
                        />
                        <div className='consumeplan-category-percent'>
                            %
                        </div>
                    </div>
                </div>

                <div className='consumeplan-category'>                                                              
                    <div className='consumeplan-icon-container'>
                        <ShoppingBagRounded style={{ color: 'white', fontSize: '30px' }}/>
                    </div>
                    <div className='consumeplan-category-detail'>
                        <div className='consumeplan-title'>
                            쇼핑
                        </div>
                        <div className='consumeplan-category-amount'>
                            예상 소비 금액 : {eatAmount}
                        </div>
                    </div>
                    <div className='consumeplan-percent'>
                        <textarea
                            value={shoppingPercent}
                            onChange={(e) => setShoppingPercent(() => {
                                const newShoppingPercent =  e.target.value;
                                return newShoppingPercent;
                            })}
                        />
                        <div className='consumeplan-category-percent'>
                            %
                        </div>
                    </div>
                </div>

                <div className='consumeplan-category'>                                                              
                    <div className='consumeplan-icon-container'>
                        <LocalCafeRounded style={{ color: 'white', fontSize: '30px' }}/>
                    </div>
                    <div className='consumeplan-category-detail'>
                        <div className='consumeplan-title'>
                            카페·간식   
                        </div>
                        <div className='consumeplan-category-amount'>
                            예상 소비 금액 : {eatAmount}
                        </div>
                    </div>
                    <div className='consumeplan-percent'>
                        <textarea
                            value={snackPercent}
                            onChange={(e) => setSnackPercent(() => {
                                const newSnackPercent =  e.target.value;
                                return newSnackPercent;
                            })}
                        />
                        <div className='consumeplan-category-percent'>
                            %
                        </div>
                    </div>
                </div>


                <div className='consumeplan-category'>                                                              
                    <div className='consumeplan-icon-container'>
                        <RequestQuoteRounded style={{ color: 'white', fontSize: '30px' }}/>
                    </div>
                    <div className='consumeplan-category-detail'>
                        <div className='consumeplan-title'>
                            보험·세금·기타금융 
                        </div>
                        <div className='consumeplan-category-amount'>
                            예상 소비 금액 : {eatAmount}
                        </div>
                    </div>
                    <div className='consumeplan-percent'>
                        <textarea
                            value={insurancePercent}
                            onChange={(e) => setInsurancePercent(() => {
                                const newInsurancePercent=  e.target.value;
                                return newInsurancePercent;
                            })}
                        />
                        <div className='consumeplan-category-percent'>
                            %
                        </div>
                    </div>
                </div>

                <div className='consumeplan-category'>                                                              
                    <div className='consumeplan-icon-container'>
                        <SportsEsportsRounded style={{ color: 'white', fontSize: '30px' }}/>
                    </div>
                    <div className='consumeplan-category-detail'>
                        <div className='consumeplan-title'>
                            취미·여가
                        </div>
                        <div className='consumeplan-category-amount'>
                            예상 소비 금액 : {eatAmount}
                        </div>
                    </div>
                    <div className='consumeplan-percent'>
                        <textarea
                            value={hobbyPercent}
                            onChange={(e) => setHobbyPercent(() => {
                                const newHobbyPercent =  e.target.value;
                                return newHobbyPercent;
                            })}
                        />
                        <div className='consumeplan-category-percent'>
                            %
                        </div>
                    </div>
                </div>

                <div className='consumeplan-category'>                                                              
                    <div className='consumeplan-icon-container'>
                        <ContentCutRounded style={{ color: 'white', fontSize: '30px' }}/>
                    </div>
                    <div className='consumeplan-category-detail'>
                        <div className='consumeplan-title'>
                            미용
                        </div>
                        <div className='consumeplan-category-amount'>
                            예상 소비 금액 : {eatAmount}
                        </div>
                    </div>
                    <div className='consumeplan-percent'>
                        <textarea
                            value={hairPercent}
                            onChange={(e) => setHairPercent(() => {
                                const newHairPercent =  e.target.value;
                                return newHairPercent;
                            })}
                        />
                        <div className='consumeplan-category-percent'>
                            %
                        </div>
                    </div>
                </div>

                <div className='consumeplan-category'>                                                              
                    <div className='consumeplan-icon-container'>
                        <FitnessCenterRounded style={{ color: 'white', fontSize: '30px' }}/>
                    </div>
                    <div className='consumeplan-category-detail'>
                        <div className='consumeplan-title'>
                            의료·건강·피트니스
                        </div>
                        <div className='consumeplan-category-amount'>
                            예상 소비 금액 : {eatAmount}
                        </div>
                    </div>
                    <div className='consumeplan-percent'>
                        <textarea
                            value={healthPercent}
                            onChange={(e) => setHealthPercent(() => {
                                const newHealthPercent =  e.target.value;
                                return newHealthPercent;
                            })}
                        />
                        <div className='consumeplan-category-percent'>
                            %
                        </div>
                    </div>
                </div>

                <div className='consumeplan-category'>                                                              
                    <div className='consumeplan-icon-container'>
                        <SubscriptionsRounded style={{ color: 'white', fontSize: '30px' }}/>
                    </div>
                    <div className='consumeplan-category-detail'>
                        <div className='consumeplan-title'>
                            정기결제
                        </div>
                        <div className='consumeplan-category-amount'>
                            예상 소비 금액 : {eatAmount}
                        </div>
                    </div>
                    <div className='consumeplan-percent'>
                        <textarea
                            value={ottPercent}
                            onChange={(e) => setOttPercent(() => {
                                const newOttPercent =  e.target.value;
                                return newOttPercent;
                            })}
                        />
                        <div className='consumeplan-category-percent'>
                            %
                        </div>
                    </div>
                </div>
                
                <div className='consumeplan-category'>                                                              
                    <div className='consumeplan-icon-container'>
                        <PetsRounded style={{ color: 'white', fontSize: '30px' }}/>
                    </div>
                    <div className='consumeplan-category-detail'>
                        <div className='consumeplan-title'>
                            반려동물
                        </div>
                        <div className='consumeplan-category-amount'>
                            예상 소비 금액 : {eatAmount}
                        </div>
                    </div>
                    <div className='consumeplan-percent'>
                        <textarea
                            value={petPercent}
                            onChange={(e) => setPetPercent(() => {
                                const newPetPercent =  e.target.value;
                                return newPetPercent;
                            })}
                        />
                        <div className='consumeplan-category-percent'>
                            %
                        </div>
                    </div>
                </div>

                <div className='consumeplan-category'>                                                              
                    <div className='consumeplan-icon-container'>
                        <LuggageRounded style={{ color: 'white', fontSize: '30px' }}/>
                    </div>
                    <div className='consumeplan-category-detail'>
                        <div className='consumeplan-title'>
                            여행
                        </div>
                        <div className='consumeplan-category-amount'>
                            예상 소비 금액 : {eatAmount}
                        </div>
                    </div>
                    <div className='consumeplan-percent'>
                        <textarea
                            value={travelPercent}
                            onChange={(e) => setTravelPercent(() => {
                                const newTravelPercent =  e.target.value;
                                return newTravelPercent;
                            })}
                        />
                        <div className='consumeplan-category-percent'>
                            %
                        </div>
                    </div>
                </div>
                <div className='consumeplan-register-button' onClick={handleSubmitConsumePlan}>
                    등록
                </div>
            </div>
        </>
    );
}
export default ConsumePlanInsertPage;