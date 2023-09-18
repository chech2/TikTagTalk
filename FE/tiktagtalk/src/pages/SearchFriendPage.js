import React, { useEffect, useState, useRef } from "react";
import './SearchFriendPage.css'
import { useDispatch } from "react-redux";
// redux dispatch



function SearchFriendPage({data}) {
    const [filteredData, setFilteredData] = useState([]);
    const [wordEntered, setWordEntered] = useState("");
    const [needSearch, setNeedSearch] = useState(true);
    const dataResultRef = useRef(null);
    const dispatch = useDispatch();



    const handleFilter =(e)=>{
        const searchWord = e.target.value;
        setWordEntered(searchWord);
        const newFilter = data.titles.filter((title)=>{
            return title.toLowerCase().includes(searchWord.toLowerCase());
        });
        if (searchWord === "") {
            setFilteredData([]);
        } else {
            setFilteredData(newFilter);
        }
    };
    const activeEnter = (e) =>{
        if (e.key === 'Enter') {
            // handleSendKeyword()
        }
    }



    return (
        <>
            <div className="search">
                <div className="searchInputs">
                    <input 
                        className="searchIn"
                        type="text"
                        placeholder="유저 검색"
                        value={wordEntered}
                        onChange={handleFilter}
                        onKeyDown={(e)=>activeEnter(e)}    
                    />
                        

                </div>
            </div>

        </>
    );
}

export default SearchFriendPage;