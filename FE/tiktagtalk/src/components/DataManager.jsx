import { atom, useAtom } from "jotai";
import { useEffect } from "react";
import { fetchData } from "../hooks/api";

export const mapAtom = atom(null);
export const userAtom = atom(null);
export const itemsAtom = atom(null);

export const DataManager = () => {
  const [_map, setMap] = useAtom(mapAtom);
  // const [_user, setUser] = useAtom(userAtom);
  const [_items, setItems] = useAtom(itemsAtom);

  useEffect(() => {
    // 비동기 작업을 처리하기 위한 별도 함수
    const loadData = async () => {
      const data = await fetchData(); // fetchData를 호출하여 데이터를 가져옵니다.
      if (data) {
        setMap(data.map);
        setItems(data.items);
        // setUser(data.user); // user 데이터도 있을 경우 이렇게 설정할 수 있습니다.
      }
    };

    loadData(); // 초기 데이터 로드
  }, []);  // 빈 dependency 배열을 지정하여 컴포넌트가 마운트 될 때만 실행
};
