import request from "./request";

export const SignIn = data =>
	request({
		url: "/signin",
		method: "post",
		data,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const SignUpGuest = data =>
	request({
		url: "/signup/guest",
		method: "post",
		data,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const SignUpManager = params =>
	request({
		url: "/signup/manager",
		method: "post",
		params,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const SignUpEmployee = data =>
	request({
		url: "/signup/employee",
		method: "post",
		data,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});
