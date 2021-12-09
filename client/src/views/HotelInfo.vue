<template>
	<section class="hotel-info">
		<h4 class="text-h4 mb-3">Hotel name: {{ hotel.name }}</h4>
		<h5 class="text-h5 mb-3">Address:{{ hotel.address }}</h5>
		<h6 class="body-1">Phone numbers</h6>
		<ol class="mb-3">
			<li v-for="p of hotel.phone" :key="p">
				{{ p }}
			</li>
		</ol>
		<h6 v-if="hotel.roomTypes.length" class="body-1">Room types</h6>
		<ul class="d-flex mb-4">
			<li
				v-for="p of hotel.roomTypes"
				:key="p.name"
				class="d-flex flex-column mr-7"
			>
				<span><b>Type: </b>{{ p.name }}</span>
				<span><b>Size: </b>{{ p.size }}</span>
				<span class="mb-3"><b>Capacity: </b>{{ p.capacity }}</span>
				<span></span>
				<v-btn small depressed color="primary" @click="reserveRoom(p)"
					>Pick</v-btn
				>
			</li>
		</ul>
		<RoomReserve v-if="showReserve" :item="toShow" />
	</section>
</template>

<script>
import RoomReserve from "@/components/RoomReserve";
import { GetOneHotel } from "@/api/hotel";

export default {
	name: "HotelInfo",

	components: { RoomReserve },

	data() {
		return {
			hotel: null,
			hotel_id: this.$route.params.id,
			showReserve: false,
			toShow: null,
		};
	},

	methods: {
		async loadData() {
			try {
				this.hotel = await GetOneHotel(this.hotel_id);
				console.log(this.hotel);
			} catch (error) {
				console.error(error);
				this.$root.snackbar.show({ message: "Not found", color: "error" });
			}
		},

		reserveRoom(item) {
			this.showReserve = true;
			this.toShow = item;
		},
	},

	created() {
		this.loadData();
	},
};
</script>

<style></style>
