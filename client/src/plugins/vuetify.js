import Vue from "vue";
import Vuetify from "vuetify/lib/framework";

Vue.use(Vuetify);

import colors from "vuetify/lib/util/colors";

const vuetify = new Vuetify({
	theme: {
		themes: {
			light: {
				primary: colors.blue.darken4,
			},
			dark: {
				primary: colors.blue.lighten3,
			},
		},
	},
});

export default vuetify;
