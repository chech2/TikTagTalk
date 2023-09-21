// import logo from './logo.svg';
import './App.css';
// import react from 'react';
import { Routes, Route } from "react-router-dom";
// import styled from 'styled-components';
import LoginPage from './pages/LoginPage';
import SignupPage from './pages/SignupPage';
import MainPage from './pages/MainPage';
import CoinPurchasePage from './pages/CoinPurchasePage';
import CommentPage from './pages/CommentPage';
import NoticePage from './pages/NoticePage';
import FriendListPage from './pages/FriendListPage';
import AchievementsPage from './pages/AchievementsPage';
// import GlobalStyle from './styles/GlobalStyle';
// import  MobilePage  from './pages/MobilePage';
import SkingShopPage from './pages/SkinShopPage';
import Footer from './components/ui/Footer';
import TestCom from './components/TestCom';
import Test from './pages/Test';
import { useEffect } from 'react';

function App() {

  function setScreenSize() {
    let vh = window.innerHeight * 0.01;
    document.documentElement.style.setProperty("--vh", `${vh}px`);
  }
  useEffect(() => {
    setScreenSize();
  });

  return (    
    <div className="App">
      <Routes>
        <Route path='/' element={<MainPage/>}></Route>
        {/* 회원 */}
        <Route path='/login' element={<LoginPage/>} />
        <Route path='/sign-up' element={<SignupPage/>}></Route>
        <Route path='/skin' element={<SkingShopPage/>}></Route>
        <Route path='/test' element={<TestCom/>}></Route>
        <Route path='/coin-purchase' element={<CoinPurchasePage/>}></Route>
        <Route path='/comment' element={<CommentPage/>}></Route>
        <Route path='/notice' element={<NoticePage/>}></Route>
        <Route path='/friend-list' element={<FriendListPage/>}></Route>
        <Route path='/achievement' element={<AchievementsPage/>}></Route>
        <Route path='/test1' element={<Test/>}></Route>
        {/* <Route path='/:id' element={<Detail />} /> */}
      </Routes>
      {<Footer/>}
    </div>
  );
}

export default App;
