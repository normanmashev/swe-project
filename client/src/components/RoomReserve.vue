<template>
	<article class="room-reserve">
		<h4 class="body-1">Room reservation</h4>

		<v-row class="d-flex">
			<v-col cols="12" sm="2">
				<h6 class="text-h6">Room type: {{ item.name }}</h6>
			</v-col>
			<v-col cols="12" sm="3">
				<DatePicker
					:date.sync="data.checkin_date"
					label="Check-in"
					dense
					:past-dates="false"
				/>
			</v-col>
			<v-col cols="12" sm="3">
				<DatePicker
					:date.sync="data.checkout_date"
					label="Check-out"
					dense
					:past-dates="false"
				/>
			</v-col>
			<v-col cols="12" sm="3">
				<v-btn depressed small color="info" class="mb-5" @click="findRooms">
					<v-icon small>mdi-magnify</v-icon>
					Find rooms
				</v-btn>
			</v-col>
		</v-row>

		<h5 v-if="rooms.id">
			<div class="text-h6">Room found</div>
			<b class="body-1">Floor: {{ rooms.floor }}</b>
			<br />
			<b class="body-1">Number: {{ rooms.number }}</b>
			<br />
			<v-btn depressed color="primary" small @click="reserveRoom"
				>Reserve</v-btn
			>
		</h5>
		<span v-else-if="typeof rooms == 'string'" class="text-grey caption">
			No room found
		</span>
	</article>
</template>

<script>
import { FindRooms } from "@/api/hotel";
import { AddReservation } from "@/api/reservation";
import { mapGetters } from "vuex";

export default {
	name: "RoomReserve",

	props: {
		item: {
			required: true,
		},
	},

	data() {
		return {
			rooms: [],
			data: {
				checkin_date: "",
				checkout_date: "",
			},
		};
	},

	computed: {
		...mapGetters({
			user: "user/getUser",
		}),
	},

	methods: {
		async findRooms() {
			this.rooms = await FindRooms({
				room_type_id: this.item.room_type_id,
				...this.data,
			});
		},

		async reserveRoom() {
			const res = await AddReservation({
				username: this.user.username,
				check_in_date: this.data.checkin_date,
				check_out_date: this.data.checkout_date,
				room_id: this.rooms.id,
			});
			this.$root.snackbar.show({ message: "Room has been reserved!" });
		},
	},
};
</script>

<style></style>
