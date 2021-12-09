import request from "./request";

export const GetAllReservations = params =>
	request({
		url: "/reservation/allReservations",
		method: "get",
		params,
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

export const AddReservation = params =>
	request({
		url: "/reservation/add",
		method: "post",
		params,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const GetUserReservations = params =>
	request({
		url: "/profile/upcomingReservations",
		method: "get",
		params,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const DeleteReservation = id =>
	request({
		url: `/reservation/${id}/delete`,
		method: "post",
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const UpdateReservation = id =>
	request({
		url: `/reservation/${id}/edit`,
		method: "post",
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const GetGuestBilling = id =>
	request({
		url: `/billing/get/all/guest/${id}`,
		method: "GET",
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});
