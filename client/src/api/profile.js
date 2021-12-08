import request from "./request";

export const GetBillingHistory = () =>
  request({
    url: "/billingHistory",
    method: "get",
  })
    .then(res => res.data)
    .catch(e => {
      throw e;
    });

export const GetUpcomingReservations = () =>
    request({
      url: "/upcomingReservations",
      method: "get",
    })
      .then(res => res.data)
      .catch(e => {
        throw e;
      });