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

    const [ItemLists, setItemLists] = useState([
        { item_key : 0, item_url: '', item_name: '선풍기0', item_price: 100 },
        { item_key : 1, item_url: '', item_name: '선풍기1', item_price: 200 },
        { item_key : 2, item_url: '', item_name: '선풍기2', item_price: 350 },
        { item_key : 3, item_url: '', item_name: '선풍기3', item_price: 100 },
        { item_key : 4, item_url: '', item_name: '선풍기4', item_price: 200 },
        { item_key : 5, item_url: '', item_name: '선풍기5', item_price: 700 },
        { item_key : 6, item_url: '', item_name: '선풍기6', item_price: 200 }
    ]);

    return (
        <>
            <div>
                <AppBar title='상점'></AppBar>
                <p>카테고리 목록으로 들어가야됨</p>
                <div className='skinshop-container'>
                    {ItemLists.map((item, index) => (
                        <ItemCard className='itemsize'
                            key={index}
                            item_key={item.item_key}
                            item_url={item.item_url}
                            item_name={item.item_name}
                            item_price={item.item_price}
                        />
                    ))}
                </div>
                
                {isModalOpen && (
                <Modal closeModal={() => setIsModalOpen(!isModalOpen)}>
                    {/* <ItemModal}></ItemModal> */}
                </Modal>)}
            </div>

        </>
    );
}

export default SkinShopPage;
