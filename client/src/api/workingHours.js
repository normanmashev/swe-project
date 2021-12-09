import request from "./request";

export const GetSchedule = () =>
	request({
		url: "/workinghours/schedule",
		method: "get",
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const EditWorkingHours = params =>
	request({
		url: "/workinghours/edit",
		method: "post",
		params,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const EditSalary = params =>
	request({
		url: "/employee/edit/salary",
		method: "post",
		params,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});
