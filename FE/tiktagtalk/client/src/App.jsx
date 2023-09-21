import { Canvas } from "@react-three/fiber";
import { Experience } from "./components/Experience";
import { SocketManager } from "./components/SocketManager";
import { UI } from "./components/UI";
import { ScrollControls } from "@react-three/drei";

import React, { useEffect } from "react";

function App() {
  return (
    <>
      <SocketManager />
      <Canvas shadows camera={{ position: [30, 23, 30], fov: 31 }}>
        <color attach="background" args={["#ececec"]} />
        <ScrollControls pages={8}>
          <Experience />
        </ScrollControls>
      </Canvas>
      <UI />
    </>
  );
}

export default App;
