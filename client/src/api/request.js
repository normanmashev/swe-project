import axios from "axios";
import store from "@/store";
import cookies from "@/utils/cookies";

const instance = axios.create({
	baseURL: process.env.VUE_APP_API_URL,
});

instance.interceptors.request.use(
	config => {
		const token = cookies.getToken();
		if (token) {
			config.headers["Authorization"] = token;
		}
		return config;
	},
	error => {
		return Promise.reject(error);
	}
);

instance.interceptors.response.use(
	response => {
		if (response.status !== 200) {
			return Promise.reject(new Error(res.data.error || "Error"));
		}
		return response;
	},
	error => {
		if (error.response.status === 401) {
			store.dispatch("auth/logout");
		}
		return Promise.reject({
			error: error,
			color: "error",
			message: error.response.data.error,
		});
	}
);

export default request;
