const state = {
	hotels: [],
};

const getters = {
	getHotels: state => state.hotels,
};

const mutations = {
	SET_HOTELS: (state, data) => (state.hotels = data),
};

const actions = {
	loadHotels({ commit }) {},
};

export default {
	namespaced: true,
	state,
	getters,
	mutations,
	actions,
};
