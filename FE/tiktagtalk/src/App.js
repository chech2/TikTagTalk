// import logo from './logo.svg';
import "./App.css";
// import react from 'react';
import { Routes, Route } from "react-router-dom";
import { useState } from 'react';
// import styled from 'styled-components';
import LoginPage from "./pages/LoginPage";
import SignupPage from "./pages/SignupPage";
import OAuthSignupPage from "./pages/OAuthSignupPage";
import OAuthRedirectPage from "./pages/OAuthRedirectPage";
import LogoutHandler from "./components/form/LogoutHandler";
import MainPage from "./pages/MainPage";
import CoinPurchasePage from "./pages/CoinPurchasePage";
import CommentPage from "./pages/CommentPage";
import NoticePage from "./pages/NoticePage";
import FriendListPage from "./pages/FriendListPage";
import AchievementsPage from "./pages/AchievementsPage";
import ExchangePage from "./pages/ExchangePage";
// import GlobalStyle from './styles/GlobalStyle';
// import  MobilePage  from './pages/MobilePage';
import SkingShopPage from "./pages/SkinShopPage";
import Footer from "./components/ui/Footer";
import TestCom from "./components/TestCom";
import Test from "./pages/Test";
import { useEffect } from "react";
import StartPage from "./pages/StartPage";
import ConsumePatternPage from "./pages/ConsumePatternPage";
import DebtListPage from "./pages/DebtPage";
import DebtDetailPage from "./pages/DebtDetailPage";
import FilteredPurchaseListPage from "./pages/FilteredPurchaseListPage";
import ConsumePlanInsertPage from "./pages/ConsumePlanInsertPage";
import EntirePurchaseListPage from "./pages/EntirePurchaseListPage";
import DebtCreatePage from "./pages/DebtCreatePage";
function App() {
  const currentPath = window.location.pathname;
  const shouldRenderFooter =
    currentPath !== "/" &&
    currentPath !== "/sign-up" &&
    currentPath !== "/login" &&
    !currentPath.startsWith("/oauth2/sign-up/") &&
    !currentPath.startsWith("/oauth/redirect/");

  function setViewportHeight() {
    let vh = window.innerHeight * 0.01;
    document.documentElement.style.setProperty("--vh", `${vh}px`);
  }
  setViewportHeight();

  // 페이지 로드 시와 화면 크기 변경 시에 뷰포트 높이를 설정
  window.addEventListener("resize", setViewportHeight);
  window.addEventListener("orientationchange", setViewportHeight);

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<StartPage />}></Route>
        {/* 회원 */}
        <Route path="/main/:id" element={<MainPage />}></Route>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/sign-up" element={<SignupPage />}></Route>
        <Route path="/oauth2/sign-up/:token" element={<OAuthSignupPage />}></Route>
        <Route path="/oauth/redirect/:token" element={<OAuthRedirectPage />}></Route>
        <Route path="/logout" element={<LogoutHandler />}></Route>
        <Route path="/consumeplaninsert" element={<ConsumePlanInsertPage />}></Route>

        <Route path="/skin" element={<SkingShopPage />}></Route>
        <Route path="/test" element={<TestCom />}></Route>
        <Route path="/coin-purchase" element={<CoinPurchasePage />}></Route>
        <Route path="/comment/:id" element={<CommentPage />}></Route>
        <Route path="/notice" element={<NoticePage />}></Route>
        <Route path="/friend-list" element={<FriendListPage />}></Route>
        <Route path="/achievement" element={<AchievementsPage />}></Route>
        <Route path="/test/1" element={<Test />}></Route>
        {/* <Route path='/:id' element={<Detail />} /> */}
        <Route path="/exchange" element={<ExchangePage />}></Route>
        <Route path="/consume-pattern" element={<ConsumePatternPage />}></Route>
        <Route path="filter-purchase/:tag" element={<FilteredPurchaseListPage />}></Route>
        <Route path="entire-purchase" element={<EntirePurchaseListPage />}></Route>

        <Route path="/debts/list" element={<DebtListPage />}></Route>
        <Route path="/debts/detail/:id/:mode" element={<DebtDetailPage />}></Route>
        <Route path="/debts/create" element={<DebtCreatePage />}></Route>
      </Routes>
      {shouldRenderFooter && <Footer />}
    </div>
  );
}

export default App;

// import { Canvas } from "@react-three/fiber";
// import { Experience } from "./components/Experience";
// import { DataManager } from "./components/DataManager";
// import { UI } from "./components/UI";
// import { ScrollControls } from "@react-three/drei";

// import React, { useEffect } from "react";

// function App() {

//   return (
//     <>
//       <DataManager />
//       <Canvas shadows camera={{ position: [30, 23, 30], fov: 31 }}>
//         <color attach="background" args={["#ececec"]} />
//         <ScrollControls pages={8}>
//           <Experience />
//         </ScrollControls>
//       </Canvas>
//       <UI />
//     </>
//   );
// }

// export default App;
