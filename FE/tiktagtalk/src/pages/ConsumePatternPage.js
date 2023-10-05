import { useSelector } from 'react-redux';
import './ConsumePatternPage.css'
import { useState,useEffect } from 'react';
import { customAxios } from '../CustomAxios';
import { useNavigate } from 'react-router';
import ConsumePatternChart from '../components/ui/ConsumePatternChart.jsx';
import EmptyConsumePatternChart from '../components/ui/EmptyConsumePatternChart';

import { IconButton } from '@mui/material';
import { ArrowBackIosNew, ArrowLeft, ArrowRight, FiberManualRecord, KeyboardArrowRight } from '@mui/icons-material';

function ConsumePatternPage() {
<<<<<<< HEAD
  const navigator = useNavigate();
  let user = useSelector((state)=>state.user);
  const [mymonth, setmymonth] = useState();
  const [year, setYear] = useState(0);
  const [month, setMonth] = useState(0);
  const [totalAmount, setTotalAmount] = useState();
  const [tagList, setTagList] = useState([]);
  
  const goBack = () => {
    window.history.back();
  };
  
  // 처음 현재 년월 불러오기
  useEffect(() => {
    const now = new Date();
    setYear(now.getFullYear());
    setMonth(now.getMonth() + 1);
    setmymonth(`${now.getFullYear()}-${String(now.getMonth() + 1).padStart(2, '0')}`);
  }, [])
  
  // 전 월 데이터 보기
  const subMonth = () => {
    if (month === 1) {
      setmymonth(`${year - 1}-12`);
      setYear(year - 1);
      setMonth(12);
    }
    else {
      setmymonth(`${year}-${String(month - 1).padStart(2, '0')}`);
      setMonth(month - 1);
=======
    const naviage = useNavigate()
    let user = useSelector((state)=>state.user)
    const [year, setyear] = useState('')
    const [month, setmonth] = useState('')
    const [mymonth, setmymonth] = useState('');
    const [requestmonth, setrequestmonth] = useState({yearAndMonth:'2023-08'})
    const [totalamount, settotalamount] = useState('')
    const [highesttag, sethighesttag] = useState([])
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
          settotalamount(res.data.totalAmount);
          console.log('거래내역', res.data.totalAmount);
        })
        .catch((error) => {
          console.log('거래내역 에러', error);
        });
        customAxios
        .post(process.env.REACT_APP_BASE_URL + '/consume/highest', body)
        .then((res) => {
          // highesttag를 업데이트합니다.
          sethighesttag(res.data.slice(0, 4));
          console.log(res)
          console.log('응답상위',res.data.slice(0,4))
          // console.log('상위4개:', highesttag);
        })
        .catch((error) => {
          console.log('거래내역 에러', error);
        });


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
>>>>>>> 125d55b79ecf5a7dcc0f2a3dbae2916909fd201d
    }
  };

  // 다음 월 데이터 보기
  const sumMonth = () => {
    const now = new Date();
    if(year === now.getFullYear() && month === now.getMonth() + 1) return;

    if(month === 12) {
      setmymonth(`${year + 1}-01`);
      setYear(year + 1);
      setMonth(1);
    }
    else {
      setmymonth(`${year}-${String(month + 1).padStart(2, '0')}`);
      setMonth(month + 1);
    }
  };

  // 월이 바뀔 때 데이터 다시 불러오기
  useEffect(() => {
    if (mymonth !== '') { // mymonth가 빈 문자열이 아닐 때만 실행
    let body = {'yearAndMonth' : `${mymonth}`}
    customAxios
      .post(process.env.REACT_APP_BASE_URL + '/consume/checkaccount',body)
      .then((res) => {
        setTotalAmount(res.data.totalAmount);
        setTagList(res.data.tagList);
        console.log(tagList);
      })
      .catch((error) => {
        console.log('거래내역 에러', error);
      });
    }
  }, [mymonth])

  // 카테고리별 소비 내역 보러가기
  const handlefilter = (tagName)=>{
      const [a,bill] = tagName
      navigator(`/filter-purchase/${a}`,{ state: { mymonth,bill } })
  }

  // 전체 소비 내역 보러가기
  const goToFullHistory = () => {
    navigator('/entire-purchase');
  }

    const colors = [
      'rgb(255, 99, 132)',
      'rgb(54, 162, 235)',
      'rgb(255, 206, 86)',
      'rgb(75, 192, 192)',
      'rgb(153, 102, 255)'
    ];

    return (
        <>
            <div className='appbar-container'>
                {/* 이전 페이지 이동 버튼 */}
                <IconButton
                    className='go-back-icon'
                    onClick={goBack} 
                >
                    <ArrowBackIosNew/>
                </IconButton>
          
                {/* 연월 선택하기 */}
                <div className="select-month">
                    {/* 전 달 보기 버튼 */}
                    <IconButton
                      className='move-month-icon'
                      onClick={subMonth} 
                    >
                      <ArrowLeft/>
                    </IconButton>
                    {/* 현재 보고 있는 연월 정보 */}
                    <span>{year}년 {month}월 소비</span>
                    {/* 앞 달 보기 버튼*/}
                    <IconButton
                      className='move-month-icon'
                      onClick={sumMonth} 
                    >
                      <ArrowRight/>
                    </IconButton>
                </div>
        </div>
        
            <div className='consume-pattern-container'>
                <div className='profile'>
                  {tagList.length > 0 ? (
                    <div className='consume-pattern-doughnut-chart'>
                      <ConsumePatternChart tagList={tagList}/>
                    </div>
                    ) : (
                    <div className='consume-pattern-doughnut-chart'>
                      <EmptyConsumePatternChart/>
                    </div>
                    )}
                  <img className='profile-image' src={`/avatar/type${user.avatarType}.jpg`} alt="" /> 
                </div>
<<<<<<< HEAD
                {totalAmount !== null ? (
                  <div className="consume-list">
                    <div className='total-price'>
                      <div>￦ {totalAmount?.toLocaleString()}</div>
                      <button className='go-to-full-history' onClick={goToFullHistory}>전체 내역</button>
                    </div>
                    <div className='tag-list'>
                    {tagList.map((item,index)=>(
                          <div key = {index} className='tag'>
                              <FiberManualRecord style={{ color: index < 4 ? colors[index] : colors[4] }}/>
                              <div>{item.name}</div>
                              <div className='tag-amount'>
                                <div className='tag-amount-amount' onClick={handlefilter.bind(null,[item.name,item.amount])}>{item.amount?.toLocaleString() + '원'}</div>
                                <div className='tag-amount-percent'>({item.percent}%)</div>
                              </div>
=======
            </div>
            <div className='consume-pattern'>
                <div>
                  <img className='consume-responsive-image' src={`/avatar/type${user.avatarType}.jpg`} alt="" /> 
                </div>
                <div>
                    {user.userId}
                </div>
                {totalamount !== null ? (
                <div>
                  <div>{year}년 {month}월</div>
                  <div>총 금액 : {totalamount}원</div>
                    <div>
                    {highesttag.map((item,index)=>(
                          <div key = {index} className='consume-container'>
                              <div><CircleIcon></CircleIcon></div>
                              <div>{item.tag.name+" : "}</div>
                              <div>{item.amount + '원'}</div>
                              <StyledButton onClick={handlefilter.bind(null,[item.tag.name,item.amount])}></StyledButton>
>>>>>>> 125d55b79ecf5a7dcc0f2a3dbae2916909fd201d
                          </div>
                      ))}

                    </div>
<<<<<<< HEAD
                  </div>
                ) : (
                <div className="consume-list">
                  <div className='total-price'>
                    <div>￦ 0</div>
                    <button className='go-to-full-history' onClick={goToFullHistory}>전체 내역</button>
                  </div>
                </div>
                ) }
=======
                </div>
                     ) : null }
>>>>>>> 125d55b79ecf5a7dcc0f2a3dbae2916909fd201d
                     {/* // null 대신 이미지 넣어야할듯? */}
            </div>
        </>
    );
}

export default ConsumePatternPage;
