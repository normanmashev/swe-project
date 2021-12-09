import request from "./request";

export const AddRoomType = params =>
	request({
		url: "/roomtype/add/",
		method: "post",
		params,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const GetAllRoomTypes = id =>
	request({
		url: `/roomtype/get/all/${id}`,
		method: "get",
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const DeleteRoomType = id =>
	request({
		url: `/roomtype/delete/${id}`,
		method: "post",
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});
export const EditRoomType = params =>
	request({
		url: `/roomtype/edit/${params.room_type_id}`,
		method: "post",
		params,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});
