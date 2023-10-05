import './FriendModal.css'
import { customAxios } from '../CustomAxios';

// import ItemCard from './ItemCard';
function FriendModal({ friend, closeModal }) {

    const handleAccept = ()=>{
        customAxios.put(process.env.REACT_APP_BASE_URL + `/talk-talks/${friend.id}`)
        .then((res)=>{            
            console.log('친구수락',res)
            window.alert('친구 요청을 수락했습니다.')
            closeModal()
            
        })
        .catch((err)=>{
            console.log('친구수락 에러',err)
        })
    }
    const handleRefuse = ()=>{
        console.log(friend.otherId)
        customAxios.delete(process.env.REACT_APP_BASE_URL + `/talk-talks/${friend.id}`)
        .then((res)=>{            
            console.log('친구거절',res)
            closeModal()
        })
        .catch((err)=>{
            console.log('친구거절 에러',err)
        })
    }

    return (
        <>
        <div>
            <div className='modal-flex'>
                <img className='modal-responsive' src={`/avatar/type${friend.otherAvatarType}.jpg`} alt="" />
            </div>
            <div className='modal-black'>{friend.otherUserId}</div>
            <div>
                <button onClick={handleAccept}>승락</button>
                <button onClick={handleRefuse}>거절</button>
            </div>
        </div>
        </>
      );
    }

export default FriendModal;