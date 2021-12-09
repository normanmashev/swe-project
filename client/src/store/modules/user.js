import { SignIn, SignUpManager, SignUpGuest } from "@/api/auth";
import cookies from "@/utils/helpers";

const state = {
	user: null,
};

const getters = {
	isAuth: state => !!state.user,
	getUser: state => state.user,
	getRole: state => state.user?.role || null,
};

const mutations = {
	SET_USER: (state, data) => (state.user = data),
};

const actions = {
	async login({ commit }, data) {
		try {
			const fd = data.toFormData();
			const res = await SignIn(fd);
			if (!res.role) res.role = "guest";
			commit("SET_USER", res);
			return res;
		} catch (error) {
			throw error;
		}
	},

	logout({ commit }) {
		commit("SET_USER", null);
	},

	async signUp({}, { data, role }) {
		try {
			console.log(data);
			let res;
			if (role === "M") {
				res = await SignUpManager(data);
			} else {
				const formData = data.toFormData();
				res = await SignUpGuest(formData);
			}
			console.log(res);
			return res;
		} catch (error) {
			throw error;
		}
	},
};

export default {
	namespaced: true,
	state,
	getters,
	mutations,
	actions,
};
