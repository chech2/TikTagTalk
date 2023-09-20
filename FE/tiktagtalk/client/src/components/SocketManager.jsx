import { atom, useAtom } from "jotai";
import { useEffect } from "react";
import { io } from "socket.io-client";

export const socket = io("http://localhost:3001");
export const mapAtom = atom(null);
export const userAtom = atom(null);
export const itemsAtom = atom(null);

export const SocketManager = () => {
  const [_map, setMap] = useAtom(mapAtom);
  const [_user, setUser] = useAtom(userAtom);
  const [_items, setItems] = useAtom(itemsAtom);

  useEffect(() => {
    function onConnect() {
      console.log("connected");
    }
    function onDisconnect() {
      console.log("disconnected");
    }

    function onHello(value) {
      setMap(value.map);
      setUser(value.id);
      setItems(value.items);
    }

    function onMapUpdate(value) {
      setMap(value.map);
    }

    socket.on("connect", onConnect);
    socket.on("disconnect", onDisconnect);
    socket.on("hello", onHello);
    socket.on("mapUpdate", onMapUpdate);

    return () => {
      socket.off("connect", onConnect);
      socket.off("disconnect", onDisconnect);
      socket.off("hello", onHello);
      socket.off("mapUpdate", onMapUpdate);
    };
  }, []);
};
