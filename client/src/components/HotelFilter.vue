<template>
	<v-row class="mt-5">
		<v-col cols="12" class="pb-0 d-flex">
			<h6 class="text-h6 grey--text font-weight-light mr-4">Filters</h6>
			<v-btn
				color="primary"
				class="subtitle-2 font-weight-light text-capitalize"
				depressed
				text
			>
				<v-icon small>mdi-filter</v-icon>
				Apply
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
				guestNumber: null,
			},
		};
	},
};
</script>

<style></style>
