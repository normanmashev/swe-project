<template>
	<section class="home__guest">
		<HotelFilter />
		<HotelGrid />
	</section>
</template>

<script>
import HotelFilter from "@/components/HotelFilter";
import HotelGrid from "@/components/HotelGrid";

export default {
	name: "HomeGuest",

	components: {
		HotelFilter,
		HotelGrid,
	},
};
</script>

<style></style>
