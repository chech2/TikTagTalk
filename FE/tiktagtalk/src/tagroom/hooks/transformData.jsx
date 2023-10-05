export const transformToItems = (data) => {
  const items = {};
  data.forEach(entry => {
    const { item, inRoom } = entry;
    items[item.name] = {
      name: item.name,
      size: [item.sizeX, item.sizeY],
      room: item.room,
      wall: item.wall,
      inRoom: inRoom,
    };
  });
  return items;
};

export const transformToMap = (data, items) => {
  const map = {
    size: [7, 7],
    gridDivision: 2,
    items: data.map(entry => {
      const { position_x, position_y, grid_z_number, item, rotation, inRoom } = entry;

      if (inRoom) {
        return {
          ...items[item.name],
          gridPosition: [position_x, position_y],
          gridNumber: grid_z_number,
          rotation: rotation,
          inRoom: inRoom,
        };
      }
    }).filter(Boolean),
  };
  return map;
};