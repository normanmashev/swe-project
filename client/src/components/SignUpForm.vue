<template>
	<v-form @submit.prevent="handleSubmit" lazy-validation>
		<v-card class="center">
			<v-card-title class="d-flex flex-column align-start">
				<h5 class="text-h5 text-center">Registration Form</h5>
				<v-divider width="100%" class="my-3"></v-divider>
			</v-card-title>
			<v-card-text class="d-flex flex-column">
                <v-text-field
					v-model="data.name"
					outlined
					dense
					label="Name"
				></v-text-field>
                <v-text-field
					v-model="data.surname"
					outlined
					dense
					label="Surname"
				></v-text-field>
				<v-text-field
					v-model="data.email"
					outlined
					dense
					label="E-mail"
					:rules="rules.email"
				></v-text-field>
                <v-select
                    :items="data.items"
                    label="Position"
                    solo
                ></v-select>
				<v-text-field
					v-model="data.password"
					type="password"
					outlined
					dense
					label="Password"
					:rules="[rules.required]"
				></v-text-field>
			</v-card-text>

			<v-card-actions class="pa-4 pt-0 d-flex justify-space-between">
				<v-btn depressed color="primary">Register</v-btn>
			</v-card-actions>
		</v-card>
	</v-form>
</template>

<script>
import { rules } from "@/utils/helpers";

export default {
	name: "SignUpForm",

	data() {
		return {
			rules:{
				required:v => v.length >= 4 || 'Min 4 characters',
			},
			data: {
                name:"", 
                surname:"",
				email: "",
                items: ['Manager', 'Guest', 'Employer'],
				password: "",
			},
            
		};
	},

  methods: {

    handleSubmit() {
     // this.$store.dispatch('user/login');
    }
  }
};
</script>

<style scoped lang="scss">
.col {
	padding: 0px;
}
.center{
    width: 500px;
    max-width: 500px;
    margin: 0 auto;
    align-content: center;
}
</style>
