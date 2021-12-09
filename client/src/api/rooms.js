import request from "./request";

export const AddRoom = params =>
	request({
		url: "/room/add",
		method: "post",
		params,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const GetAllRooms = id =>
	request({
		url: `/room/get/byhotel/${id}`,
		method: "get",
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const DeleteRoom = id =>
	request({
		url: `/room/delete/${id}`,
		method: "post",
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const UpdateRoom = id =>
	request({
		url: `/room/edit/${id}`,
		method: "post",
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});
