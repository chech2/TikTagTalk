import './FriendListPage.css'
import React, { useEffect, useState } from "react";
import axios from "axios";
import RecommendFriendPage from './RecommendFriendPage';
import SearchFriendPage from './SearchFriendPage';



function FriendListPage(){
    const [showFriend, setShowFriend] = useState(1)
    const hanldeMyfriend = ()=>{
        setShowFriend(1)
    }
    const hanldeRecommendfriend = ()=>{
        setShowFriend(2)
    }
    const handleSearchfriend = ()=>{
        setShowFriend(3)
    }
    const [usernameList, setusernameList] = useState([])

    const [friendList, setFriendList] = useState([{
        id : 1,
        userId : '1',
        name : '일번',
        introduction : '나의소개글',
        profile_image : '/Icon/마이페이지 아이콘.png',
        },{
        id : 2,
        userId : '2',
        name : '이번',
        introduction : '나의소개글',
        profile_image : '/Icon/마이페이지 아이콘.png',
        },{
        id : 3,
        userId : '3',
        name : '삼번',
        introduction : '나의소개글',
        profile_image : '/Icon/마이페이지 아이콘.png',
        },{
        id : 4,
        userId : '4',
        name : '사번',
        introduction : '나의소개글',
        profile_image : '/Icon/마이페이지 아이콘.png',
        },
    ])
    

    useEffect(()=>{
        axios.get('/api/talk-talk')
        .then((res)=>{
            setFriendList(res.data)
        })
        .catch((err)=>{
            console.log('친구리스트 받기 에러',err)
        })
    },[])

    return(
        <>
            <div className='friend-container'>
                <h1>친구</h1>
                {/* <img src="./Icon/Search.png" alt="" className='search-icon' /> */}
            </div>
            <div className='friend-container'>
                <div className={showFriend === 1 ? 'yellow-background' : 'black-background'} onClick={hanldeMyfriend}>My Talk</div>
                <div className={showFriend === 2 ? 'yellow-background' : 'black-background'} onClick={hanldeRecommendfriend}>Recommend Talk</div>
                <div className={showFriend === 3 ? 'yellow-background' : 'black-background'} onClick={handleSearchfriend}>Search Talk</div>    
            </div>
            {/* {`friend-class${showFriend}`} */}

            {showFriend === 1 ?  
            (friendList.length === 0 ? (
                // friendList 배열이 비어있는 경우
                <div>
                    <img src="/Icon/No friends.png" alt="" />
                </div>
            ) : (
                // friendList 배열에 친구가 있는 경우
                <div className='friend-list'>
                    {friendList.map((friend) => (
                        <div key={friend.id} className='friend-item'>
                            <div>
                                <img src={friend.profile_image} alt={friend.name} />
                            </div>
                            <div>
                                <h2>{friend.name}</h2>
                                {/* <p>{friend.introduction}</p> */}
                            </div>
                        </div>
                    ))}
                </div>
            )) : showFriend === 2 ?   (
                <div className='friend-container2'> 
                    <RecommendFriendPage></RecommendFriendPage>
                </div>
            ) : (
                <div>
                    <SearchFriendPage data={usernameList}></SearchFriendPage>
                </div>
            )}       


            {/* FILTER 된 친구 목록 보여주어야 함*/}
        </>
    )
}
export default FriendListPage;