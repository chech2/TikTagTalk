import AppBar from '../components/ui/AppBar';
import './CommentPage.css'
import React, { useState} from 'react';

// import { useState } from "react";
// import { useSelector } from 'react-redux';
// import Modal from '../components/ui/Modal';
// import ItemModal from '../components/ItemModal';

function CommentPage(props) {
    const [isFriend,setisFriend] = useState(false)







    return (
        <>
        <div>
            <div>
                <img src="Icon/마이페이지 아이콘.png" alt="" />
                <h1>허주혁(이름)</h1>
                <div>
                    <button>톡톡 버튼 예정</button>
                </div>
                {/* container아래 */}
                <div style={{display: 'flex', justifyContent:'center' ,}}>
                    <div className='comment-box'>   
                        <p>댓글 수</p>
                        <p>3</p>
                    </div >
                    <div className='v-line'>
                    </div>
                    <div className='comment-box'>
                        <p>톡톡 수</p>
                        <p>5</p>
                    </div>
                </div>
                
            </div>
            <hr />
            {/* <div className='commentForm'>
                    <h2>댓글</h2>
                    <ul className='comment-list'>
                        {comments.map((comment, index) => (
                        <li key={index}>
                            <div className='comment-container'>
                                <div className='comment-profile-img'>
                                    <img src={comment.profileUrl}></img>
                                </div>
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
                                    {isLoggedIn && comment.writerId === userId&& (
                                            <div className='comment-delete' onClick={() => handleDeleteComment(comment.id)}>삭제하기</div>
                                        )}
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
                                    <div className='file-input'>
                                        <label>
                                            후기 이미지<br></br>첨부하기
                                            <input type='file' onChange={handleImageChange} style={{ display: 'none'} } />
                                        </label>
                                    </div>                  
                                    <button type='submit'>댓글 작성</button>
                                </div>
                            </form>
                        </div>


                </div>
            
            <div>
                <p>hi</p>
            </div> */}
        </div>
        </>
    );
}
export default CommentPage;