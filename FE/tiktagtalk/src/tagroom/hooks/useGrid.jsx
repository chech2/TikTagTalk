import { useAtom } from "jotai";
import { mapAtom } from "../components/DataManager";

import * as THREE from "three";

export const useGrid = () => {
  const [map] = useAtom(mapAtom);

  const vector3ToGrid = (vector3) => {
    return [
      Math.floor(vector3.x * map.gridDivision),
      Math.floor(vector3.z * map.gridDivision),
      Math.floor(vector3.y * map.gridDivision),
    ];
  };

  const gridToVector3 = (gridPosition, width = 1, z = 0, height = 1) => {
    return new THREE.Vector3(
      width / map.gridDivision / 2 + gridPosition[0] / map.gridDivision,
      z,
      height / map.gridDivision / 2 + gridPosition[1] / map.gridDivision
    );
  };

  const gridZPositions = {
    0: 0,
    1: 0.7,
    2: 5.1,
    // 더 많은 그리드들...
  };

  return {
    vector3ToGrid,
    gridToVector3,
    gridZPositions,
  };
};
