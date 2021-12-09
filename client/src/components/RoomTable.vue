<template>
	<v-simple-table class="room-type-table" :loading="loading">
		<template v-slot:default>
			<thead>
				<tr>
					<th class="text-left">Room type</th>
					<th class="text-left">Floor</th>
					<th class="text-left">Number</th>
					<th class="text-left">Cleaned</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="item of rooms" :key="item.name">
					<td>{{ item.roomType }}</td>
					<td>{{ item.floor }}</td>
					<td>{{ item.number }}</td>
					<td>{{ item.isClean ? "Yes" : "No" }}</td>
				</tr>
			</tbody>
		</template>
	</v-simple-table>
</template>

<script>
import { mapGetters } from "vuex";
export default {
	name: "RoomTable",

	data() {
		return {
			loading: false,
		};
	},

	computed: {
		...mapGetters({
			rooms: "hotels/getRooms",
		}),
	},

	methods: {
		async loadData() {
			try {
				this.loading = true;
				await this.$store.dispatch("hotels/loadRooms");
			} catch (error) {
				console.error(error);
			} finally {
				this.loading = false;
			}
		},

		handleDelete(item) {
			this.$store.dispatch("hotels/deleteRoom", item.room_type_id);
		},
	},

	created() {
		this.loadData();
	},
};
</script>

<style></style>
