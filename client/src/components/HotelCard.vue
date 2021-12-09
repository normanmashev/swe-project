<template>
	<v-card>
		<template slot="progress">
			<v-progress-linear
				color="primary"
				height="10"
				indeterminate
			></v-progress-linear>
		</template>

		<v-card-title>{{ data.name }}</v-card-title>

		<v-card-text>
			<!-- <v-row align="center" class="mx-0 mb-4">
				<v-rating
					:value="4.5"
					color="amber"
					dense
					half-increments
					readonly
					size="14"
				></v-rating>

				<div class="grey--text ms-4">{{ "" }}</div>
			</v-row> -->

			<div class="mb-1 text-subtitle-1 d-flex align-items-center">
				<v-icon class="mr-1" small>mdi-map-marker</v-icon>
				<span>{{ data.address }}</span>
			</div>
			<div
				v-for="p of data.phone"
				:key="p"
				class="mb-1 text-subtitle-2 d-flex align-items-center"
			>
				<v-icon class="mr-1" small>mdi-cellphone</v-icon>
				<span>{{ p }}</span>
			</div>

			<ul v-if="data.roomTypes.length" class="d-flex flex-column">
				<h6 class="subtitle-1">Room types:</h6>
				<li v-for="(rt, i) of data.roomTypes" :key="i">
					<b>{{ rt.name }}</b>
				</li>
			</ul>
		</v-card-text>

		<v-card-actions class="d-flex justify-end">
			<v-btn
				v-if="role"
				color="deep-purple lighten-2"
				text
				link
				:to="`/hotel/${data.hotel_id}`"
			>
				Reserve
			</v-btn>
			<span v-else class="text-grey caption">
				Authorize into the system in order to book a room in this hotel
			</span>
		</v-card-actions>
	</v-card>
</template>

<script>
import { mapGetters } from "vuex";
export default {
	name: "HotelCard",

	props: {
		data: {
			type: Object,
			required: true,
		},
	},

	computed: {
		...mapGetters({
			role: "user/getRole",
		}),
	},
};
</script>

<style></style>
