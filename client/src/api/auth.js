import request from "./request";

export const Login = () =>
	request({
		url: "/login",
		method: "post",
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});

export const Register = () =>
	request({
		url: "/register",
		method: "post",
	})
		.then(res => res.data)
		.catch(e => {
			throw e;
		});
