import React from 'react';
import { Canvas } from '@react-three/fiber'
import Model from './Scene.jsx'


import { OrbitControls, Box } from '@react-three/drei';
const controls = {zoomSpeed:'5.0',   // 줌속도
minDistance : '100.0',  // 최소 줌
maxDistance : '1350.0', // 최대 줌
minPolarAngle : '-1', // 카메라 위도 
target : [-10,-10,10],  // 바라볼 좌표
enableRotate  : true , // 회전 막기
}
function MainPage() {
  return (
    <Canvas style={{ width: '100%',
      height: '100vh',
      background: '#0000'}}>
      <ambientLight />
      <pointLight position={[10,0,0]} />
      <Model /> {/* Model 컴포넌트를 사용 */}
      {/* Camera */}
      <OrbitControls {...controls}/>
      {/* <perspectiveCamera
        makeDefault  // 이 옵션을 추가하여 이 카메라를 기본 카메라로 설정
        position={[0, 0, 1000]}  // 원하는 카메라 위치로 변경
        lookAt={[100, 1000, 0]}  // 카메라가 바라볼 지점 설정
        fov={60}
        near={0.1}
        far={1000}
      /> */}
    </Canvas>
  );
}

export default MainPage;
