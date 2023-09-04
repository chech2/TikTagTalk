import React from 'react';
import { Canvas,useThree } from '@react-three/fiber'
import Model from './Scene.jsx'


import { OrbitControls, Box } from '@react-three/drei';
const controls = {zoomSpeed:5.0,   // 줌속도
minDistance : 100.,  // 최소 줌
maxDistance : 1350, // 최대 줌
minPolarAngle : 1, // 카메라 위도 
target : [-5,-5,-2],  // 바라볼 좌표
enableRotate  : true , // 회전 막기
}

function CameraPosition() {
  const { camera } = useThree();
  camera.position.set(350, 350, 350); // 카메라의 위치를 설정합니다.
  
  return null; // 이 컴포넌트는 렌더링할 내용이 없으므로 null을 반환합니다.
}

function MainPage() {
  return (
    <div style={{width:'412px'}}>

    
    <Canvas style={{ width: '100%',
      height: '100vh',
      background: '#0000'}}>

      <ambientLight />
      {/* <pointLight position={[10,0,0]} /> */}
      <Model scale={[0.45,0.45,0.45]}/> {/* Model 컴포넌트를 사용 */}
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
      <CameraPosition />
    </Canvas>
    </div>
  );
}

export default MainPage;
