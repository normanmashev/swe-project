<template>
	<v-form ref="form" lazy-validation @submit.prevent="handleSubmit">
		<v-row>
			<v-col cols="12" class="pb-0">
				<h6 class="text-h6">Add new room type</h6>
			</v-col>
			<v-col cols="12" sm="4">
				<v-autocomplete
					v-model="data.size"
					solo
					dense
					label="Room type name"
					validate-on-blur
					:rules="rules.required"
				></v-autocomplete>
			</v-col>
			<v-col cols="12" sm="4">
				<v-text-field
					v-model="data.capacity"
					type="number"
					min="1"
					solo
					dense
					label="Capacity"
					validate-on-blur
					:rules="rules.capacity"
				>
				</v-text-field>
			</v-col>
			<v-col cols="12" sm="2">
				<v-btn depressed color="primary" @click="handleSubmit">
					<v-icon small>mdi-plus</v-icon>
					Add
				</v-btn>
			</v-col>
		</v-row>
	</v-form>
</template>

<script>
import { mapGetters } from "vuex";
export default {
	name: "RoomForm",

	data() {
		return {
			data: {},
		};
	},

	computed: {
		...mapGetters({
			roomTypes: "hotels/getRoomTypes",
		}),
	},

	methods: {
		handleSubmit() {},
	},

	beforeCreate() {
		this.$store.dispatch("hotels/loadRoomTypes");
	},
};
</script>

<style></style>
