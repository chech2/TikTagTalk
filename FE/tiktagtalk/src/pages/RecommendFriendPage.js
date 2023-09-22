import './RecommendFriendPage.css'
import React, {useCallback, useEffect, useState} from 'react';
import { ReactTags } from 'react-tag-autocomplete';

import { useDispatch, useSelector } from 'react-redux';
// import { updateIngredients, requestFilteredRecipeList } from "../../redux/recipeSearchSlice";
import useDidMountEffect from '../hooks/useDidMountEffect'
import axios from 'axios';


function RecommendFriendPage() {

    // 처음에 useDidMountEffect 를 통해 내 소비성향카테고리를 불러온다.

    const dispatch = useDispatch();
    const [suggestions, setSuggestions] = useState('');
    const [selected, setSelected] = useState([]);

    // const detectSelected = useSelector((state)=>state.recipeSearch.detectedingredients)

    // useDidMountEffect(()=>{
    
    //     setSelected([...detectSelected, ...selected]);
    //     // console.log('합쳐져라 머리머리:', detectSelected)
    //   }, [detectSelected])
    
    // useDidMountEffect(()=>{
    // dispatch(updateIngredients(selected))
    // // console.log('궁금증:', selected)
    // }, [selected])


    // useEffect(()=>{
    //     function getRandomBrightColor() {
    //         const hue = Math.floor(Math.random() * 360);
    //         const saturation = '100%';
    //         const lightness = `${Math.floor(Math.random() * 21) + 70}%`;
    //         return `hsl(${hue}, ${saturation}, ${lightness})`;
    //     }
    
    //     axios.get(process.env.REACT_APP_BASE_URL +'/api/ingredients')
    //     .then((res)=>{
    //       // console.log('res data : ' ,res.data)
    //       const tmp = []
    //       res.data.ingredients.map((i)=>{
    //         tmp.push({value:i.id, label:i.name})
    //         setColorArray(colorArray => [...colorArray, getRandomBrightColor()])
    //       })
    //       setSuggestions(tmp)
    //     })
    //     .catch((err)=>{
    //       // console.log('error is : ', err)
    //     })
    // },[])
    
    const onAdd = useCallback(
        (newTag) => {
          setSelected([...selected, newTag])
          //
          // onSelectedChange([...selected, newTag]);
        },
        [selected]
    )
    
    const onDelete = useCallback(
        (tagIndex) => {
          // setSelected(selected.filter((_, i) => i !== tagIndex))
          const newSelected = selected.filter((_, i) => i !== tagIndex);
          setSelected(newSelected);
          // onSelectedChange(newSelected); // 삭제된 재료 정보를 상위 컴포넌트로 전달
        },
        [selected]  //, onSelectedChange]
    );
        
    
    // const CustomTag = ({ classNames, tag, ...tagProps }) => {
    //     // console.log(tag.value);
    //     return (
    //       <button type="button" className={classNames.tag} {...tagProps} style={{background : colorArray[tag.value - 1]}}>
    //         <span className={classNames.tagName}>{tag.label}</span>
    //       </button>
    //     )
    // }
    return (
        <>
            <div className="ingredientSelect">
                <ReactTags
                    suggestions={suggestions}
                    placeholderText={selected.length? "" : "성향 선택"}
                    selected={selected}
                    onAdd={onAdd}
                    onDelete={onDelete}
                    noOptionsText="일치하는 재료가 없습니다."
                    allowBackspace={true}
                    // renderTag={CustomTag}
                    // maxSuggestionsLength={3} // 원하는 최대 표시 제안 수로 조절
                />
            </div>

        </>
    );
}
export default RecommendFriendPage;