<template>
	<v-dialog v-model="show" persistent max-width="500">
		<v-card>
			<v-card-title class="text-h5 d-flex justify-space-between">
				<h6 class="text-h6">Add new employee</h6>
				<v-btn icon @click="$emit('close-modal')">
					<v-icon>mdi-close</v-icon>
				</v-btn>
			</v-card-title>
			<v-card-text>
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

				<v-autocomplete
					v-model="data.role"
					outlined
					dense
					label="Role"
					:items="ROLES"
					:rules="rules.required"
				></v-autocomplete>

				<v-text-field
					v-model="data.username"
					prepend-inner-icon="mdi-account"
					outlined
					dense
					label="Username"
					:rules="rules.required"
				></v-text-field>

				<v-text-field
					v-model="data.password"
					prepend-inner-icon="mdi-lock"
					type="password"
					outlined
					dense
					label="Password"
				></v-text-field>

				<v-alert v-if="alert" dense :type="alert.type">{{
					alert.message
				}}</v-alert>
			</v-card-text>
			<v-card-actions>
				<v-spacer></v-spacer>
				<v-btn color="green darken-1" text @click="handleSubmit"> Add </v-btn>
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>

<script>
import { mapGetters } from "vuex";
import { RULES, ROLES } from "@/utils/helpers";

export default {
	name: "EmployeeModal",

	data() {
		return {
			show: true,
			ROLES,
			rules: RULES,
			alert: null,
			data: {
				username: "",
				name: "",
				surname: "",
				password: "",
				role: "",
			},
		};
	},

	computed: {
		...mapGetters({
			user: "user/getUser",
		}),
	},

	methods: {
		async handleSubmit() {
			try {
				const { hotel_id } = this.user.hotel;

				const res = await this.$store.dispatch("manager/addEmployee", {
					...this.data,
					hotel_id,
				});

				this.alert = {
					type: "success",
					message: "Employee added",
				};

				setTimeout(() => this.$emit("close-modal"), 1000);
			} catch (error) {
				console.error(error);
			}
		},
	},
};
</script>

<style></style>
