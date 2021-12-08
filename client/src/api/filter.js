import request from "./request";

export const Filter = () =>
  request({
    url: "/filter/",
    method: "post",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
  });