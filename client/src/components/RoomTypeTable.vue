<template>
	<div>
		<v-simple-table class="room-type-table" :loading="loading">
			<template v-slot:default>
				<thead>
					<tr>
						<th class="text-left">Room type</th>
						<th class="text-left">Size</th>
						<th class="text-left">Capacity</th>
						<th class="text-left">Prices</th>
						<th class="text-left">Delete</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="item of roomTypes" :key="item.name">
						<td>{{ item.name }}</td>
						<td>{{ item.size }}</td>
						<td>{{ item.capacity }}</td>
						<td>
							<v-btn color="primary" small @click="showPrice(item)">
								See and edit prices
							</v-btn>
						</td>
						<td>
							<v-btn color="red" icon @click="handleDelete(item)">
								<v-icon>mdi-delete</v-icon>
							</v-btn>
						</td>
					</tr>
				</tbody>
			</template>
		</v-simple-table>
		<RoomTypeModal
			v-if="showModal"
			:item="toShow"
			@close-modal="showModal = false"
		/>
	</div>
</template>

<script>
import { mapGetters } from "vuex";
import RoomTypeModal from "@/components/RoomTypeModal";

export default {
	name: "RoomTypeTable",

	components: {
		RoomTypeModal,
	},

	data() {
		return {
			showModal: false,
			toShow: null,
			loading: false,
		};
	},

	computed: {
		...mapGetters({
			roomTypes: "hotels/getRoomTypes",
		}),
	},

	methods: {
		async loadData() {
			try {
				this.loading = true;
				await this.$store.dispatch("hotels/loadRoomTypes");
			} catch (error) {
				console.error(error);
			} finally {
				this.loading = false;
			}
		},

		handleDelete(item) {
			this.$store.dispatch("hotels/deleteRoomType", item.room_type_id);
		},

		showPrice(item) {
			console.log(item);
			this.showModal = true;
			this.toShow = item;
		},
	},

	created() {
		this.loadData();
	},
};
</script>

<style></style>
