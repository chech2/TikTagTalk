import { useEffect } from "react";
import { customAxios } from "../CustomAxios";

function Test() {


    useEffect(() => {
        customAxios.get(process.env.REACT_APP_BASE_URL + "/talk-talks")
    })
}

export default Test;