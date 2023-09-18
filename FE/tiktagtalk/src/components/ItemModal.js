import './ItemModal.css'
import ItemCard from './ItemCard';
function ItemModal(props) {

    return (
        <>
        <div>
            {/* <ItemCard
            item_url={props.item_url}
            item_name={props.item_name}>
            </ItemCard> */}
            <div>
                <img src="./Icon/Coin.png" alt="" />
                <p>{props.price} 구매하실?</p>
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