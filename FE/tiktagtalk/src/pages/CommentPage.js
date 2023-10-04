import AppBar from '../components/ui/AppBar';
import './CommentPage.css'
import React, { useState,useEffect} from 'react';
import axios from 'axios';
import { useParams } from 'react-router';
import { customAxios } from '../CustomAxios';
import { useSelector } from 'react-redux';
import { FaPlus } from 'react-icons/fa';
// import { useState } from "react";
// import { useSelector } from 'react-redux';
// import Modal from '../components/ui/Modal';
// import ItemModal from '../components/ItemModal';

function CommentPage(props) {
    const {id} = useParams();
    const user = useSelector((state)=> state.user)
    const [isFriend,setisFriend] = useState(false)
    const [comments, setComments] = useState([]);
    // const isLoggedIn = useSelector(state => state.user.isLogin);
    const isLoggedIn = useState(true) // 임시
    // const userId=useSelector(state=>state.user.id);

    const [friendNums,setfriendNums] = useState(0)
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

    //comment 목록 불러오기 시작
    useEffect(() => {
        const fetchData = async () => {
          try {
            console.log("tagRoom : "+id);
            // const ownerOfCommentRoom =await fetch(
            //     process.env.REACT_APP_BASE_URL
            // ) 태그룸의 주인을 tagroom db에서 가져와서 프로필 갖고 오자
            const responseComment = await fetch(
              process.env.REACT_APP_BASE_URL + `/comment/${id}`,
              {
                method: 'GET',
                headers: {
                  'Content-Type': 'application/json',
                  'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
                },
              }
            );
            if (responseComment.status === 200) {
              const commentList = await responseComment.json();
              setComments(commentList);
            } else {
              console.error('댓글 불러오기 실패');
            }
          } catch (error) {
            console.error('에러 발생', error);
          }
        };
        fetchData();
      }, [id]);

    //comment 목록 불러오기 끝






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

    //comment 등록 시작
    const [newCommentContent, setNewCommentContent] = useState('');
    const handleCommentChange = (event) => {
        setNewCommentContent(event.target.value);
    };

    const handleSubmitComment = async (event) => {
        
        event.preventDefault();
        const comment={
            tagRoom:{
                id: id
            },
            content:newCommentContent,
        }
        alert("댓글 작성완료");
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
            const response = await axios.post(
                `${process.env.REACT_APP_BASE_URL}/comment`,
                JSON.stringify(comment), // 직렬화된 JSON 문자열을 전송
                {
                    headers: {
                        'Content-Type': 'application/json; charset=UTF-8',
                        'Authorization' : 'Bearer '+localStorage.getItem("accessToken")
                    }
                }
            );
            if (response.status === 200) {
                // 댓글 작성 후 댓글 목록을 다시 가져온다.
                const responseComment = await fetch(
                    process.env.REACT_APP_BASE_URL + `/comment/${id}`,
                    {
                      method: 'GET',
                      headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
                      },
                    }
                  );
                const commentList = await responseComment.json();
                setComments(commentList);
                // 댓글 작성 내용 초기화
                setNewCommentContent('');
            } else {
                console.error('댓글 작성 실패');
            }
        } catch (error) {
            console.error('에러 발생', error);
        }
    };
    //comment 등록 끝
    //comment 삭제 시작
    const handleDeleteComment = async (commentId) => {
        try {
            const response = await fetch(
                process.env.REACT_APP_BASE_URL+`/comment/${commentId}`,
                {
                    method:'DELETE',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
                      },
                }
            );
    
            if (response.status === 200) {
                // 댓글 삭제 후 댓글 목록을 다시 가져온다.
                const responseComment = await fetch(
                    process.env.REACT_APP_BASE_URL + `/comment/${id}`,
                    {
                      method: 'GET',
                      headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
                      },
                    }
                  );
                const commentList = await responseComment.json();
                setComments(commentList);
            } else {
                // console.error('댓글 삭제 실패');
            }
        } catch (error) {
            // console.error('에러 발생', error);
        }
    };
    //comment 삭제 끝

    // useEffect(()=>{
    //     axios.get(process.env.REACT_APP_BASE_URL + '/talk-talk')
    //     .then((res)=>{
    //     console.log(res)
    //     setfriendNums(res.members.length)
    //     setuserName(res.userId)
    //     setuserId(res.id)
    // })
    // })

    //yarn add react-icons -> 리액트 아이콘 라이브러리 다운!!!
    return (
        <>
        <AppBar></AppBar>
        <div>
            <div className='comment-user-info'>
                <img className='comment-responsive-image' src={`/avatar/type${user.avatarType}.jpg`} alt="" /> 
                <h2>{user.userId}</h2>
                <div className='comment-user-talktalk-button'>
                    <button onClick={handleAddTalk}><FaPlus className='FaPlus'></FaPlus> 톡톡</button>
                </div>
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
                    <ul className='comment-list'>
                        {comments.map((comment, index) => (
                        <li key={index}>
                            <div className='comment-container'>
                                <div className='comment-profile-img'>
                                    <img src={`/avatar/type${comment.member.avatarType}.jpg`}></img>
                                </div>
                                <div className='comment-form-content'>
                                    <div className='comment-info'>
                                        <div className='comment-writer-info'>
                                            {comment.member.name}
                                            
                                        </div>
                                        <div className='comment-writtenTime'>
                                            {formatDate(comment.writtenTime)}
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
                                        
                                    {isLoggedIn[0] && comment.member.id == user.id&& (
                                            <div className='comment-delete' onClick={() => handleDeleteComment(comment.id)}>삭제하기</div>
                                        )}
                                        

                                </div>
                            </div>
                            {index !== comments.length - 1 && <hr />} {/* 마지막 아이템이 아닌 경우에만 <hr>을 렌더링 */}
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