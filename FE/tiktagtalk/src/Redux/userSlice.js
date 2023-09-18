// todoSlice.js 파일

import { createSlice } from '@reduxjs/toolkit';
// cannot redeclare block-scope variable 'todoSlice'
const user = createSlice({
  name: 'user', // 슬라이스의 이름
  initialState: {
    id:'',
  },

  reducers: {
    loginUser: (state, action) => {
      // 새로운 todo를 추가하는 리듀서
      state.id = action.payload;
    },

  },
});

export const { loginUser } = user.actions;
export default user;