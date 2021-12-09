<template>
	<v-form @submit.prevent="handleSubmit" lazy-validation ref="form">
		<v-card width="500px" max-width="500px">
			<v-card-title class="d-flex flex-column align-start">
				<h5 class="text-h5">Authorization</h5>
				<v-divider width="100px" class="my-3"></v-divider>
				<p class="grey--text subtitle-1 ma-0">
					<router-link class="text-decoration-none" to="/register">
						Sign up
					</router-link>
					<span>if you don't have an account yet</span>
				</p>
			</v-card-title>
			<v-card-text class="d-flex flex-column">
				<v-text-field
					v-model="data.username"
					outlined
					dense
					label="Username"
					:rules="rules.required"
				></v-text-field>
				<v-text-field
					v-model="data.password"
					type="password"
					outlined
					dense
					label="Password"
					:rules="rules.required"
				></v-text-field>
				<v-alert v-if="alert" dense :type="alert.type">{{
					alert.message
				}}</v-alert>
			</v-card-text>

			<v-card-actions class="pa-4 pt-0 d-flex justify-space-between">
				<v-btn depressed color="primary" @click="handleSubmit">Login</v-btn>
			</v-card-actions>
		</v-card>
	</v-form>
</template>

<script>
import { RULES } from "@/utils/helpers";

export default {
	name: "LoginForm",

	data() {
		return {
			rules: RULES,
			alert: null,
			data: {
				username: "",
				password: "",
			},
		};
	},

	methods: {
		async handleSubmit() {
			this.alert = null;
			try {
				await this.$store.dispatch("user/login", this.data);
				this.alert = {
					type: "success",
					message:
						"Authorization successful! You will be redirected to the home page now..",
				};
				setTimeout(() => this.$router.push("/"), 1500);
			} catch (error) {
				this.alert = {
					type: "error",
					message: error.message || "Error",
				};
			}
		},
	},
};
</script>

<style scoped lang="scss">
.col {
	padding: 0px;
}
</style>
