import { transformToItems, transformToMap} from './transformData';
import { customAxios } from "../../CustomAxios"
import { useState } from 'react';
import { useSelector } from 'react-redux';

export const fetchData = async () => {
  try {
    const response = await customAxios.get(`${process.env.REACT_APP_BASE_URL}/memberitem?memberId=1`);
    const data = response.data;
    
    const items = transformToItems(data);
    const map = transformToMap(data, items);  

    return {items, map};
    } catch (error) {
        console.error('API 호출 중 오류 발생', error);
    return null;
  }
};

// import React, { useState, useEffect } from 'react';
// import { transformToItems, transformToMap } from './transformData';
// import { customAxios } from "../../CustomAxios";

// function FetchData() {
//   const [items, setItems] = useState(null);
//   const [map, setMap] = useState(null);

//   useEffect(() => {
//     const fetchData2 = async () => {
//       try {
//         await customAxios.post(`${process.env.REACT_APP_BASE_URL}/memberitem/initmemberitem`,
//           {
//             tag: {
//               id: 13
//             }
//           },
//         );

//         const response = await customAxios.get(`${process.env.REACT_APP_BASE_URL}/memberitem?memberId=1`);
//         const data = response.data;
        
//         if (!data) {
//           console.error("Data is null or undefined");
//           return;
//         }

//         const newItems = transformToItems(data);
//         const newMap = transformToMap(data, newItems);

//         setItems(newItems);
//         setMap(newMap);

//       } catch (error) {
//         console.error("API 호출 중 오류 발생", error);
//       }
//     };

//     fetchData2();
//   }, []);

//   // items와 map을 사용한 나머지 로직
//   return (
//     <div>
//       {/* 여기에서 items와 map을 활용한 렌더링 로직을 추가 */}
//     </div>
//   );
// }

// export default FetchData;