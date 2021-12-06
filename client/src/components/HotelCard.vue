<template>
	<v-card>
		<template slot="progress">
			<v-progress-linear
				color="primary"
				height="10"
				indeterminate
			></v-progress-linear>
		</template>

		<v-img
			height="250"
			src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
		></v-img>

		<v-card-title>Hotel title</v-card-title>

		<v-card-text>
			<v-row align="center" class="mx-0 mb-4">
				<v-rating
					:value="4.5"
					color="amber"
					dense
					half-increments
					readonly
					size="14"
				></v-rating>

				<div class="grey--text ms-4">4.5 (413)</div>
			</v-row>

			<div class="mb-1 text-subtitle-1 d-flex align-items-center">
				<v-icon class="mr-1" small>mdi-map-marker</v-icon>
				<span>Country name</span>
			</div>
			<div class="mb-1 text-subtitle-2 d-flex align-items-center">
				<v-icon class="mr-1" small>mdi-cellphone</v-icon>
				<span>+7-700-000-11-22</span>
			</div>

			<div>
				Small plates, salads & sandwiches - an intimate setting with 12 indoor
				seats plus patio seating.
			</div>
		</v-card-text>

		<v-divider class="mx-4"></v-divider>

		<v-card-title>Tonight's availability</v-card-title>

		<v-card-text>
			<v-chip-group active-class="deep-purple accent-4 white--text" column>
				<v-chip>5:30PM</v-chip>

				<v-chip>7:30PM</v-chip>

				<v-chip>8:00PM</v-chip>

				<v-chip>9:00PM</v-chip>
			</v-chip-group>
		</v-card-text>

		<v-card-actions>
			<v-btn color="deep-purple lighten-2" text> Reserve </v-btn>
		</v-card-actions>
	</v-card>
</template>

<script>
export default {
	name: "HotelCard",

	props: {
		data: {},
	},
};
</script>

<style></style>
