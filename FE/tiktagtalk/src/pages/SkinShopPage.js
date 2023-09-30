import ItemCard from '../components/ItemCard'
import AppBar from '../components/ui/AppBar';
import './SkinShopPage.css'
import { useState } from "react";
import { useSelector } from 'react-redux';
import Modal from '../components/ui/Modal';
import ItemModal from '../components/ItemModal';

function SkinShopPage(props) {
    // 서버에 요청 보내서 받기 or 프론트 내부 사진폴더 ? 그러면 json 형식 image 이름을 숫자식으로 나열 
    const [isModalOpen, setIsModalOpen] = useState(false);
    const iteminfor = useSelector((state)=>state.item)
    const userid = useSelector((state)=>state.user.id)
    const [ItemLists, setItemLists] = useState([
        { item_key : 0, item_url: '', item_name: '선풍기0', item_price: 100 },
        { item_key : 1, item_url: '', item_name: '선풍기1', item_price: 200 },
        { item_key : 2, item_url: '', item_name: '선풍기2', item_price: 350 },
        { item_key : 3, item_url: '', item_name: '선풍기3', item_price: 100 },
        { item_key : 4, item_url: '', item_name: '선풍기4', item_price: 200 },
        { item_key : 5, item_url: '', item_name: '선풍기5', item_price: 700 },
        { item_key : 6, item_url: '', item_name: '선풍기6', item_price: 200 },
        { item_key : 0, item_url: '', item_name: '선풍기0', item_price: 100 },
        { item_key : 1, item_url: '', item_name: '선풍기1', item_price: 200 },
        { item_key : 2, item_url: '', item_name: '선풍기2', item_price: 350 },
        { item_key : 3, item_url: '', item_name: '선풍기3', item_price: 100 },
        { item_key : 4, item_url: '', item_name: '선풍기4', item_price: 200 },
        { item_key : 5, item_url: '', item_name: '선풍기5', item_price: 700 },
        { item_key : 6, item_url: '', item_name: '선풍기6', item_price: 200 },
        { item_key : 0, item_url: '', item_name: '선풍기0', item_price: 100 },
        { item_key : 1, item_url: '', item_name: '선풍기1', item_price: 200 },
        { item_key : 2, item_url: '', item_name: '선풍기2', item_price: 350 },
        { item_key : 3, item_url: '', item_name: '선풍기3', item_price: 100 },
        { item_key : 4, item_url: '', item_name: '선풍기4', item_price: 200 },
        { item_key : 5, item_url: '', item_name: '선풍기5', item_price: 700 },
        { item_key : 6, item_url: '', item_name: '선풍기6', item_price: 200 },
        { item_key : 0, item_url: '', item_name: '선풍기0', item_price: 100 },
        { item_key : 1, item_url: '', item_name: '선풍기1', item_price: 200 },
        { item_key : 2, item_url: '', item_name: '선풍기2', item_price: 350 },
        { item_key : 3, item_url: '', item_name: '선풍기3', item_price: 100 },
        { item_key : 4, item_url: '', item_name: '선풍기4', item_price: 200 },
        { item_key : 5, item_url: '', item_name: '선풍기5', item_price: 700 },
        { item_key : 6, item_url: '', item_name: '선풍기6', item_price: 200 },
        { item_key : 0, item_url: '', item_name: '선풍기0', item_price: 100 },
        { item_key : 1, item_url: '', item_name: '선풍기1', item_price: 200 },
        { item_key : 2, item_url: '', item_name: '선풍기2', item_price: 350 },
        { item_key : 3, item_url: '', item_name: '선풍기3', item_price: 100 },
        { item_key : 4, item_url: '', item_name: '선풍기4', item_price: 200 },
        { item_key : 5, item_url: '', item_name: '선풍기5', item_price: 700 },
        { item_key : 6, item_url: '', item_name: '선풍기6', item_price: 200 },
    ]);

    const [selectedCard, setSelectedCard] = useState(null);
    const handleCardClick = (itemKey) => {
        const selected = ItemLists.find((item) => item.item_key === itemKey);
        setSelectedCard(selected);
        setIsModalOpen(true);
      };



    return (
        <>
            <div>
                <AppBar title='상점' id = {userid}></AppBar>
                <div className='skinshop-container'>
                    {ItemLists.map((item, index) => (
                        <ItemCard className='itemsize'
                            key={index}
                            item_key={item.item_key}
                            item_url={item.item_url}
                            item_name={item.item_name}
                            item_price={item.item_price}
                            onCardClick={handleCardClick}
                        />
                    ))}
                </div>
                
                {isModalOpen && (
                    // <div className='modal-container'>
                        // <div className='modal-content'>
                            <Modal className='modal-container' closeModal={() => setIsModalOpen(!isModalOpen)}>
                                {/* <ItemModal}></ItemModal> */}
                                {selectedCard && <ItemModal  item={selectedCard} />}
                            </Modal>
                        // </div>
                    // </div>
                )}
                
            </div>

        </>
    );
}

export default SkinShopPage;

// className='modal-conatiner'