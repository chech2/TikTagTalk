import './FriendListPage.css'
import React, { useEffect, useState } from "react";
import axios from "axios";
import RecommendFriendPage from './RecommendFriendPage';
import SearchFriendPage from './SearchFriendPage';
import { customAxios } from '../CustomAxios';
import Modal from '../components/ui/Modal';
import FriendModal from '../components/FriendModal'
import { useNavigate } from 'react-router';

function FriendListPage(){
    const navigate = useNavigate()
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [selectedFriends, setselectedFriends] = useState(null);
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
    const handleTalk = (friend)=>{
        setselectedFriends(friend);
        setIsModalOpen(true);
    }
    const handleMove = (friend) =>{
        console.log(friend)
        navigate(`/main/${friend.otherId}`)
    }
    // const handleCardClick = (itemKey) => {
    //     const selected = ItemLists.find((item) => item.item_key === itemKey);
    //     setSelectedCard(selected);
    //     setIsModalOpen(true);
    //   };
    const [usernameList, setusernameList] = useState([])
  
    // const [recieveList, setrecieveList] = useState([])
    const [received,setreceived] = useState([])
    const [request,setrequest] = useState([])
    const [friendList, setfreiendList] = useState([])


    // const [friendList, setfriendList] = useState([{
    //     id : 1,
    //     userId : '1',
    //     name : '일번',
    //     introduction : '나의소개글',
    //     profile_image : '/Icon/마이페이지 아이콘.png',
    //     },{
    //     id : 2,
    //     userId : '2',
    //     name : '이번',
    //     introduction : '나의소개글',
    //     profile_image : '/Icon/마이페이지 아이콘.png',
    //     },{
    //     id : 3,
    //     userId : '3',
    //     name : '삼번',
    //     introduction : '나의소개글',
    //     profile_image : '/Icon/마이페이지 아이콘.png',
    //     },{
    //     id : 4,
    //     userId : '4',
    //     name : '사번',
    //     introduction : '나의소개글',
    //     profile_image : '/Icon/마이페이지 아이콘.png',
    //     },
    // ])
    

    useEffect(()=>{
        customAxios.get(process.env.REACT_APP_BASE_URL + '/talk-talks')
        .then((res)=>{            
            console.log('받은친구',res)
            setreceived(res.data.filter(item =>item.status === 'RECEIVED'))
            setrequest(res.data.filter(item =>item.status === 'REQUESTING'))
            setfreiendList(res.data.filter(item =>item.status === 'TALK_TALK'))
        })
        .catch((err)=>{
            console.log('친구리스트 받기 에러',err)
        })
    },[])

    // useEffect(() => {
    //     // data 상태가 업데이트될 때마다 필터링 수행
    //     setreceived(received)
    //   }, [received]);



    // function filterData() {
    // const filtered = FriendList.filter(item => item.status === 'REQUESTING');
    // setrecieveList(filtered);
    // }

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
            {/* 친구 폼  */}
            <div>받은 친구 </div>
                <div className='friend-list'>
                    {received.map((friend,index) => (
                        <div key={friend.otherId} className='friend-item'>
                            <div>
                                {/* {`/avatar/type${comment.member.avatarType}.jpg`} */}
                                <img onClick={() => handleTalk(friend)} className='friend-responisve-img' src={`/avatar/type${friend.otherAvatarType}.jpg`}alt={friend.name} /> 
                            </div>
                            <div>
                                <h2>{friend.otherUserId}</h2>
                            </div>
                        </div>
                    ))}
                </div>

                <div>보낸 친구</div>
                <div className='friend-list'>
                    {request.map((friend) => (
                        <div key={friend.otherId} className='friend-item'>
                            <div>
                                <img className='friend-responisve-img' src={`/avatar/type${friend.otherAvatarType}.jpg`}alt={friend.name} /> 
                            </div>
                            <div>
                                <h2>{friend.otherUserId}</h2>
                            </div>
                        </div>
                    ))}
                </div>
                <div>친구목록</div>
                {/* 친구폼 끝 */}
            {showFriend === 1 ?  
            (friendList.length === 0 ? (
                // friendList 배열이 비어있는 경우
                <div>
                    <img src="/Icon/No friends.png" alt="" />
                </div>
            ) : (
                // friendList 배열에 친구가 있는 경우
                <>
                <div className='friend-list'>
                    {friendList.map((friend) => (
                        <div key={friend.id} className='friend-item'>
                            <div>
                                <img onClick={() => handleMove(friend)} className='friend-responisve-img' src={`/avatar/type${friend.otherAvatarType}.jpg`}alt={friend.name} /> 
                            </div>
                            <div>
                                <h2>{friend.otherUserId}</h2>
                                {/* <p>{friend.introduction}</p> */}
                            </div>
                        </div>
                    ))}
                </div>

                </>
            )) : showFriend === 2 ?   (
                <div className='friend-container2'> 
                    <RecommendFriendPage></RecommendFriendPage>
                </div>
            ) : (
                <div>
                    <SearchFriendPage data={usernameList}></SearchFriendPage>
                </div>
            )}       
                {isModalOpen && (
                    // <div className='modal-container'>
                        // <div className='modal-content'>
                            <Modal className='modal-container' closeModal={() => setIsModalOpen(!isModalOpen)}>
                                {selectedFriends && <FriendModal  friend={selectedFriends} closeModal={() => setIsModalOpen(false)}/>}
                            </Modal>
                        // </div>   
                    // </div>
                )}

            {/* FILTER 된 친구 목록 보여주어야 함*/}
        </>
    )
}
export default FriendListPage;