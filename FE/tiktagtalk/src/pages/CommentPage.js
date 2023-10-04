import AppBar from '../components/ui/AppBar';
import './CommentPage.css'
import React, { useState,useEffect} from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router';
import { customAxios } from '../CustomAxios';
import { useSelector } from 'react-redux';
import FriendListPage from './FriendListPage';
// import { useState } from "react";
// import { useSelector } from 'react-redux';
// import Modal from '../components/ui/Modal';
// import ItemModal from '../components/ItemModal';

function CommentPage(props) {
    const {id} = useParams();
    const user = useSelector((state)=> state.user)
    const navigate = useNavigate()
    // 댓글
    const [data, setData] = useState(null);
    const userId = useSelector(state=>state.user.userId); // 진짜 user의 아이디값
    const userAvatar = useSelector(state=>state.user.avatarType)
    const isLoggedIn = useSelector(state => state.user.isLogin);
    const [comments, setComments] = useState([]);
    

    useEffect(()=>{
        customAxios.get(process.env.REACT_APP_BASE_URL+`/comment/${id}`)
        .then((res)=>{
            console.log('댓글 get요청',res)
        })
        .catch((err)=>{
            console.log('댓글 get요청실패', err)
        })

    },[id]);
        // const fetchData=async()=>{
        //     try{
        //         const response =customAxios.get(process.env.REACT_APP_BASE_URL+`/comment/${id}`);
        //         const responseComment=customAxios(process.env.REACT_APP_BASE_URL+`/comment?tagRoomId=${id}`,{'Content-Type': 'application/json'})
        //         if(!response.ok){
        //             // console.log('에러에러 error: ');
        //         }
        //         const data=await response.json();
        //         const comment=await responseComment.json(); 
        //         setComments(comment);

        //         setData(data);
        //     }catch(error){
        //         // console.error('Error occured ',"문제야 문제");
        //     }
        // };
        // fetchData();


    //
    const [isFriend,setisFriend] = useState(false)
    const [newCommentContent, setNewCommentContent] = useState('');
    // const isLoggedIn = useSelector(state => state.user.isLogin);

    // const userId=useSelector(state=>state.user.id);

    const [friendNums,setfriendNums] = useState(0)
    const [FreiendList,setFreiendList] = useState([])
    // const [userName, setuserName] = useState('허')
    // const [userId, setuserId] = useState(1)
    // const [userAvatarType, setuserAvatarType] = useState(1)

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        return `${year}-${month}-${day} ${hours}:${minutes}`;
    };
    let body = {memberId : id}

    const handleAddTalk = ()=>{
        console.log('id임:',id)
        customAxios.post(process.env.REACT_APP_BASE_URL + '/talk-talks', {'memberId':`${id}`})
        .then((res)=>{
            console.log(res)
        })
        .catch((err)=>{
            // console.log(err)
        })
    } 

    const handleSubmitComment = async (event) => {
        event.preventDefault();
        // const formData = new FormData();
        const comment={
            'content':`${newCommentContent}`,
            'tagRoom':{'id':id}
        }
        // formData.append('dto',new Blob([JSON.stringify(comment)],{type:"application/json"}));   
        // console.log('comment:',comment)     
        // if (!isLoggedIn) {
            
        //     Confirm().then(() => {
        //         // Handle anything else after confirmation if needed
        //     });
        //     return ;
        // }
        // if (!newCommentContent.trim()) {
        //     CommentAlert().then(()=>{

        //     });
        //     return ;
        // }
        try {
            const response = await customAxios.post(
                `${process.env.REACT_APP_BASE_URL}/comment`,{'content':`${newCommentContent}`,'tagRoom':{'id':userId}});
            if (response.status === 200) {
                // 댓글 작성 후 댓글 목록을 다시 가져온다.
                const responseComment = await customAxios.get(
                    `${process.env.REACT_APP_BASE_URL}/comment/${id}`
                );
                const comment = responseComment.data;
                setComments(comment);
                // 댓글 작성 내용 초기화
                setNewCommentContent('');
            } else {
                // console.error('댓글 작성 실패');
            }
        } catch (error) {
            // console.error('에러 발생', error);
        }
    };



    const handleCommentChange = (event) => {
        setNewCommentContent(event.target.value);
        // console.log('댓글 내용임:',newCommentContent)
    };




    useEffect(()=>{
        customAxios.get(process.env.REACT_APP_BASE_URL + '/talk-talks')
        .then((res)=>{            
            console.log('받은친구',res)
            setFreiendList(res.data.filter(item =>item.status === 'RECEIVED'))
        })
        .catch((err)=>{
            console.log('친구리스트 받기 에러',err)
        })
    },[])



    return (
        <>
        <AppBar title='방명록' id={id} ></AppBar>
        <div>
            <div>
                {/* 마이페이지 아이콘 변경해야됨 */}
                <img className='comment-responsive-image' src={`/avatar/type${userAvatar}.jpg`} alt="" /> 
                <h1>{userId}</h1>
                { id == user.id ? (null) :(
                <div>
                    <button onClick={handleAddTalk}>톡톡 버튼</button>
                </div>
                ) }
                <br />
                {/* container아래 */}
                <div style={{display: 'flex', justifyContent:'center' ,}}>
                    <div className='comment-box'>   
                        <div>댓글 수</div>
                        <div>{friendNums}</div>
                    </div >
                    <div className='v-line'>
                    </div>
                    <div className='comment-box'>
                        <div>톡톡 수</div>
                        <div>{FreiendList.length}</div>
                    </div>
                </div>
                
            </div>
            <hr />
            <tr/>
            <div className='commentForm'>
                    <h2>방명록</h2>
                    <ul className='comment-list'>
                        {comments.map((comment, index) => (
                        <li key={index}>
                            <div className='comment-container'>
                                {/* <div className='comment-profile-img'>
                                    <img src={comment.profileUrl}></img>
                                </div> */}
                                <div className='comment-form-content'>
                                    <div className='comment-info'>
                                        <div className='comment-writer-info'>
                                            {comment.writer}
                                            
                                        </div>
                                        <div className='comment-writtenTime'>
                                            {formatDate(comment.writeTime)}
                                        </div>
                                    </div>
                                        <div className='comment-content-delete'>
                                            <div className='comment-content'>{comment.content}</div>
                                        </div>
                                        {comment.commentImgUrl && (
                                            <div className='comment-img'>
                                                <img className='comment-content-img' src={comment.commentImgUrl}></img>
                                            </div>
                                        )}
                                    {/* {isLoggedIn && comment.writerId === userId&& (
                                            <div className='comment-delete' onClick={() => handleDeleteComment(comment.id)}>삭제하기</div>
                                        )} */}
                                </div>
                            </div>
                            <hr></hr>
                        </li>
                        ))}
                    </ul>
                                            
                        <div className='comment-form'>
                            <form onSubmit={handleSubmitComment} className="comment-input-container">
                                <div className="comment-input">
                                    <textarea
                                        placeholder='댓글을 입력하세요...'
                                        value={newCommentContent}
                                        onChange={handleCommentChange}
                                    />
                                    {/* <div className='file-input'>
                                        <label>
                                            후기 이미지<br></br>첨부하기
                                            <input type='file' onChange={handleImageChange} style={{ display: 'none'} } />
                                        </label>
                                    </div>                   */}
                                    <button type='submit'>댓글 작성</button>
                                </div>
                            </form>
                        </div>
                </div>
        </div>
        </>
    );
}
export default CommentPage;










