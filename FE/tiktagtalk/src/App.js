// import logo from './logo.svg';
import './App.css';
// import react from 'react';
import {Routes, Route} from "react-router-dom";
// import styled from 'styled-components';
import LoginPage from './pages/LoginPage';
import MainPage from './pages/MainPage'
// import GlobalStyle from './styles/GlobalStyle';
// import  MobilePage  from './pages/MobilePage';
import SkingShopPage from './pages/SkinShopPage'
import TestCom from './components/TestCom'



function App() {
  return (
    <div className="App">

      <Routes>
        <Route path='/' element={<MainPage/>}></Route>
        <Route path='/login' element={<LoginPage/>} />
        <Route path='skin' element={<SkingShopPage/>}></Route>
        <Route path='test' element={<TestCom></TestCom>}></Route>
        {/* <Route path='/:id' element={<Detail />} /> */}
      </Routes>
    </div>
  );
}

export default App;
