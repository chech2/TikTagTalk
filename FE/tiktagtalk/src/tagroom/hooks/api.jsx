// import axios from 'axios';
import { transformToItems, transformToMap} from './transformData';
import { customAxios } from "../../CustomAxios"

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