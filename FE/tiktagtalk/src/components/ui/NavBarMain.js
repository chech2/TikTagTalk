import { useEffect } from 'react'
import './NavBarMain.css'
import {useNavigate} from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'

function NavBarMain(props) {
    const navigate = useNavigate()
    const dispatch = useDispatch()
    const handleComment = ()=>{
        navigate(`/comment/${props.Nav_id}`)
    }
    const handleSkinPurchase = ()=>{
        navigate('/skin')
    }
    const handleNotice =()=>{
        navigate('/notice')
    }
    const handleExchange = ()=>{
        navigate('/exchange')
    }

    const mypoint = useSelector((state)=>state.user.point)
    const mycoin = useSelector((state)=> state.user.coin)



    return (
        <>
        <div className='navbarcontainer'>
            <div className='image-container'>
                <img className='responsive-image' src="/Icon/댓글 페이지창 버튼.png" alt="" onClick={handleComment}/>
                <img className='responsive-image' src="/Icon/스킨 구매창.png" alt="" onClick={handleSkinPurchase}/>
                <img className='responsive-image' src="/Icon/알림창 버튼.png" alt="" onClick={handleNotice}/>
            </div>


            
            {/* 우 정렬 */}
            <div className='image-container2'>
                <div className='image-container2-1'>
                    <img className='responsive-image2' src="/Icon/포인트 아이콘.png" alt="" />
                    <div>{mypoint}</div>
                </div>
                <div className='image-container2-1'>
                    <img className='responsive-image2' src="/Icon/Coin.png" alt="" />
                    <div>{mycoin}</div>
                    <img className='responsive-image2' src="/Icon/코인 구매창.png" alt="" onClick={handleExchange}/>
                </div>
            </div>
        </div>
        </>
      );
    }

export default NavBarMain;