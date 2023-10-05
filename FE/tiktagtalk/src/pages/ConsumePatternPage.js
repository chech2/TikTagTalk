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
                          </div>
                      ))}

                    </div>
                  </div>
                ) : (
                <div className="consume-list">
                  <div className='total-price'>
                    <div>￦ 0</div>
                    <button className='go-to-full-history' onClick={goToFullHistory}>전체 내역</button>
                  </div>
                </div>
                ) }
                     {/* // null 대신 이미지 넣어야할듯? */}
            </div>
        </>
    );
}

export default ConsumePatternPage;
