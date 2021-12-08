<template>
	<v-row>
		<v-col cols="12" class="pt-0">
			<h5 class="text-h5">Hotels list</h5>
		</v-col>
		<v-col v-for="e in 5" :key="e.id" cols="12" sm="4">
			<HotelCard />
		</v-col>
	</v-row>
</template>

<script>
import HotelCard from "@/components/HotelCard";

export default {
	name: "HotelGrid",

	components: {
		HotelCard,
	},

	computed: {
		data() {
			return [];
		},
	},

	beforeCreate() {},
};
</script>

<style></style>
