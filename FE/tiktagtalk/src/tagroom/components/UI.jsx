import { atom, useAtom } from "jotai";
import './UI.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowLeft, faStore, faHouse, faRotate, faArrowUp, faArrowDown, faXmark } from "@fortawesome/free-solid-svg-icons";

export const buildModeAtom = atom(false);
export const storeModeAtom = atom(false);
export const draggedItemAtom = atom(null);
export const draggedItemRotationAtom = atom(0);
export const gridUpItemAtom = atom(false);
export const gridDownItemAtom = atom(false);

export const UI = () => {
  const [buildMode, setBuildMode] = useAtom(buildModeAtom);
  const [storeMode, setStoreMode] = useAtom(storeModeAtom);
  const [draggedItem, setDraggedItem] = useAtom(draggedItemAtom);
  const [draggedItemRotation, setDraggedItemRotation] = useAtom(draggedItemRotationAtom);
  const [gridUpItem, setGridUpItem] = useAtom(gridUpItemAtom);
  const [gridDownItem, setGridDownItem] = useAtom(gridDownItemAtom);

  return (
    <div className="fixed-button-container">
      <div className="button-group">
        {/* BACK */}
        {(buildMode || storeMode) && !draggedItem && (
          <button
            className="button"
            onClick={() => {
              storeMode ? setStoreMode(false) : setBuildMode(false);
            }}
          >
            <FontAwesomeIcon 
              icon={faArrowLeft}
              style={{ fontSize: '18px' }}
            />
          </button>
        )}
        {/* BUILD */}
        {!buildMode && !storeMode && (
          <button
            className="button"
            onClick={() => setBuildMode(true)}
          >
            <FontAwesomeIcon 
              icon={faHouse}
              style={{ fontSize: '18px' }}
            />
          </button>
        )}
        {/* STORE */}
        {buildMode && !storeMode && !draggedItem && (
          <button
            className="button"
            onClick={() => setStoreMode(true)}
          >
            <FontAwesomeIcon 
              icon={faStore}
              style={{ fontSize: '18px' }}
            />
          </button>
        )}
        {/* ROTATE */}
        {buildMode && !storeMode && draggedItem && (
          <button
            className="button"
            onClick={() =>
              setDraggedItemRotation(
                draggedItemRotation === 3 ? 0 : draggedItemRotation + 1
              )
            }
          >
            <FontAwesomeIcon 
              icon={faRotate} 
              style={{ fontSize: '18px' }}
            />
          </button>
        )}
        {/* Z-Grid UP */}
        {buildMode && !storeMode && draggedItem && (
          <button
            className="button"
            onClick={() => setGridUpItem(true)}
          >
            <FontAwesomeIcon 
              icon={faArrowUp}
              style={{ fontSize: '18px' }}
            />
          </button>
        )}
         {/* Z-Grid DOWN */}
         {buildMode && !storeMode && draggedItem && (
          <button
            className="button"
            onClick={() => setGridDownItem(true)}
          >
            <FontAwesomeIcon 
              icon={faArrowDown}
              style={{ fontSize: '18px' }}
            />
          </button>
        )}
        {/* CANCEL */}
        {buildMode && !storeMode && draggedItem && (
          <button
            className="button"
            onClick={() => setDraggedItem(null)}
          >
            <FontAwesomeIcon 
              icon={faXmark}
              style={{ fontSize: '18px' }}
            />
          </button>
        )}
      </div>
    </div>
  );
};
