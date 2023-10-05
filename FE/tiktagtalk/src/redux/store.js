/* eslint-disable */
import {configureStore } from "@reduxjs/toolkit";
import { persistReducer } from "redux-persist";
import sessionStorage from "redux-persist/es/storage/session";
import rootReducer from "./reducers";
import thunk from "redux-thunk";
import { getDefaultNormalizer } from "@testing-library/react";
import userReducer from './userSlice';

const persistConfg ={
  key : 'root',
  storage : sessionStorage,
  whitelist: ['user','todos','item',],
}

const persistedReducer = persistReducer(persistConfg, rootReducer);

const store = configureStore({   
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(thunk),
})

export default store;