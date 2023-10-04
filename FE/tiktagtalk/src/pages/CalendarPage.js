import React, { useState, useEffect } from 'react';
import Calendar from 'react-calendar'; // 적절한 캘린더 라이브러리를 가져옵니다.
import moment from 'moment';
import axios from 'axios';

function App() {
  const [value, setValue] = useState(new Date());
  const [mark, setMark] = useState([]); // 출석 체크된 날짜를 저장하는 배열
  const [isFirstAccessToday, setIsFirstAccessToday] = useState(false); // 당일 첫 접속인지 확인
  const [point, setPoint] = useState(0); // 현재 포인트 보유량


  useEffect(() => {
    // 회원가입 후 첫 접속 확인 API 호출
    axios.get('http://localhost:8080/api/firstLogin')
      .then(response => {
        // API 응답에서 isFirstAccessToday 값을 가져옴
        // (가정: API 응답 객체에 isFirstAccessToday 필드가 있다고 가정)
        const isFirst = response.data.isFirstAccessToday;

        setIsFirstAccessToday(isFirst);

        // isFirst가 true면 포인트 적립 API를 호출
        if (isFirst) {
        // 현재 포인트 가져오기
        axios.get('http://localhost:8080/api/getPoint')
          .then(response => {
            const currentPoint = response.data.point;
            setPoint(currentPoint);

            // 포인트 증가
            const updatedPoint = currentPoint + 50;
            axios.put('http://localhost:8080/api/updatePoint', { point: updatedPoint });

            // 오늘 날짜를 mark 배열에 추가
            setMark([...mark, moment(new Date()).format("YYYY-MM-DD")]);
          });
        }
      })
      .catch(error => {
        // API 호출 실패 시 처리 로직
        console.error('API 호출 실패:', error);
      });
  }, [mark]);

  return (
    <div className="App">
      <Calendar
        locale="en"
        value={value}
        next2Label={null}
        prev2Label={null}
        formatDay={(locale, date) => moment(date).format('D')}
        // tileContent={addContent}
        showNeighboringMonth={false}
      />
    </div>
  );
}

export default App;
