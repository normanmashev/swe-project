import {
	GetAllReservations,
	DeleteReservation,
	EditReservation,
} from "@/api/reservation";

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

	async editReservation({ rootGetters, dispatch }, data) {
		try {
			const { hotel_id } = rootGetters["user/getUser"].hotel;
			let res = await EditReservation(data.reservation_id, {
				...data,
				reservation_id: null,
				hotel_id,
			});
			dispatch("loadReservations");
		} catch (error) {
			throw error;
		}
	},

	async deleteReservation({ rootGetters, dispatch }, reservation_id) {
		try {
			const { hotel_id } = rootGetters["user/getUser"].hotel;
			await DeleteReservation({ reservation_id, hotel_id });
			dispatch("loadReservations");
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
