export const transformToItems = (data) => {
  const items = {};
  data.forEach(entry => {
    const {size_x, size_y, room, wall, item } = entry;
    items[item.name] = {
      name: item.name,
      size: [size_x, size_y],
      room,
      wall,
    };
  });
  return items;
};

export const transformToMap = (data, items) => {
  const map = {
    size: [7, 7],
    gridDivision: 2,
    items: data.map(entry => {
      const { position_x, position_y, grid_z_number, item, rotation } = entry;
      return {
        ...items[item.name],
        gridPosition: [position_x, position_y],
        gridNumber: grid_z_number,
        rotation: rotation,
      };
    }),
  };
  return map;
};