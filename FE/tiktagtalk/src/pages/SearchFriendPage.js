import React, { useEffect, useState, useRef } from "react";
import './SearchFriendPage.css'
import { useDispatch } from "react-redux";
import { customAxios } from "../CustomAxios";
// redux dispatch



function SearchFriendPage({data}) {
    const [filteredData, setFilteredData] = useState([]);
    const [wordEntered, setWordEntered] = useState("");
    const [needSearch, setNeedSearch] = useState(true);
    const dataResultRef = useRef(null);
    const [searchedData,setsearchedData] = useState([])





    useEffect(()=>{
        document.addEventListener("click", handleClickOutside);
        document.addEventListener("keydown", handleKeyDown);
    return ()=>{
        document.removeEventListener("click", handleClickOutside);
        document.removeEventListener("keydown", handleKeyDown);
    };
    },[]);

    const handleClickOutside =(e)=>{
        if (dataResultRef.current && !dataResultRef.current.contains(e.target)) {
            setFilteredData([]);
        }
    };

    const handleKeyDown = (e) =>{
        if (e.keyCode === 27) {
            setFilteredData([]);
        }
    }

    useEffect(()=>{
        if (wordEntered===""){
            setNeedSearch(true)
        }
    },[wordEntered])


    const handleFilter =(e)=>{
        const searchWord = e.target.value;
        setWordEntered(searchWord);
        console.log('인풋내용',searchWord)
        
        // const newFilter = data.titles.filter((title)=>{
        //     return title.toLowerCase().includes(searchWord.toLowerCase());
        // });
        // if (searchWord === "") {
        //     setFilteredData([]);
        // } else {
        //     setFilteredData(newFilter);
        // }
    };
    const handleSendKeyword = (e)=>{
        // e.preventDefalut()
        let body = {'userId':`${wordEntered}`}
        console.log(body)
        customAxios
        .post(process.env.REACT_APP_BASE_URL + '/members/search',body, {
            headers: {
                'Content-Type': 'application/json'
            }
        }
        
        )
        .then((res) => {
            console.log('친구검색',res.data)
            setsearchedData(res)
        })
        .catch((error) => {
          console.log('친구검색 에러', error);
        });
        // dispatch() // reudx 
    }
    const activeEnter = (e) =>{
        if (e.key === 'Enter') {
            handleSendKeyword()
        }
    }



    return (
        <>
            <div className="search">
                <div className="searchInputs">
                    <input 
                        className="searchInput"
                        type="text"
                        placeholder="유저 검색"
                        value={wordEntered}
                        onChange={handleFilter}
                        onKeyDown={(e)=>activeEnter(e)}    
                    />
                    {/* <div onClick={handleSendKeyword} className="searchIcon">
                        <ion-icon name='search-outline'></ion-icon>
                    </div> */}
                    <button className='search-button' onClick={handleSendKeyword}>검색</button>
                </div>

                    {/* searchedData로 mapping  */}
                    {searchedData.map((friend) => (
                        <div key={friend.id} className='friend-item'>
                            <div>
                                {/* onClick={() => handleMove(friend)} */}
                                <img className='friend-responisve-img' src={`/avatar/type${friend.avatarType}.jpg`}alt={friend.name} /> 
                            </div>
                            <div>
                                <h2>{friend.userId}</h2>
                                {/* <p>{friend.introduction}</p> */}
                            </div>
                        </div>
                    ))}




                {/* {filteredData.legnth !==0 && needSearch && (
                    <div className="dataResult" ref={dataResultRef}>
                    {filteredData.slice(0,5).map((title,key)=>{
                        return (
                            <p key={key} className="titleClick" onClick={()=>{
                                setWordEntered(title);
                                setNeedSearch(false);
                            }}>{title}</p>
                        );
                    })}
                    </div>
                )} */}
            </div>

        </>
    );
}

export default SearchFriendPage;