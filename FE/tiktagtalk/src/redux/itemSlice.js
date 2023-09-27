
import { createSlice } from '@reduxjs/toolkit';
// cannot redeclare block-scope variable 'todoSlice'
const item = createSlice({
  name: 'item', // 슬라이스의 이름
  initialState: {
    id:-1,
    img:'',
    name:'',
    price:0,
  },

  reducers: {
    itemNumber: (state, action) => {
      // 새로운 todo를 추가하는 리듀서
      state.id = action.payload.item_key;
      state.img = action.payload.item_url;
      state.name = action.payload.item_name;
      state.price = action.payload.item_price;

      console.log('리덕스 아이템 정보:', action.payload)
    },

  },
});

export const { itemNumber } = item.actions;
export default item;