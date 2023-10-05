import { useEffect } from 'react'
import './NavBarMain.css'
import {useNavigate} from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'

<<<<<<< HEAD
import { IconButton } from '@mui/material';
=======
import { IconButton, Badge } from '@mui/material';
>>>>>>> 125d55b79ecf5a7dcc0f2a3dbae2916909fd201d
import { EditLocationAlt, Storefront, Add } from '@mui/icons-material';

function NavBarMain(props) {
    const pageid = props.id
    const userid = useSelector(state=>state.user.id)
    const shouldShowRightAlign = userid === pageid; // 조건부 랜더링
    const navigate = useNavigate()
    const dispatch = useDispatch()
    const handleComment = ()=>{
        navigate(`/comment/${props.mainpage_id}`)
    }
    const handleSkinPurchase = ()=>{
        navigate('/skin')
    }
    const handleExchange = ()=>{
        navigate('/exchange')
    }

    const mypoint = useSelector((state)=>state.user.point)
    const mycoin = useSelector((state)=> state.user.coin)



    return (
        <div className='navbar-container'>
            {/* 좌 정렬 */}
            <div className='navbar-left-container'>
                {/* 방명록 작성 페이지 이동 버튼 */}
                <IconButton
                    className='responsive-icon'
                    onClick={handleComment} 
                >
                    <EditLocationAlt/>
                </IconButton>

                {/* 스킨 구매창 이동 버튼 */}
                <IconButton
                    className='responsive-icon'
                    onClick={handleSkinPurchase}
                >
                    <Storefront/>
                </IconButton>
            </div>


            
            {/* 우 정렬 */}
            <div className='navbar-right-container'>
                <div className="point-coin-container">
                    <img className='responsive-image' src="/Icon/point.png" alt="" />
                    <span>{mypoint.toLocaleString()}</span>
                </div>
                <div className='point-coin-container'>
                    <img className='responsive-image' src="/Icon/Coin.png" alt="" />
                    <span>{mycoin.toLocaleString()}</span>
                </div>
                <div className='point-coin-container'>
                    <IconButton
                        className='responsive-icon'
                        onClick={handleExchange}
                    >
                        <Add/>
                    </IconButton>
                </div>    
            </div>
        </div>
    );
}

export default NavBarMain;