<template>
	<v-form @submit.prevent="handleSubmit" lazy-validation ref="form">
		<v-card width="500px" max-width="500px">
			<v-card-title class="d-flex flex-column align-start">
				<h6 class="text-h6">Registration for hotel manager</h6>
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

				<v-text-field
					v-model="data.address"
					prepend-inner-icon="mdi-map-marker"
					label="Address"
					outlined
					dense
					:rules="rules.required"
				>
				</v-text-field>
				<v-row class="pb-4">
					<v-col cols="12" class="d-flex flex-column">
						<v-text-field
							v-model="phone"
							class="mb-0"
							prepend-inner-icon="mdi-cellphone"
							label="Mobile phone"
							hint="Enter phone number and press ENTER to add"
							outlined
							dense
							v-mask="'+7-###-###-##-##'"
							placeholder="+7-___-___-__-__"
							:rules="phoneRule"
							@keydown.enter="addPhone"
						>
						</v-text-field>
						<span v-if="!data.phones.length" class="caption">
							No phones added
						</span>
						<ul v-else class="d-flex flex-column">
							<li
								v-for="p of data.phones"
								:key="p"
								class="subtitle-1 d-flex align-items-center"
							>
								<span>{{ p }}</span>
								<v-btn small icon color="red">
									<v-icon small @click="deletePhone">mdi-delete</v-icon>
								</v-btn>
							</li>
						</ul>
					</v-col>
				</v-row>

				<v-text-field
					v-model="data.password"
					prepend-inner-icon="mdi-lock"
					type="password"
					outlined
					dense
					label="Password"
				></v-text-field>
			</v-card-text>

			<v-card-actions class="pa-4 pt-0 d-flex">
				<v-btn depressed class="primary mr-2">Sign up</v-btn>
				<v-btn
					depressed
					text
					color="primary"
					@click="$store.commit('register/SET_TYPE', 0)"
				>
					Back
				</v-btn>
			</v-card-actions>
		</v-card>
	</v-form>
</template>

<script>
import { RULES, ID_TYPES } from "@/utils/helpers";

export default {
	name: "RegisterManager",

	data() {
		return {
			id_types: ID_TYPES,
			rules: RULES,
			phone: "",
			data: {
				username: "",
				name: "",
				surname: "",
				address: "",
				hotel_name: "",
				phones: [],
				password: "",
			},
			phoneRule: [
				v => v.length === 0 || v.length === 16 || "Enter valid phone number",
			],
		};
	},

	methods: {
		handleSubmit() {
			this.$store.dispatch("user/login");
		},

		addPhone() {
			if (this.phone.length < 16) return;

			const exist = this.data.phones.findIndex(e => e === this.phone);

			if (exist !== -1) return;

			this.data.phones.push(this.phone);
			this.phone = "";
		},

		deletePhone(phone) {
			const index = this.data.phones.findIndex(e => e === phone);
			this.data.phones.splice(index, 1);
		},
	},
};
</script>

<style scoped lang="scss">
.v-text-field {
	margin-bottom: 8px;
}
</style>
