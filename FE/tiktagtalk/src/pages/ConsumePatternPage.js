import { useSelector } from 'react-redux';
import AppBar from '../components/ui/AppBar';
import './ConsumePatternPage.css'
import DropdownMenu from '../components/ui/DropdownMenu';
import { useState,useEffect } from 'react';
import { customAxios } from '../CustomAxios';
import styled from 'styled-components';
import { useNavigate } from 'react-router';
// import { useState } from "react";
// import { useSelector } from 'react-redux';
// import Modal from '../components/ui/Modal';
// import ItemModal from '../components/ItemModal';

function ConsumePatternPage() {
    const naviage = useNavigate()
    let user = useSelector((state)=>state.user)
    const [year, setyear] = useState('')
    const [month, setmonth] = useState('')
    const [mymonth, setmymonth] = useState('');
    const [requestmonth, setrequestmonth] = useState({yearAndMonth:'2023-08'})
    const [totalamount, settotalamount] = useState('')
    const [highesttag, sethighesttag] = useState([])
    const [category, setcategory] = useState([])
    // console.log('redux임',user)
    const handleData = (data) =>{
        console.log(data)
        setmymonth(data)
        console.log('월데이터?', data)

    }

    useEffect(() => {
      // mymonth 상태 업데이트 후에 axios 요청을 보내도록 처리합니다.
      setyear(new Date().getFullYear())
      setmonth(new Date().getMonth().toString().padStart(2,'0'))
      setmymonth(`${new Date().getFullYear()}-${String(new Date().getMonth()).padStart(2, '0')}`);
    }, []); // 빈 배열을 전달하여 이펙트가 한 번만 실행되도록 합니다.

    useEffect(() => {
      if (mymonth !== '') { // mymonth가 빈 문자열이 아닐 때만 실행
      // mymonth 상태가 업데이트되면 실행되는 이펙트
      console.log('mymonth 업뎃:',mymonth)
      let body = {'yearAndMonth' : `${mymonth}`}
      console.log(body)
      customAxios
        .post(process.env.REACT_APP_BASE_URL + '/consume/checkaccount',body)
        .then((res) => {
          console.log('전체데이터임:', res.data.tagList)
          setcategory(res.data.tagList.slice(0, 4))
          settotalamount(res.data.totalAmount);
          console.log('거래내역', res.data.totalAmount);
        })
        .catch((error) => {
          console.log('거래내역 에러', error);
        });
        // customAxios
        // .post(process.env.REACT_APP_BASE_URL + '/consume/highest', body)
        // .then((res) => {
        //   // highesttag를 업데이트합니다.
        //   console.log(res.data)
        //   sethighesttag(res.data.slice(0, 4));
        //   console.log(res)
        //   console.log('응답상위',res.data.slice(0,4))
        //   // console.log('상위4개:', highesttag);
        // })
        // .catch((error) => {
        //   console.log('거래내역 에러', error);
        // });


      }
      // mymonth이 변경될 때마다 이펙트를 실행하도록 설정
    }, [mymonth]);






    // 동그라미

    const CircleIcon = styled.div`
    width: 5vw; /* 동그라미 아이콘의 너비 */
    height: 2vh; /* 동그라미 아이콘의 높이 */
    background-color: #007bff; /* 동그라미의 배경 색상 */
    border-radius: 50%; /* 원 모양으로 만듭니다. */
    display: flex;
    justify-content: center;
    align-items: center;
    color: white; /* 아이콘 색상 */
    font-size: 24px; /* 아이콘 크기 */
    `;
    const StyledButton = styled.button`
  background-color: white; /* 버튼 배경 색상 */
  color: white; /* 버튼 텍스트 색상 */`;
    const handlefilter = (tagName)=>{
        const [a,bill] = tagName
        naviage(`/filter-purchase/${a}`,{ state: { mymonth,bill } })
    }



    return (
        <>
            <div className='appbar-container'>
                <div>
                    <img src="/Icon/뒤로가기.png" alt="" />
                </div>
                <div>
                    <DropdownMenu monthData={handleData}></DropdownMenu>
                </div>
            </div>

            <div className='consume-pattern'>
                <div>
                  <img className='consume-responsive-image' src={`/avatar/type${user.avatarType}.jpg`} alt="" /> 
                </div>
                <div className='consume-namebox'>
                    {user.userId}
                </div>
                {totalamount !== null ? (
                <div>
                  <div>{year}년 {month}월 소비</div>
                  <div className='consume-totalamount'>{totalamount}원</div>
                    <div>
                    {category.map((item,index)=>(
                          <div key = {index} className='consume-container'>
                              <div className='consume-circle'><CircleIcon></CircleIcon></div>
                              <div>{item.name}</div>
                              <div>{item.amount} 원</div>
                              <div>{item.percent} %</div>
                              <StyledButton onClick={handlefilter.bind(null,[item.name,item.amount])}></StyledButton>
                          </div>
                      ))}

                    </div>
                </div>
                     ) : <h1> 소비내역이 없습니다.</h1> }
                     
                     {/* // null 대신 이미지 넣어야할듯? */}
            </div>
        </>
    );
}

export default ConsumePatternPage;
