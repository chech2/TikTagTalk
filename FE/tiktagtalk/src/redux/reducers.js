// reducers.js
import { combineReducers } from 'redux';

import user from './userSlice';
import item from './itemSlice';

const rootReducer = combineReducers({
  user : user.reducer,
  item : item.reducer,

});
  
export default rootReducer;