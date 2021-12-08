<template>
	<section class="register">
		<RegisterType v-if="!type" />
		<RegisterGuest v-else-if="type === 1" />
		<RegisterManager v-else />
	</section>
</template>

<script>
import RegisterType from "@/components/RegisterType";
import RegisterGuest from "@/components/RegisterGuest";
import RegisterManager from "@/components/RegisterManager";

import { mapGetters } from "vuex";

export default {
	name: "Register",

	components: {
		RegisterManager,
		RegisterType,
		RegisterGuest,
	},

	computed: {
		...mapGetters({
			type: "register/getType",
		}),
	},

	beforeCreate() {
		this.$store.commit("register/SET_TYPE", 0);
	},
};
</script>

<style scoped lang="scss">
.register {
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
