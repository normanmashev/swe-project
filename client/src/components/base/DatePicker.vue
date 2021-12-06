<template>
	<v-menu
		v-model="show"
		:close-on-content-click="false"
		:nudge-right="40"
		transition="scale-transition"
		offset-y
		min-width="auto"
	>
		<template v-slot:activator="{ on, attrs }">
			<v-text-field
				v-model="date"
				:label="label"
				prepend-inner-icon="mdi-calendar"
				readonly
				v-bind="{ ...attrs, ...$attrs }"
				v-on="on"
			></v-text-field>
		</template>
		<v-date-picker :value="date" @input="handleInput"></v-date-picker>
	</v-menu>
</template>

<script>
export default {
	name: "DatePicker",

	props: {
		date: {
			type: String,
			required: true,
		},
		label: {
			type: String,
			default: "",
		},
	},

	data() {
		return {
			show: false,
		};
	},

	methods: {
		handleInput(e) {
			this.show = false;
			console.log(e);
			this.$emit("update:date", e);
		},
	},

	created() {
		console.log(this.$attrs);
	},
};
</script>

<style></style>
