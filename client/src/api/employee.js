import request from "./request";

export const GetAllEmployee = params =>
	request({
		url: "/employee/get/all",
		method: "get",
		params,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});
