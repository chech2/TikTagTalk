import { useEffect } from "react";
import { customAxios } from "../CustomAxios";

function Test() {

    useEffect(() => {
        customAxios.put(process.env.REACT_APP_BASE_URL + "/members/test")
    })
}

export default Test;