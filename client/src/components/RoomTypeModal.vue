<template>
	<v-dialog v-model="show" persistent max-width="500">
		<v-card>
			<v-card-title class="text-h5 d-flex justify-space-between">
				<h6 class="text-h6">{{ item.name }} price type info</h6>
				<v-btn icon @click="$emit('close-modal')">
					<v-icon>mdi-close</v-icon>
				</v-btn>
			</v-card-title>
			<v-card-text>
				<v-text-field v-model="size" dense label="Size"></v-text-field>
				<v-text-field v-model="capacity" dense label="Capacity"></v-text-field>
				<ul class="d-flex flex-column">
					<li v-for="(d, i) of days" :key="d" class="text-h4 d-flex">
						<h6 class="mr-3">{{ d }}:</h6>
						<v-text-field
							v-model="prices[i]"
							type="number"
							min="1"
							width="100px"
							dense
							solo
						></v-text-field>
					</li>
				</ul>
			</v-card-text>
			<v-card-actions>
				<v-spacer></v-spacer>
				<v-btn color="green darken-1" text @click="handleSubmit"> Save </v-btn>
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>

<script>
import { DAYS } from "@/utils/helpers";

export default {
	name: "RoomTypeModal",

	props: {
		item: {
			type: Object,
		},
	},

	data() {
		return {
			show: true,
			size: null,
			capacity: null,
			prices: [],
			days: DAYS,
		};
	},

	methods: {
		async handleSubmit() {
			const prices = this.prices.map(e => e.toString()).join(", ");
			const data = {
				room_type_id: this.item.room_type_id,
				size: this.size,
				capacity: this.capacity,
				prices,
			};
			await this.$store.dispatch("hotels/editRoomType", data);
			this.$emit("close-modal");
		},
	},

	created() {
		this.size = this.item.size;
		this.capacity = this.item.capacity;
		this.prices = [...this.item.prices];
	},
};
</script>

<style></style>
