import request from "./request";

export const AddHotel = () =>
  request({
    url: "/hotel/add",
    method: "post",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });

export const GetAllHotels = () =>
  request({
    url: "/hotel/get/all",
    method: "get",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });

export const GetOneHotel = (id) =>
  request({
    url: `/hotel/get/${id}`,
    method: "get",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });

export const DeleteHotel = (id) =>
  request({
    url: `/hotel/delete/${id}`,
    method: "post",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });
export const UpdateHotel = (id) =>
  request({
    url: `/hotel/edit/${id}`,
    method: "post",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });