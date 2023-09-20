import { Server } from "socket.io";

const io = new Server({
  cors: {
    origin: "http://localhost:5173",
  },
});

io.listen(3001);

const items = {
  room : {
    name: "room",
    size: [14, 14],
    room: true,
  },
  bed : {
    name: "bed",
    size: [9, 7],
  },
  chair : {
    name: "chair",
    size: [3, 4],
  },
  closet : {
    name: "closet",
    size: [4, 3],
  },
  shelf : {
    name: "shelf",
    size: [5, 3],
    wall: true
  },
  table: {
    name: "table",
    size: [5, 3],
  },
  cute_coffee_cup: {
    name: "cute_coffee_cup",
    size: [2, 2],
  },
  red_gear_pc_gaming_controller : {
    name: "red_gear_pc_gaming_controller",
    size: [5, 5],
  },
  vintage_controller : {
    name: "vintage_controller",
    size: [5, 5],
  },
  xbox_controller_black : {
    name: "xbox_controller_black",
    size: [5, 5],
  },
  xbox_controller_white : {
    name: "xbox_controller_white",
    size: [5, 5],
  },
};

const map = {
  size: [7, 7],
  gridDivision: 2,
  items: [
    {
      ...items.room,
      gridPosition: [0, 0],
      gridNumber: 0,
    },
    {
      ...items.bed,
      gridPosition: [0, 7],
      gridNumber: 1,
    },
    {
      ...items.chair,
      gridPosition: [9, 4],
      gridNumber: 1,
    },
    {
      ...items.closet,
      gridPosition: [2, 1],
      gridNumber: 1,
    },
    {
      ...items.shelf,
      gridPosition: [5, 0],
      gridNumber: 1,
    },
    {
      ...items.table,
      gridPosition: [8, 1],
      gridNumber: 1,
    },
    {
      ...items.cute_coffee_cup,
      gridPosition: [5, 0],
      gridNumber: 2,
    },
  ],
};

// const generateRandomPosition = () => {
//   return [Math.random() * map.size[0], 0, Math.random() * map.size[1]];
// };

io.on("connection", (socket) => {
  console.log("user connected");

  socket.emit("hello", {
    map,
    id: socket.id,
    items,
  });

  socket.on("itemsUpdate", (items) => {
    map.items = items;
    io.emit("mapUpdate", {
      map,
    });
  });

  socket.on("disconnect", () => {
    console.log("user disconnected");
  });
});
