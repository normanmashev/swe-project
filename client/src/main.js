import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";

import "./utils/filters";
import "./assets/scss/styles.scss";

import "@/utils/objectProperties";

import { VueMaskDirective } from "v-mask";

Vue.directive("mask", VueMaskDirective);

Vue.config.productionTip = false;

const requireComponent = require.context(
	// The relative path of the components folder
	"./components/base",
	// Whether or not to look in subfolders
	false,
	// The regular expression used to match base component filenames
	/[A-Z]\w+\.(vue|js)$/
);

requireComponent.keys().forEach(fileName => {
	// Get component config
	const componentConfig = requireComponent(fileName);

	// Get PascalCase name of component
	const componentName = fileName.replace(/^\.\/(.*)\.\w+$/, "$1");

	// Register component globally
	Vue.component(
		componentName,
		// Look for the component options on `.default`, which will
		// exist if the component was exported with `export default`,
		// otherwise fall back to module's root.
		componentConfig.default || componentConfig
	);
});

new Vue({
	router,
	store,
	vuetify,
	render: h => h(App),
}).$mount("#app");
