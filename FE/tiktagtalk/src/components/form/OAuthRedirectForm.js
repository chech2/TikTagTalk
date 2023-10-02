import { useEffect, useState } from 'react';
import { customAxios } from '../../CustomAxios'
import { useNavigate, useParams } from 'react-router-dom'
import { useDispatch} from 'react-redux';
import { loginUser } from '../../redux/userSlice';

import LoadingComponent from "../ui/LoadingComponent";

function OAuthRedirectForm() {

    const navigate = useNavigate();
    const params = useParams();
    const dispatch = useDispatch();

    useEffect(async () => {
        localStorage.setItem("accessToken", params.token);

        await customAxios.get(process.env.REACT_APP_BASE_URL + '/members/oauth/success', {})
        .then((res) => {
            if(res.status === 200) {
                const accessToken = res.headers['authorization'];
                const refreshToken = res.headers['authorization-refresh'];
                
                // LocalStorage에 토큰 저장
                localStorage.setItem("accessToken", accessToken);
                localStorage.setItem("refreshToken", refreshToken);

                const data = res.data;
                dispatch(loginUser(data))
                window.location.replace(`/main/${data.id}`);
                alert("로그인이 완료되었습니다.");
            }
        })
        .catch((res) => {
            window.location.replace('/login');
            alert("로그인에 실패했습니다.");
        })
    }, []);

    return (
        <><LoadingComponent></LoadingComponent></>
    )
}

export default OAuthRedirectForm;