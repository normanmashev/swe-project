<template>
	<v-row class="mt-5">
		<v-col cols="12" class="pb-0 d-flex align-items-center">
			<h6 class="text-h6 grey--text mr-4">Filters</h6>
			<v-btn color="primary" depressed x-small text>
				<v-icon small>mdi-filter</v-icon>
				Apply filters
			</v-btn>
		</v-col>
		<v-col cols="12" sm="4" md="3">
			<DatePicker :date.sync="data.dateFrom" label="Check-in date" solo dense />
		</v-col>
		<v-col cols="12" sm="4" md="3">
			<DatePicker :date.sync="data.dateTo" label="Check-out date" solo dense />
		</v-col>
		<v-col cols="12" sm="4" md="3">
			<v-text-field
				v-model="data.guestNumber"
				type="number"
				prepend-inner-icon="mdi-account-multiple"
				label="Number of people"
				min="1"
				solo
				dense
			></v-text-field>
		</v-col>
		<v-col cols="12" sm="4" md="3">
			<v-text-field
				v-model="data.location"
				prepend-inner-icon="mdi-map-marker"
				label="Location"
				solo
				dense
			></v-text-field>
		</v-col>
	</v-row>
</template>

<script>
export default {
	name: "HomeFilter",

	data() {
		return {
			data: {
				dateFrom: "",
				dateTo: "",
				location: "",
				guestNumber: 1,
			},
		};
	},
};
</script>

<style></style>
