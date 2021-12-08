import request from "./request";

export const Login = data =>
	request({
		url: "/login",
		method: "post",
		data,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const Register = data =>
	request({
		url: "/register",
		method: "post",
		data,
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});
