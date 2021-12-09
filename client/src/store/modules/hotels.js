import { GetAllHotels, HotelFilter } from "@/api/hotel";
import {
	GetAllRoomTypes,
	AddRoomType,
	DeleteRoomType,
	EditRoomType,
} from "@/api/roomTypes";
import { GetAllRooms, AddRoom, DeleteRoom } from "@/api/rooms";

const state = {
	hotels: [],
	filters: null,
	roomTypes: [],
	rooms: [],
};

const getters = {
	getHotels: state => state.hotels,
	getRoomTypes: state => state.roomTypes,
	getRooms: state => state.rooms,
};

const mutations = {
	SET_HOTELS: (state, data) => (state.hotels = data),
	SET_ROOM_TYPES: (state, data) => (state.roomTypes = data),
	SET_ROOMS: (state, data) => (state.rooms = data),
	SET_FILTERS: (state, data) => (state.filters = data),
};

const actions = {
	async loadHotels({ commit, state }) {
		try {
			let res;
			if (state.filters) {
				res = await HotelFilter(state.filters);
			} else {
				res = await GetAllHotels();
			}

			console.log(res);
			commit("SET_HOTELS", res);
		} catch (error) {
			throw error;
		}
	},

	async loadRoomTypes({ rootGetters, commit }) {
		try {
			const user = rootGetters["user/getUser"];
			const res = await GetAllRoomTypes(user.hotel.hotel_id);
			commit("SET_ROOM_TYPES", res);
		} catch (error) {
			throw error;
		}
	},

	async loadRooms({ rootGetters, commit }) {
		try {
			const user = rootGetters["user/getUser"];
			const res = await GetAllRooms(user.hotel.hotel_id);
      console.log(res);
			commit("SET_ROOMS", res);
		} catch (error) {
			throw error;
		}
	},

	async addRoom({ rootGetters, dispatch }, data) {
		try {
			const user = rootGetters["user/getUser"];
			const res = await AddRoom(data);
			dispatch("loadRooms");
		} catch (error) {
			throw error;
		}
	},

	async deleteRoom({ dispatch }, id) {
		try {
			const res = await DeleteRoom(id);
			dispatch("loadRooms");
		} catch (error) {
			throw error;
		}
	},

	async addRoomType({ rootGetters, dispatch }, data) {
		try {
			const user = rootGetters["user/getUser"];
			const res = await AddRoomType({ ...data, hotel_id: user.hotel.hotel_id });
			dispatch("loadRoomTypes");
		} catch (error) {
			throw error;
		}
	},

	async editRoomType({ dispatch }, data) {
		try {
			const res = await EditRoomType(data);
			dispatch("loadRoomTypes");
		} catch (error) {
			throw error;
		}
	},

	async deleteRoomType({ dispatch }, id) {
		try {
			const res = await DeleteRoomType(id);
			dispatch("loadRoomTypes");
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
