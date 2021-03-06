<template>
	<v-form @submit.prevent="handleSubmit" lazy-validation>
		<v-card width="500px" max-width="500px">
			<v-card-title class="d-flex flex-column align-start">
				<h6 class="text-h6">Registration for guest</h6>
				<v-divider width="100px" class="my-3"></v-divider>
				<p class="grey--text subtitle-1 ma-0">
					<router-link class="text-decoration-none" to="/login">
						Sign in
					</router-link>
					<span>if you already have an account</span>
				</p>
			</v-card-title>
			<v-card-text class="d-flex flex-column">
				<v-row>
					<v-col cols="12" sm="6">
						<v-text-field
							v-model="data.name"
							outlined
							dense
							label="Name"
							:rules="rules.required"
						></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field
							v-model="data.surname"
							outlined
							dense
							label="Surname"
							:rules="rules.required"
						></v-text-field>
					</v-col>
				</v-row>

				<v-text-field
					v-model="data.username"
					prepend-inner-icon="mdi-account"
					outlined
					dense
					label="Username"
					:rules="rules.required"
				></v-text-field>
				<v-row>
					<v-col cols="12" sm="6">
						<v-select
							v-model="data.identification_type"
							prepend-inner-icon="mdi-passport"
							outlined
							dense
							label="Identification type"
							:items="id_types"
							:rules="rules.required"
						>
						</v-select>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field
							v-model="data.number"
							prepend-inner-icon="mdi-alpha-n"
							:label="IDLabel"
							:disabled="!data.identification_type"
							outlined
							dense
							:rules="rules.required"
						>
						</v-text-field>
					</v-col>
				</v-row>

				<v-autocomplete
					v-model="data.address"
					prepend-inner-icon="mdi-map-marker"
					label="Address"
					outlined
					dense
					:items="cities"
					:rules="rules.required"
				>
				</v-autocomplete>
				<v-row>
					<v-col cols="12" sm="6">
						<v-text-field
							v-model="data.mobile_phone"
							prepend-inner-icon="mdi-cellphone"
							label="Mobile phone"
							outlined
							dense
							v-mask="'+7-###-###-##-##'"
							placeholder="+7-___-___-__-__"
							validate-on-blur
							:rules="rules.phone"
						>
						</v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field
							v-model="data.home_phone"
							prepend-inner-icon="mdi-phone"
							label="Home phone"
							v-mask="'+7-###-###-##-##'"
							placeholder="+7-___-___-__-__"
							outlined
							dense
							validate-on-blur
							:rules="rules.phone"
						>
						</v-text-field>
					</v-col>
				</v-row>
				<v-text-field
					v-model="data.password"
					prepend-inner-icon="mdi-lock"
					type="password"
					outlined
					dense
					label="Password"
					:rules="rules.required"
				></v-text-field>

				<v-alert v-if="alert" dense :type="alert.type">
					{{ alert.message }}
				</v-alert>
			</v-card-text>

			<v-card-actions class="pa-4 pt-0 d-flex">
				<v-btn depressed class="primary mr-2" @click="handleSubmit"
					>Sign up</v-btn
				>
				<v-btn
					depressed
					text
					color="primary"
					@click="$store.commit('register/SET_TYPE', 0)"
					>Back</v-btn
				>
			</v-card-actions>
		</v-card>
	</v-form>
</template>

<script>
import { RULES, ID_TYPES } from "@/utils/helpers";
import { mapState } from "vuex";

export default {
	name: "RegisterGuest",

	data() {
		return {
			alert: null,
			id_types: ID_TYPES,
			rules: RULES,
			data: {
				username: "",
				identification_type: null,
				number: "",
				name: "",
				surname: "",
				address: "",
				home_phone: "",
				mobile_phone: "",
				password: "",
			},
		};
	},

	computed: {
		IDLabel() {
			if (!this.data.identification_type) return "Choose ID type";
			const label =
				this.data.identification_type === "PASSPORT"
					? "Passport"
					: "Driver license";
			return label + " ID";
		},
		...mapState(["cities"]),
	},

	methods: {
		async handleSubmit() {
			this.alert = null;
			try {
				await this.$store.dispatch("user/signUp", {
					data: this.data,
					role: "G",
				});
				this.alert = {
					message:
						"You are successfully registered! You will be redirected to the login page in a moment..",
					type: "success",
				};
				setTimeout(() => {
					this.$router.push("/login");
				}, 2000);
			} catch (error) {
				this.alert = {
					message: "Unexpected error",
					type: "error",
				};
			}
		},
	},
};
</script>

<style scoped lang="scss">
.v-text-field {
	margin-bottom: 8px;
}
</style>
