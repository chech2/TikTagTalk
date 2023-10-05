export const transformToItems = (data) => {
  const items = {};
  data.forEach(entry => {
    const { wall, item, isRoom } = entry;
    items[item.name] = {
      name: item.name,
      size: [item.sizeX, item.sizeY],
      room: item.room,
      wall: wall,
      // isRoom: isRoom,
    };
  });
  return items;
};

export const transformToMap = (data, items) => {
  const map = {
    size: [7, 7],
    gridDivision: 2,
    items: data.map(entry => {
      const { position_x, position_y, grid_z_number, item, rotation, isRoom } = entry;

      if (isRoom) {
        return {
          ...items[item.name],
          gridPosition: [position_x, position_y],
          gridNumber: grid_z_number,
          rotation: rotation,
          isRoom: isRoom,
        };
      }
    }).filter(Boolean),
  };

  return map;
};