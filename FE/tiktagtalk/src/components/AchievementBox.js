import React from 'react';
import './AchievementBox.css';

function AchievementBox(props) {


  return (
    <>
    
    <div className='achievementbox'> 
        <img src="/Icon/No friends.png" alt="" className='achievementbox-image'/>
        <div>
            <p>임시 태그박스 제목</p>
            <p>임시 태그박스 내용</p>
        </div>

        {/* <img src={props.url} alt="" />
        <div>
            <p>{props.intro}</p>
            <p>{props.intro2}</p>
        </div> */}
        

    </div>
   
    </>
  );

}

export default AchievementBox;
