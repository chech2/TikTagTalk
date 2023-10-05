import React, { useEffect, useState } from "react";
import "./DebtPage.css";
import DebtListItem from "../components/DebtlistItem"; // 컴포넌트 import 필요
import { customAxios } from "../CustomAxios";
import { useSelector } from "react-redux";
import Switch from "react-switch"; // 추가
import { useNavigate } from "react-router-dom";
import AppBar from "../components/ui/AppBar";

function DebtListPage() {
  const navigate = useNavigate();
  const [pageInfo, setPageInfo] = useState();
  const [item, setItems] = useState([]);
  const [page, setPage] = useState(1); // 페이지 번호 (기본값: 0)
  const [size, setSize] = useState(4); // 한 페이지에 보여줄 항목 수 (기본값: 10)
  const [mode, setMode] = useState(1); // 채무자(0) or 채권자(1) 모드 상태 변수 추가 (기본값: 채무자(0))
  const [selectedDebtId, setSelectedDebtId] = useState(null); // 선택된 contract의 id를 저장하는 상태 추가

  const user = useSelector((state) => state.user);

  function getModeString(mode) {
    return mode === 1 ? "채무자" : "채권자";
  }

  // 유저의 차용증 내역 불러오기
  useEffect(() => {
    customAxios
      .get(process.env.REACT_APP_BASE_URL + `/debts/list?mode=${mode}&page=${page}&size=${size}`)
      .then((response) => {
        setPageInfo(response.data.pageInfo);
        setItems(response.data.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [page, mode]); // page나 size가 바뀔 때마다 API 요청을 다시 보냅니다

  const handleNextPage = () => {
    // 페이지 번호가 총 페이지 수를 넘지 않도록 체크합니다.
    if (page < pageInfo.totalPages) {
      setPage(page + 1);
    }
  };

  const handlePrevPage = () => {
    if (page > 1) {
      setPage((prevPage) => prevPage - 1);
    }
  };

  const handleIdClick = async (id, mode) => {
    navigate(`/debts/detail/${id}/${mode}`);
  };

  const createDebt = () => {
    navigate(`/debts/create`);
  };

  const handleModeChange = (checked) => {
    setMode(checked ? 0 : 1);
  };

  return (
    <div className="debt-list-font-container ">
      <div>
        <AppBar title={`${user.userId}님의 ${getModeString(mode)}용 차용증 내역`}></AppBar>
      </div>
      <div className="debtor-lender-input-button">
        <label className="debtor-lender-switch-container">
          <div className="debtor-lender-switch-wrapper">
            <Switch onChange={handleModeChange} checked={mode === 0} width={100} />
            <div
              style={{
                position: "absolute",
                top: "50%",
                left: mode === 0 ? "50%" : "50%",
                transform: "translate(-50%, -50%)",
              }}
            >
              {mode === 0 ? "채권자" : "채무자"}
            </div>
          </div>
        </label>
        <div>
          <button className="debt-input-button" onClick={createDebt}>
            차용증 생성
          </button>
        </div>
      </div>
      <div className="pagination-container">
        <div className="pagination-container-space">
          <button onClick={handlePrevPage}>이전</button>
          <span>
            &nbsp;{page} / {(pageInfo && pageInfo.totalPages) || 0}&nbsp;
          </span>{" "}
          {/* 현재 페이지 번호와 총 페이지 수 */}
          <button onClick={handleNextPage}>다음</button>
        </div>
      </div>
      {/* <button onClick={handleModeChange}>모드 변경</button> */}
      <div className="debt-list-item-container">
        {item.map((contract, index) => (
          <div
            className="debt-list-image-container"
            key={index}
            style={{ backgroundImage: "url(/Icon/차용증리스트.png)" }}
            onClick={() => handleIdClick(contract.id, mode)} // 여기에 onClick 이벤트 핸들러 추가
          >
            <div>
              {contract.status === "INAVTIVE" && (
                <img
                  src="/Icon/차용증상환완료.png"
                  alt="Status Icon"
                  style={{
                    width: "200px",
                    height: "100px",
                    position: "absolute",
                    top: "50%",
                    left: "50%",
                    transform: "translate(-50%, -50%)",
                    zIndex: 1,
                  }}
                />
              )}
            </div>

            <DebtListItem key={index} contract={contract} mode={mode} />
          </div>
        ))}
      </div>
    </div>
  );
}
export default DebtListPage;
