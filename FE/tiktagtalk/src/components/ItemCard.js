import '../css/ItemCard.css'


function ItemCard(props) {
    return (
        <>
        <div>
            <img src="logo192.png" alt="" />
            <div>{props.name}</div>
            <div className='container'>
                <img src="Coin.png" alt="" />
                {props.price}
            </div>
        </div>
        </>
      );
    }

export default ItemCard;