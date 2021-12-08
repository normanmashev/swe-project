const state = {
	type: 0,
};

const getters = {
	getType: state => state.type,
};

const mutations = {
	SET_TYPE: (state, data) => (state.type = data),
};

const actions = {};

export default {
	namespaced: true,
	state,
	getters,
	mutations,
	actions,
};
