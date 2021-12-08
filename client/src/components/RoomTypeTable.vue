<template>
	<v-simple-table class="room-type-table" :loading="loading">
		<template v-slot:default>
			<thead>
				<tr>
					<th class="text-left">Room type</th>
					<th class="text-left">Capacity</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="item in desserts" :key="item.name">
					<td>{{ item.name }}</td>
					<td>{{ item.calories }}</td>
				</tr>
			</tbody>
		</template>
	</v-simple-table>
</template>

<script>
import { mapGetters } from "vuex";

export default {
	name: "RoomTypeTable",

	data() {
		return {
			loading: false,
		};
	},

	computed: {
		...mapGetters({
			roomTypes: "hotels/getRoomTypes",
		}),
	},

	async created() {
		this.loading = true;
		// await this.$store.dispatch("hotels/loadRoomTypes");
		setTimeout(() => {
			this.loading = false;
		}, 1000);
	},
};
</script>

<style></style>
