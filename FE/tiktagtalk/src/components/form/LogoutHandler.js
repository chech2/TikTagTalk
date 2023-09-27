import { useNavigate, useParams } from "react-router-dom";
import { useDispatch } from "react-redux";
import { useEffect } from "react";
import { clearUser } from "../../redux/userSlice";
import { customAxios } from "../../CustomAxios";

const LogoutHandler = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    useEffect(() => {
        customAxios.post(process.env.REACT_APP_BASE_URL + "/logout",
            {
                headers : {
                    "Access-Control-Allow-Origin": process.env.REACT_APP_BASE_URL 
                },
                withCredentials : true
            })
        .then((res) => {
            if(res.status === 200) {
                localStorage.removeItem("accessToken");
                localStorage.removeItem("refreshToken");
                dispatch(clearUser());
                window.location.replace('/login');
                alert("로그아웃 되었습니다.");
            }
        })
        .catch((err) => {
            alert("로그아웃에 실패했습니다.");
        })
    }, [])

    return (
        <></>
    )
}

export default LogoutHandler;