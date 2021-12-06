const state = {
	user: null,
};

const getters = {
	isAuth: state => !!state.user,
	getUser: state => state.user,
};

const mutations = {
	SET_USER: (state, data) => (state.user = data),
};

const actions = {
	login({ commit }, data) {
		try {
		} catch (error) {
			throw error;
		}
	},

	register({}, data) {},
};

export default {
	namespaced: true,
	state,
	getters,
	mutations,
	actions,
};
