import ItemCard from '../components/ItemCard'
import AppBar from '../components/ui/AppBar';
import './SkinShopPage.css'
function SkinShopPage(props) {
    return (
        <>
            <div>
                <AppBar title = '상점'></AppBar>
                <p>카테고리 목록으로 들어가야됨</p>
                <div className='skinshop-container'>
                    <ItemCard price = '200'></ItemCard>
                    <ItemCard price = '300'></ItemCard>
                    <ItemCard price = '200'></ItemCard>
                    <ItemCard price = '300'></ItemCard>
                </div>

            </div>

        </>
      );
    }

export default SkinShopPage;