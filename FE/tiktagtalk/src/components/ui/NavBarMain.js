import './NavBarMain.css'


function NavBarMain() {
    return (
        <>
        <div className='navbarcontainer'>
            <div className='image-container'>
                <img src="./Icon/댓글 페이지창 버튼.png" alt="" />
                <img src="./Icon/스킨 구매창.png" alt="" />
                <img src="/Icon/알림창 버튼.png" alt="" />
            </div>
            {/* 우 정렬 */}
            <div>
                <div className='navbarcontainer-2'>
                    <img src="./Icon/포인트 아이콘.png" alt="" />
                    <p className='p-left'>500</p>
                </div>
                <div className='navbarcontainer-2'>
                    <img src="./Icon/Coin.png" alt="" />
                    <p className='p-left'>20000000</p>
                    <img src="./Icon/코인 구매창.png" alt="" />
                </div>
            </div>
        </div>
        </>
      );
    }

export default NavBarMain;