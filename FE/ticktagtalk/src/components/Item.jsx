import { useCursor, useGLTF } from "@react-three/drei";
import { useAtom } from "jotai";
import { useEffect, useMemo, useState } from "react";
import { SkeletonUtils } from "three-stdlib";
import { mapAtom } from "./DataManager";
import { useGrid } from "../hooks/useGrid";
import { buildModeAtom } from "./UI";


export const Item = ({ item, onClick, isDragging, dragPosition, canDrop, dragRotation }) => {
  const { name, gridPosition, size, rotation: itemRotation} = item;
  const rotation = isDragging ? dragRotation : itemRotation;
  const { gridToVector3, gridZPositions } = useGrid();
  const [map] = useAtom(mapAtom);
  const { scene } = useGLTF(`/models/items/${name}.glb`);
  // Skinned meshes cannot be re-used in threejs without cloning them
  const clone = useMemo(() => SkeletonUtils.clone(scene), [scene]);

  const width = rotation === 1 || rotation === 3 ? size[1] : size[0];
  const height = rotation === 1 || rotation === 3 ? size[0] : size[1];
  
  // 공중에 떠 있는 물체를 선택하기 위한 hover
  const [hover, setHover] = useState(false);
  const [buildMode] = useAtom(buildModeAtom);
  useCursor(buildMode ? hover : undefined);

  // 개별 아이템들에 그림자 효과 부여
  useEffect(() => {
    clone.traverse((child) => {
      child.castShadow = true;
      child.receiveShadow = true;
    })
  }, []);

  const gridNumber = item.gridNumber;

  // gridZPositions에서 해당 그리드의 z-축 위치를 찾음
  const z = gridZPositions[gridNumber] || 0; // 만약 gridName이 없거나 잘못됐다면 기본값으로 0을 사용

  return (
    <group
      onClick={onClick}
      position={gridToVector3(
        isDragging ? dragPosition || gridPosition : gridPosition,
        width,
        z,
        height
      )}
      onPointerEnter={() => setHover(true)}
      onPointerLeave={() => setHover(false)}
    >
      <primitive object={clone} rotation-y={((rotation || 0) * Math.PI) / 2} />
      {isDragging && (
        <mesh>
          <boxGeometry
            args={[width / map.gridDivision, 0.2, height / map.gridDivision]}
          />
          <meshBasicMaterial
            color={canDrop ? "green" : "red"}
            opacity={0.3}
            transparent
          />
        </mesh>
      )}
    </group>
    
  );
};
