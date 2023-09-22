import { createSlice } from '@reduxjs/toolkit';

// USER 관련
const user = createSlice({
  name: 'user', // 슬라이스의 이름
  initialState: {
    id:'', // long
    userId: '', // string
    avatarType: '',
    point: '',
    coin: '',
    isLogin: false
  },

  reducers: {
    loginUser: (state, action) => {
      state.id = action.payload.id;
      state.userId = action.payload.userId;
      state.avatarType = action.payload.avatarType;
      state.point = action.payload.point;
      state.coin = action.payload.coin;
      state.isLogin = true;
    },
    clearUser: (state) => {
      state.id = '';
      state.userId = '';
      state.avatarType = '';
      state.point = '';
      state.coin = '';
      state.isLogin = false;
    }
  },
});

export const { loginUser, clearUSer } = user.actions;
export default user;