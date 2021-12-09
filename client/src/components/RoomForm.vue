<template>
	<v-form ref="form" lazy-validation @submit.prevent="handleSubmit">
		<v-row>
			<v-col cols="12" class="pb-0">
				<h6 class="text-h6">Add new room</h6>
			</v-col>
			<v-col cols="12" sm="4">
				<v-autocomplete
					v-model="data.room_type_id"
					ref="size"
					solo
					dense
					label="Room type"
					validate-on-blur
					:items="roomTypes"
					item-text="name"
					item-value="room_type_id"
					:rules="requiredRule"
				></v-autocomplete>
			</v-col>
			<v-col cols="12" sm="3">
				<v-text-field
					v-model="data.floor"
					ref="floor"
					type="number"
					min="1"
					solo
					dense
					label="Floor"
					:rules="[...requiredRule, ...numberRule]"
				>
				</v-text-field>
			</v-col>
			<v-col cols="12" sm="3">
				<v-text-field
					v-model="data.number"
					ref="floor"
					type="number"
					min="1"
					solo
					dense
					label="Number"
					validate-on-blur
					:rules="[...requiredRule, ...numberRule]"
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
import { RULES } from "@/utils/helpers";

export default {
	name: "RoomForm",

	data() {
		return {
			rules: RULES,
			requiredRule: [],
			numberRule: [],
			data: {
				floor: null,
				room_type_id: null,
				number: null,
			},
		};
	},

	computed: {
		...mapGetters({
			roomTypes: "hotels/getRoomTypes",
		}),
	},

	watch: {
		data: {
			handler(val) {
				this.resetValidation();
			},
			deep: true,
		},
	},

	methods: {
		resetValidation() {
			this.$refs.form.resetValidation();
		},

		handleSubmit() {
			this.$store.dispatch("hotels/addRoom", this.data);
      this.data = {
				floor: null,
				room_type_id: null,
				number: null,
			};
		},
	},

	beforeCreate() {
		this.$store.dispatch("hotels/loadRoomTypes");
	},
};
</script>

<style></style>
