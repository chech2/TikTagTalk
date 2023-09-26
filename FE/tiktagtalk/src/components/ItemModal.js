import './ItemModal.css'
import ItemCard from './ItemCard';
function ItemModal({ item }) {

    return (
        <>
        <div>
            <div className='modal-flex'>
                <img className='modal-responsive' src="./Icon/Coin.png" alt="" />
                <p>{item.item_price} 구매하실?</p>
                </div>
            <div>
                <button>구매</button>
                <button>취소</button>
            </div>
        </div>
        </>
      );
    }

export default ItemModal;