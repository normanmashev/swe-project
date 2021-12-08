import request from "./request";


export const GetSchedules = () =>
  request({
    url: /workinghours/schedules,
    method: "get",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });
    
export const GetSchedule = () =>
  request({
    url: "/workinghours/schedule",
    method: "get",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });