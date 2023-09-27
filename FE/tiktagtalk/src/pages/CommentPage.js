import AppBar from '../components/ui/AppBar';
import './CommentPage.css'
import React, { useState,useEffect} from 'react';
import axios from 'axios';
import { useParams } from 'react-router';
import { customAxios } from '../CustomAxios';
import { useSelector } from 'react-redux';
// import { useState } from "react";
// import { useSelector } from 'react-redux';
// import Modal from '../components/ui/Modal';
// import ItemModal from '../components/ItemModal';

function CommentPage(props) {
    const {id} = useParams();
    const user = useSelector((state)=> state.user)
    const [isFriend,setisFriend] = useState(false)
    const [comments, setComments] = useState([]);
    const [newCommentContent, setNewCommentContent] = useState('');
    // const isLoggedIn = useSelector(state => state.user.isLogin);
    const isLoggedIn = useState(true) // 임시
    // const userId=useSelector(state=>state.user.id);

    const [friendNums,setfriendNums] = useState(0)
    // const [userName, setuserName] = useState('허')
    const [userId, setuserId] = useState(1)
    const [userAvatarType, setuserAvatarType] = useState(1)

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        return `${year}-${month}-${day} ${hours}:${minutes}`;
    };
    const handleAddTalk = ()=>{
        customAxios.post(process.env.REACT_APP_BASE_URL + '/talk-talks',id)
        .then((res)=>{
            console.log(res)
        })
        .catch((err)=>{
            // console.log(err)
        })
    } 

    const handleSubmitComment = ()=>{

    }
    const handleCommentChange = ()=>{

    }

    // useEffect(()=>{
    //     axios.get(process.env.REACT_APP_BASE_URL + '/talk-talk')
    //     .then((res)=>{
    //     console.log(res)
    //     setfriendNums(res.members.length)
    //     setuserName(res.userId)
    //     setuserId(res.id)
    // })
    // })


    return (
        <>
        <AppBar title='방명록'></AppBar>
        <div>
            <div>
                {/* 마이페이지 아이콘 변경해야됨 */}
                <img className='comment-responsive-image' src="Icon/마이페이지 아이콘.png" alt="" /> 
                <h1>{user.userId}</h1>
                { id === userId ? ( null) :(
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
                        <div>{friendNums}</div>
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