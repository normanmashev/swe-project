import request from "./request";

export const AddRoom = () =>
  request({
    url: "/roomtype/add/",
    method: "post",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });

export const GetAllRooms = (id) =>
  request({
    url: `/roomtype/get/all/${id}`,
    method: "get",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });


export const DeleteRoom = (id) =>
  request({
    url: `/roomtype/delete/${id}`,
    method: "post",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });
export const UpdateRoom = (id) =>
  request({
    url: `/roomtype/edit/${id}`,
    method: "post",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });