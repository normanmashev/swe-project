<template>
	<section class="home-employee">
		<h5 class="text-h5 mb-4">Hotel reservations</h5>
		<v-simple-table class="room-type-table">
			<template v-slot:default>
				<thead>
					<tr>
						<th class="text-left">Room info</th>
						<th class="text-left">Username</th>
						<th class="text-left">Mobile phone</th>
						<th class="text-left">Check-in date</th>
						<th class="text-left">Check-out date</th>
						<th class="text-left">Cleaned</th>
						<th class="text-left">Actions</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="item of reservations" :key="item.id">
						<td>{{ roomInfo(item) }}</td>
						<td>{{ item.guest.username }}</td>
						<td>{{ item.guest.mobile_phone }}</td>
						<td>{{ item.checkin_date | timestamp }}</td>
						<td>{{ item.checkout_date | timestamp }}</td>
						<td>{{ item.room.isClean ? "Yes" : "No" }}</td>
						<td>
							<v-btn color="primary" small icon @click="showModal(item)">
								<v-icon>mdi-pencil</v-icon>
							</v-btn>
							<v-btn color="red" small icon @click="deleteReservation(item)">
								<v-icon>mdi-delete</v-icon>
							</v-btn>
						</td>
					</tr>
				</tbody>
			</template>
		</v-simple-table>
		<ClerkEditModal v-if="modal" :item="toShow" @close-modal="modal = false" />
	</section>
</template>

<script>
import ClerkEditModal from "@/components/ClerkEditModal";
import { mapGetters } from "vuex";
export default {
	name: "HomeEmployee",

	components: {
		ClerkEditModal,
	},

	data() {
		return {
			modal: false,
			toShow: null,
		};
	},

	computed: {
		...mapGetters({
			reservations: "clerk/getReservations",
		}),
	},

	methods: {
		showModal(item) {
			this.modal = true;
			this.toShow = item;
		},

		roomInfo(item) {
			const room = item.room;
			return `${room.room_type.name}, floor ${room.floor}, №${room.number}`;
		},
		deleteReservation(item) {
			this.$store.dispatch("clerk/deleteReservation", item.reservation_id);
		},
	},

	beforeCreate() {
		this.$store.dispatch("clerk/loadReservations");
	},
};
</script>

<style></style>
