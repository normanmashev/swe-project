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
		<v-date-picker
			:value="date"
			@input="handleInput"
			:min="minDate"
		></v-date-picker>
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
		pastDates: {
			type: Boolean,
			default: true,
		},
	},

	data() {
		return {
			show: false,
		};
	},

	computed: {
		minDate() {
			if (this.pastDates) return "1970-01-01";
			return new Date().toISOString().substr(0, 10);
		},
	},

	methods: {
		handleInput(e) {
			this.show = false;
			this.$emit("update:date", e);
		},
	},
};
</script>

<style></style>
