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
					</tr>
				</thead>
				<tbody>
					<tr v-for="(item, i) of reservations" :key="item.name">
						<td>{{ i + 1 }}</td>
						<td>{{ item.checkin_date | timestamp }}</td>
						<td>{{ item.checkout_date | timestamp }}</td>
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
			this.reservations = await GetUserReservations({ guest_id });
			this.billing = await GetGuestBilling(guest_id);
			console.log(this.billing);
			this.loading = false;
		},
	},

	mounted() {
		this.loadData();
	},
};
</script>

<style></style>
