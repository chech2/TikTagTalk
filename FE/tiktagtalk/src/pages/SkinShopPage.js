import ItemCard from '../components/ItemCard'
import AppBar from '../components/ui/AppBar';
import './SkinShopPage.css'
import { useState,useEffect } from "react";
import { useSelector } from 'react-redux';
import Modal from '../components/ui/Modal';
import ItemModal from '../components/ItemModal';
import { customAxios } from '../CustomAxios';
function SkinShopPage(props) {
    // 서버에 요청 보내서 받기 or 프론트 내부 사진폴더 ? 그러면 json 형식 image 이름을 숫자식으로 나열 
    const [isModalOpen, setIsModalOpen] = useState(false);
    const iteminfor = useSelector((state)=>state.item)
    const userid = useSelector((state)=>state.user.id)
    const [ItemLists, setItemLists] = useState([
        { item_key : 0, item_name: '이발소 전용 빠따', item_url: 'barbers_pole', item_price: 100 },
        { item_key : 1, item_name: '삐진 커피잔', item_url: 'cute_coffee_cup', item_price: 100 },
        { item_key : 2, item_name: '구찌', item_url: 'gucci', item_price: 100 },
        { item_key : 3, item_name: '포르쉐 911', item_url: 'porsche_911', item_price: 100 },
        { item_key : 4, item_name: '배그 구급상자', item_url: 'pubg_mobile_first_aid_kit', item_price: 100 },
        { item_key : 5, item_name: '로봇 털바퀴', item_url: 'robo_cat', item_price: 100 },
        { item_key : 6, item_name: '금고', item_url: 'safe', item_price: 100 },
        { item_key : 7, item_name: '개복치 햄버거', item_url: 'sculptober_burger', item_price: 100 },
        { item_key : 8, item_name: '자판기', item_url: 'vending_machine', item_price: 100 },
        { item_key : 9, item_name: '추억의 컨트롤러', item_url: 'vintage_controller', item_price: 100 },
        { item_key : 10, item_name: '빈티지 여행가방', item_url: 'vintage_luggage', item_price: 100 },
        { item_key : 11, item_name: '골드버튼', item_url: 'youtube_gold_play_button', item_price: 100 },
    ]);

    const [selectedCard, setSelectedCard] = useState(null);
    const handleCardClick = (itemKey) => {
        const selected = ItemLists.find((item) => item.item_key === itemKey);
        setSelectedCard(selected);
        setIsModalOpen(true);
      };

    // item 요청
    // useEffect(()=>{
    //     customAxios.get(process.env.REACT_APP_BASE_URL+'/' , )
    //     .then((res)=>{
    //         console.log(res)
    //         setItemLists(res)
    //     })
    //     .catch((err)=>{
    //         console.log(err)
    //     })
    // })

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