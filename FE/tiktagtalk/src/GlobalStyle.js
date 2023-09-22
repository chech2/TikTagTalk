// GlobalStyle.js
import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  :root {
    --vh: 100%;
  }

  /* 다른 전역 스타일 정의 */

  /* 다른 스타일 룰 정의 */

  /* 아래의 코드도 추가 */
  html, body {
    height: calc(var(--vh, 1vh) * 100);
    /* 다른 스타일 설정 */
  }
`;

export default GlobalStyle;
