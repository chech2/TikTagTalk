import './NavBarMain.css'
import {useNavigate} from 'react-router-dom'

function NavBarMain(props) {
    const navigate = useNavigate()
    const handleComment = ()=>{
        navigate(`/comment/${props.Nav_id}`)
    }
    const handleSkinPurchase = ()=>{
        navigate('/skin')
    }
    const handleNotice =()=>{
        navigate('/notice')
    }


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
                    <div>500</div>
                </div>
                <div className='image-container2-1'>
                    <img className='responsive-image2' src="/Icon/Coin.png" alt="" />
                    <div>20000000</div>
                    <img className='responsive-image2' src="/Icon/코인 구매창.png" alt="" />
                </div>
            </div>
        </div>
        </>
      );
    }

export default NavBarMain;