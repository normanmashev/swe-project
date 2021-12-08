import request from "./request";

export const SignIn = () =>
  request({
    url: "/signin",
    method: "post",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
    });

export const SignUpGuest = () =>
    request({
      url: "/signup/guest",
      method: "post",
    })
      .then(res => res.data)
      .catch(e => {
        throw e;
      });

export const SignUpManager = () =>
  request({
    url: "/signup/manager",
    method: "post",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
    });
export const SignUpEmployee = () =>
  request({
    url: "/signup/employee",
    method: "post",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
    });