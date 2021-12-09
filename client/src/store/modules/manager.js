import { GetAllEmployee } from "@/api/employee";
import { SignUpEmployee } from "@/api/auth";
import { EditWorkingHours, EditSalary } from "@/api/workingHours";

const state = {
	employee: [],
};

const getters = {
	getEmployee: state => state.employee,
};

const mutations = {
	SET_EMPLOYEE: (state, data) => (state.employee = data),
};

const actions = {
	async loadEmployee({ rootGetters, commit }) {
		try {
			const { hotel_id } = rootGetters["user/getUser"].hotel;
			let res = await GetAllEmployee({ hotel_id });
			commit("SET_EMPLOYEE", res);
		} catch (error) {
			throw error;
		}
	},

	async addEmployee({ dispatch }, data) {
		try {
			let res = await SignUpEmployee(data.toFormData());
			dispatch("loadEmployee");
		} catch (error) {
			throw error;
		}
	},

	async editSalary({ dispatch }, data) {
		try {
			let res = await EditSalary(data);
			dispatch("loadEmployee");
		} catch (error) {
			throw error;
		}
	},

	async editWorkingHours({ dispatch }, data) {
		try {
			for (const x of data) {
				if (!x.start || !x.end) continue;
				await EditWorkingHours(x);
			}
			dispatch("loadEmployee");
			return { message: "Working hours edited" };
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
