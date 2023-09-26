import { useAtom } from "jotai";
import { itemsAtom, mapAtom } from "./DataManager";
import { useMemo, useRef, useEffect } from "react";
import { useScroll, useGLTF } from "@react-three/drei";
import { useFrame } from "@react-three/fiber";
import { SkeletonUtils } from "three-stdlib";
import { useGrid } from "../hooks/useGrid";

const StoreItem = ({ item, ...props }) => {
    const { name, size } = item;
    const { scene } = useGLTF(`/models/items/${name}.glb`);
    // Skinned meshes cannot be re-used in threejs without cloning them
    const clone = useMemo(() => SkeletonUtils.clone(scene), [scene]);
    const { gridToVector3 } = useGrid();
  
    return (
      <group {...props}>
        <group position={gridToVector3([0, 0], size[0], 0, size[1])}>
          <primitive object={clone} />
        </group>
      </group>
    );
  };

export const Store = ({ onItemSelected }) => {
    const [items] = useAtom(itemsAtom);
    const [map] = useAtom(mapAtom);

    const maxX = useRef(0);

    const storeContainer = useRef();
    const scrollData = useScroll();

    const isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);

    
    // 터치 시작 위치
    let touchStartX = 0;

    const handleTouchStart = (e) => {
        touchStartX = e.touches[0].clientX;
    };

    const handleTouchMove = (e) => {
        const touchMoveX = e.touches[0].clientX;
        const diffX = -(touchStartX - touchMoveX);

        // 스크롤 오프셋 업데이트
        storeContainer.current.position.x += diffX * 0.05;

        // 업데이트된 터치 위치 저장
        touchStartX = touchMoveX;
    };

    const handleTouchEnd = (e) => {
        // 터치 끝났을 때의 로직 (필요하면)
    };

    useEffect(() => {
        if (isMobile) {
            window.addEventListener('touchstart', handleTouchStart);
            window.addEventListener('touchmove', handleTouchMove);
            window.addEventListener('touchend', handleTouchEnd);
        }

        return () => {
            if (isMobile) {
            window.removeEventListener('touchstart', handleTouchStart);
            window.removeEventListener('touchmove', handleTouchMove);
            window.removeEventListener('touchend', handleTouchEnd);
            }
        };
    }, []);

    useFrame(() => {
        if (!isMobile) {
            storeContainer.current.position.x = -scrollData.offset * maxX.current;
        }
        // 모바일일 경우 이미 handleTouchMove에서 처리가 되므로 여기서는 따로 처리하지 않음
    });

    const storeItems = useMemo(() => {
        let x = 0;
        return Object.values(items).map((item, index) => {
            const xPos = x;
            x += item.size[0] / map.gridDivision + 10;
            maxX.current = x;
            return (
                <StoreItem 
                    key={index} 
                    position-x={xPos} 
                    item={item} 
                    onClick={(e) => {
                        e.stopPropagation(); // Prevents the onPlaneClicked from firing just after we pick up an item
                        onItemSelected(item);
                    }}/>
            )
        });
    }, [items]);

    return <group ref={storeContainer}>
        {storeItems}
    </group>
}