<template>
	<section class="home-employee">
		<h5 class="text-h5 mb-4">Hotel reservations</h5>
		<v-simple-table class="room-type-table" :loading="loading">
			<template v-slot:default>
				<thead>
					<tr>
						<th class="text-left">Room type</th>
						<th class="text-left">Username</th>
						<th class="text-left">Check-in date</th>
						<th class="text-left">Check-out date</th>
						<th class="text-left">Actions</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="item of reservations" :key="item.id">
						<td>{{ item.name }}</td>
						<td>{{ item.size }}</td>
						<td>{{ item.checkin_date | timestamp }}</td>
						<td>{{ item.checkout_date | timestamp }}</td>
						<td>
							<v-btn color="primary" small icon @click="showPrice(item)">
								<v-icon>mdi-pencil</v-icon>
							</v-btn>
							<v-btn color="red" small icon @click="showPrice(item)">
								<v-icon>mdi-delete</v-icon>
							</v-btn>
						</td>
					</tr>
				</tbody>
			</template>
		</v-simple-table>
	</section>
</template>

<script>
import { mapGetters } from "vuex";
export default {
	name: "HomeEmployee",

	computed: {
		...mapGetters({
			reservations: "clerk/getReservations",
		}),
	},

	beforeCreate() {
		this.$store.dispatch("clerk/loadReservations");
	},
};
</script>

<style></style>
