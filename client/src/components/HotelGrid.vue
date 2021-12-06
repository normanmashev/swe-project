<template>
	<v-row>
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
