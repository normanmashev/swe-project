import request from "./request";


export const GetAllReservations = () =>
  request({
    url: /reservation/allReservations,
    method: "get",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });
    
export const GetReservations = () =>
  request({
    url: "/reservation/allReservations/",
    method: "get",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });
export const AddReservation = () =>
  request({
    url: "/reservation/create",
    method: "post",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });

export const DeleteReservation = (id) =>
  request({
    url: `/reservation/${id}/delete`,
    method: "post",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });
export const UpdateReservation = (id) =>
  request({
    url: `/reservation/${id}/edit`,
    method: "post",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });