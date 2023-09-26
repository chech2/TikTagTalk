import { useEffect, useState } from 'react';
import { customAxios } from '../../CustomAxios'
import { useNavigate, useParams } from 'react-router-dom'
import { useCookies } from 'react-cookie';
import { useDispatch} from 'react-redux';
import { loginUser } from '../../redux/userSlice';

function OAuthRedirectForm() {

    const navigate = useNavigate();
    const params = useParams();
    const dispatch = useDispatch();

    const [cookie, setCookie] = useCookies(['accessToken', 'refreshToken']);

    useEffect(async () => {
        setCookie('accessToken', params.token, { path: '/' });

        await customAxios.get(process.env.REACT_APP_BASE_URL + '/members/oauth/success', {})
        .then((res) => {
            if(res.status === 200) {
                const accessToken = res.headers['authorization'];
                const refreshToken = res.headers['authorization-refresh'];
                
                // 쿠키에 토큰 저장
                setCookie('accessToken', accessToken, { path: '/' });
                setCookie('refreshToken', refreshToken, { path: '/' });

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
        <></>
    )
}

export default OAuthRedirectForm;