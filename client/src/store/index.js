import Vue from "vue";
import Vuex from "vuex";

import createPersistedState from "vuex-persistedstate";
import * as Cookies from "js-cookie";

Vue.use(Vuex);

// * Auto import store modules
const context = require.context("./modules");
const modules = {};

context.keys().forEach(file => {
	const moduleName = file.replace(/(\.\/|\/*\.js$)/g, "");
	modules[moduleName] = context(file).default || context(file);
	modules[moduleName].namespaced = true;
});

export default new Vuex.Store({
	modules,
	plugins: [
		createPersistedState({
			paths: ["user"],
			storage: {
				getItem: key => Cookies.get(key),
				setItem: (key, value) =>
					Cookies.set(key, value, { expires: 3, secure: true }),
				removeItem: key => Cookies.remove(key),
			},
		}),
	],
});
