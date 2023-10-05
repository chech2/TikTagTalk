import './ItemCard.css'
import { useDispatch } from 'react-redux';
import { itemNumber } from '../redux/itemSlice';
import { useSelector } from 'react-redux';

function ItemCard({ item_key, item_url, item_name, item_price, onCardClick }) {
    const dispatch = useDispatch()
    // const iteminfor = useSelector((state)=>state.item)
    
    // const handleModal = ()=>{
    //     console.log('정보', props)
    //     dispatch(itemNumber(props))
    // }





    return (
        <>
        <div onClick={()=> onCardClick(item_key)} className='skin-cardback'>
            <img src={`./skin/${item_url}.png`} alt="" className='skin-cardsize'/>
            <div>{item_name}</div>
            <div className='skincard-container'>
                <img src="./Icon/Coin.png" alt="" />
                {item_price}
            </div>
        </div>
        </>
      );
    }

export default ItemCard;