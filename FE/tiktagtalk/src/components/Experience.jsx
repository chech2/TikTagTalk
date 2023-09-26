import { Environment, Grid, OrbitControls, useCursor } from "@react-three/drei";

import { useThree } from "@react-three/fiber";
import { useAtom } from "jotai";
import { useState, useEffect, useRef} from "react";
import { useGrid } from "../hooks/useGrid";
import { Item } from "./Item";
import { mapAtom, userAtom } from "./DataManager";
import { buildModeAtom, storeModeAtom, draggedItemAtom, draggedItemRotationAtom, gridUpItemAtom, gridDownItemAtom } from "./UI";
import { Store } from "./Store";
import { customAxios } from "../CustomAxios";
import axios from 'axios';

export const Experience = () => {
  const [buildMode, setBuildMode] = useAtom(buildModeAtom);
  const [storeMode, setStoreMode] = useAtom(storeModeAtom);

  const [map] = useAtom(mapAtom);
  const [items, setItems] = useState(map.items);
  const [onFloor, setOnFloor] = useState(false);
  
  const [draggedItem, setDraggedItem] = useAtom(draggedItemAtom);
  const [draggedItemRotation, setDraggedItemRotation] = useAtom(draggedItemRotationAtom);
  const [dragPosition, setDragPosition] = useState([0, 0]);
  const [canDrop, setCanDrop] = useState(false);

  const [gridUpItem, setGridUpItem] = useAtom(gridUpItemAtom);
  const [gridDownItem, setGridDownItem] = useAtom(gridDownItemAtom);

  useCursor(onFloor);

  // const scene = useThree((state) => state.scene);
  // const [user] = useAtom(userAtom);

  const { vector3ToGrid, gridZPositions } = useGrid();
 
  const onPlaneClicked = (e) => {
    if (!buildMode) {
      return;
    } else {
      if (draggedItem !== null) {
        if (canDrop) {
          setItems((prev) => {
            const newItems = [...prev];
            delete newItems[draggedItem].tmp;
            newItems[draggedItem].gridPosition = vector3ToGrid(e.point);
            newItems[draggedItem].rotation = draggedItemRotation;
            return newItems;
          });
        }
        setDraggedItem(null);
      }
    }
  };

  useEffect(() => {
    if (!draggedItem) {
      return ;
    }

    const item = items[draggedItem];
    const width = draggedItemRotation === 1 || draggedItemRotation === 3 ? item.size[1] : item.size[0];
    const height = draggedItemRotation === 1 || draggedItemRotation === 3 ? item.size[0] : item.size[1];

    let droppable = true;

    // check if item is in bounds
    if (dragPosition) {
      if (
        dragPosition[0] < 0 ||
        dragPosition[0] + width > map.size[0] * map.gridDivision
      ) {
        droppable = false;
      }
      if (
        dragPosition[1] < 0 ||
        dragPosition[1] + height > map.size[1] * map.gridDivision
      ) {
        droppable = false;
      }
    } else {
      // dragPosition이 null인 경우의 처리
      droppable = false;
    }

    // check if item is not colliding with other items
    if (!item.walkable && !item.wall) {
      items.forEach((otherItem, idx) => {
        // ignore self
        if (idx === draggedItem) {
          return;
        }

        // ignore wall & floor
        if (otherItem.walkable || otherItem.wall || otherItem.room) {
          return;
        }

        // check item overlap
        const otherWidth =
          otherItem.rotation === 1 || otherItem.rotation === 3
            ? otherItem.size[1]
            : otherItem.size[0];
        const otherHeight =
          otherItem.rotation === 1 || otherItem.rotation === 3
            ? otherItem.size[0]
            : otherItem.size[1];
        if (dragPosition) {
          if (
            dragPosition[0] < otherItem.gridPosition[0] + otherWidth &&
            dragPosition[0] + width > otherItem.gridPosition[0] &&
            dragPosition[1] < otherItem.gridPosition[1] + otherHeight &&
            dragPosition[1] + height > otherItem.gridPosition[1] &&
            item.gridNumber === otherItem.gridNumber
          ) {
            droppable = false;
          }
        } else {
          droppable = false;
        }
      });
    }

    setCanDrop(droppable);

    // Grid Up
    if (gridUpItem) {
      item.gridNumber += 1;
      if (item.gridNumber === 3) {
        item.gridNumber = 1;
      }
    }

    setGridUpItem(false);

    // Grid Down
    if (gridDownItem) {
      item.gridNumber -= 1;
      if (item.gridNumber === 0) {
        item.gridNumber = 2;
      }
    }

    setGridDownItem(false);

  }, [dragPosition, draggedItem, items, draggedItemRotation, gridUpItem, gridDownItem]);

  const controls = useRef();
  const state = useThree((state) => state);

  useEffect(() => {
    if (buildMode) {
      // 카메라 조작 봉인
      controls.current.enabled = false;

      // 업데이트된 아이템 불러오기
      setItems(map?.items || []);
    } else {
      // 카메라 조작 가능
      controls.current.enabled = true;

      // 아이템 업데이트
      updateItemsOnServer();    
    }
  }, [items, buildMode]);

  const updateItemsOnServer = async () => {
    try {
      const transformedItems = items.map(item => {
        return {
          positionX: item.gridPosition[0],
          positionY: item.gridPosition[1],
          positionZ: item.gridNumber,
          rotation: item.rotation,
          item: {
            name: item.name
          },
        };
      });

      console.log(transformedItems)

      // 서버로 보낼 최종 JSON 객체를 만듭니다.
      const payload = {
        account: {
          id: 1 // 아이디는 실제 로그인한 사용자의 아이디로 대체해야 합니다.
        },
        updateInfo: transformedItems
      };

      console.log(payload);  // 확인용 로그

      // Content-Type 헤더 추가
      const headers = {
        'Content-Type': 'application/json'
      };

      await customAxios.put('http://localhost:8080/api/memberitem', payload, { headers });
      console.log('Items successfully updated.');
    } catch (error) {
      console.error('An error occurred while updating items:', error);
    }
  };

  // 상점에서의 아이템 선택 시 TagRoom에 아이템 배치
  const onItemSelected = (item) => {
    setStoreMode(false);

    setItems((prev) => [
      ...prev,
      {
        ...item,
        gridPosition: [0, 0],
        gridNumber: 1,
        tmp: true,
      },
    ]);
    setDraggedItem(items.length);
    setDraggedItemRotation(0);
  };

  useEffect(() => {
    if (draggedItem === null) {
      setItems((prev) => prev.filter((item) => !item.tmp));
    }
  }, [draggedItem]);

  return (
    <>
      <Environment preset="sunset" />
      <ambientLight intensity={0.3} />
      <directionalLight
        position={[6, 6, 6]}
        castShadow
        intensity={0.35}
        shadow-mapSize={[1024, 1024]}
      >
        <orthographicCamera
          attach={"shadow-camera"}
          args={[-map.size[0], map.size[1], 10, -10]}
          far={map.size[0] + map.size[1]}
        />
      </directionalLight>
      <OrbitControls 
        ref={controls}
        minDistance={10}  
        maxDistance={48}
        minPolarAngle={0}
        maxPolarAngle={Math.PI / 2}
        screenSpacePanning={false}
        enableZoom={!storeMode}
      />

      {storeMode && <Store onItemSelected={onItemSelected} />}

      {/* Items */}
      {!storeMode && (buildMode ? items : map.items).map((item, idx) => (
        <Item
          key={`${item.name}-${idx}`}
          item={item}
          gridNumber={item.gridNumber} 
          onClick={() => {
            if (buildMode) {
              if (!item.room){
                setDraggedItem((prev) => (prev === null ? idx : prev));
                setDraggedItemRotation(item.rotation || 0);
              }
            }
          }}
          isDragging={draggedItem === idx}
          dragPosition={dragPosition}
          dragRotation={draggedItemRotation}
          canDrop={canDrop}
        />
      ))}

      { !storeMode && draggedItem !== null && items[draggedItem] &&
        <mesh
          rotation-x={-Math.PI / 2}
          position-y={gridZPositions[items[draggedItem].gridNumber]}
          position-x={map.size[0] / 2}
          position-z={map.size[1] / 2}

          onClick={onPlaneClicked}
          onPointerEnter={() => setOnFloor(true)}
          onPointerLeave={() => setOnFloor(false)}
          onPointerMove={(e) => {
            if (!buildMode) {
              return;
            }
            const newPosition = vector3ToGrid(e.point);
            if (
              !dragPosition ||
              newPosition[0] !== dragPosition[0] ||
              newPosition[1] !== dragPosition[1]
            ) {
              setDragPosition(newPosition);
            }
          }}

          receiveShadow
        >
          <planeGeometry args={map.size} />
          <meshStandardMaterial transparent={true} opacity={0.1} color="#f0f0f0" />
        </mesh>
      } 

      {/* Grid 1 */}
      {buildMode && !storeMode &&
      <Grid
        name="Grid1"
        position={[0, 0, 0]} // z축 위치가 0m
        infiniteGrid
        fadeDistance={50}
        fadeStrength={5}
      />}

      {/* Grid 2 */}
      {buildMode && !storeMode &&
      <Grid
        name="Grid2"
        position={[0, 0.7, 0]} // z축 위치가 0.7m
        infiniteGrid
        fadeDistance={50}
        fadeStrength={5}
      />}

      {/* Grid 3 */}
      {buildMode && !storeMode &&
      <Grid
        name="Grid3"
        position={[0, 5.1, 0]} // z축 위치가 5.1m
        infiniteGrid
        fadeDistance={50}
        fadeStrength={5}
      />}
    </>
  );
};