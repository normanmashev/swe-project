<template>
	<v-row>
		<v-col cols="12" class="pt-0">
			<h5 class="text-h5">Hotels list</h5>
		</v-col>
		<v-col v-for="hotel of hotels" :key="hotel.hotel_id" cols="12" sm="4">
			<HotelCard :data="hotel" />
		</v-col>
	</v-row>
</template>

<script>
import HotelCard from "@/components/HotelCard";
import { mapGetters } from "vuex";

export default {
	name: "HotelGrid",

	components: {
		HotelCard,
	},

	computed: {
		...mapGetters({
			hotels: "hotels/getHotels",
		}),
	},

	beforeCreate() {
		this.$store.dispatch("hotels/loadHotels");
	},

	mounted() {
		console.log(this.hotels);
	},
};
</script>

<style></style>
