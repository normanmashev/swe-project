import { GetAllReservations } from "@/api/reservation";

const state = {
	reservations: [],
};

const getters = {
	getReservations: state => state.reservations,
};

const mutations = {
	SET_RESERVATIONS: (state, data) => (state.reservations = data),
};

const actions = {
	async loadReservations({ rootGetters, commit }) {
		try {
			const { hotel_id } = rootGetters["user/getUser"].hotel;
			let res = await GetAllReservations({ hotel_id });
			console.log(res);
			commit("SET_RESERVATIONS", res);
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
