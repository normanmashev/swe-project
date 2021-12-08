const state = {
	hotels: [],
	filters: null,
	roomTypes: [],
};

const getters = {
	getHotels: state => state.hotels,
	getRoomTypes: state => state.roomTypes,
};

const mutations = {
	SET_HOTELS: (state, data) => (state.hotels = data),
	SET_ROOM_TYPES: (state, data) => (state.roomTypes = data),
	SET_FILTERS: (state, data) => (state.filters = data),
};

const actions = {
	loadHotels({ commit }) {
		try {
			let res;
			commit("SET_ROOM_TYPES", res);
		} catch (error) {
			throw error;
		}
	},

	async loadRoomTypes({ commit }) {
		try {
			let res;
			commit("SET_ROOM_TYPES", res);
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
