import './AppBar.css'


function AppBar(props) {
    return (
        <>
        <div className='appbar-underline'>
            <img src="./Icon/뒤로가기.png" alt="" className='appbar-absoulte'/>
            <div className='appbarcontainer'>
                <p className='appbarcenter'> {props.title} </p>
            </div>
        </div>
        </>
      );
    }

export default AppBar;