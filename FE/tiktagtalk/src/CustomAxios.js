import axios, { AxiosInstance } from "axios";

// 토큰을 함께 보내는 axios instance
export const  customAxios =  axios.create({ baseURL: `${process.env.REACT_APP_BASE_URL}` });

// Axios 요청을 보낼 때마다 LocalStorage에서 accessToken을 가져와 Authorization 헤더에 추가
// (access token 만료 시, refresh token으로 access token을 다시 요청하고 이를 이전 요청의 header에 반영해주기 위해 구현)
customAxios.interceptors.request.use((config) => {
    const accessToken = localStorage.getItem("accessToken");
    console.log(accessToken);

    config.headers.Authorization = `Bearer ${accessToken}`;
    return config;
});

// Axios 응답을 인터셉트하여 토큰 갱신 및 에러 처리
customAxios.interceptors.response.use(function (response) {
        return response;
    }, async function (error) {
        const accessToken = localStorage.getItem("accessToken");

        const { config, response: { status } } = error;

        if (status === 462) { // access token 만료 시

            const originRequest = config;

            // LocalStorage에서 refreshToken 가져오기
            const refreshToken = localStorage.getItem("refreshToken");

            if (!refreshToken) {
                // refreshToken이 없으면 로그인 페이지로 리다이렉트
                window.location.replace(`${process.env.REACT_APP_FRONT_URL}/login`);
            }

            try {
                // refreshToken을 사용하여 새로운 accessToken 요청
                const refreshResponse = await axios.post(
                    `${process.env.REACT_APP_BASE_URL}/refresh`,
                    {},
                    { headers: { "Authorization-refresh": `Bearer ${refreshToken}` } }
                );

                // 새로운 토큰 저장
                const newAccessToken = refreshResponse.headers['authorization'];
                const newRefreshToken = refreshResponse.headers['authorization-refresh'];
                console.log("newAccessToken=" + newAccessToken);
                console.log("newRefreshToken=" + newRefreshToken);
                localStorage.setItem("accessToken", newAccessToken);
                localStorage.setItem("refreshToken", newRefreshToken);

                // 이전 요청을 재시도
                originRequest.headers.Authorization = `Bearer ${newAccessToken}`;
                return axios(originRequest);
            } catch (refreshError) {
                //window.location.replace(`${process.env.REACT_APP_FRONT_URL}/login`);
            }
        }

        if(status === 453) { // refresh 토큰 만료 시
            window.location.replace(`${process.env.REACT_APP_FRONT_URL}/login`);
        }

        return Promise.reject(error);
    }
);