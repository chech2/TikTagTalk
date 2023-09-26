import axios from 'axios';
import { transformToItems, transformToMap} from './transformData'

export const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/memberitem?accountId=1');
    const data = response.data;
    
    const items = transformToItems(data);
    const map = transformToMap(data, items);  

    return {items, map};
    } catch (error) {
        console.error('API 호출 중 오류 발생', error);
    return null;
  }
};
