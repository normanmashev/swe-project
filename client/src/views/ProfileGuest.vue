<template>
	<section class="profile-guest">
		<h5 class="text-h5">My bookings</h5>
		<v-simple-table class="room-type-table" :loading="loading">
			<template v-slot:default>
				<thead>
					<tr>
						<th class="text-left">#</th>
						<th class="text-left">Check-in date</th>
						<th class="text-left">Check-out date</th>
						<th class="text-left">Bill amount</th>
						<th class="text-left">Room info</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(item, i) of reservations" :key="item.name">
						<td>{{ i + 1 }}</td>
						<td>{{ item.reservation.checkin_date | timestamp }}</td>
						<td>{{ item.reservation.checkout_date | timestamp }}</td>
						<td>{{ item.price }}</td>
						<td>{{ roomInfo(item) }}</td>
					</tr>
				</tbody>
			</template>
		</v-simple-table>
	</section>
</template>

<script>
import { GetUserReservations, GetGuestBilling } from "@/api/reservation";
import { mapGetters } from "vuex";

export default {
	name: "ProfileGuest",

	data() {
		return {
			reservations: [],
			loading: false,
			billing: [],
		};
	},

	computed: {
		...mapGetters({
			user: "user/getUser",
		}),
	},

	methods: {
		async loadData() {
			this.loading = true;
			const guest_id = this.user.guest_id;
			// this.reservations = await GetUserReservations({ guest_id });
			this.reservations = await GetGuestBilling(guest_id);
			this.loading = false;
		},

		roomInfo(item) {
			const room = item.reservation.room;
			return `${room.room_type.name}, floor ${room.floor}, №${room.number}`;
		},
	},

	mounted() {
		this.loadData();
	},
};
</script>

<style></style>
