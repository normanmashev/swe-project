<template>
	<v-form ref="form" lazy-validation @submit.prevent="handleSubmit">
		<v-row>
			<v-col cols="12" class="pb-0">
				<h6 class="text-h6">Add new room type</h6>
			</v-col>
			<v-col cols="12" sm="4">
				<v-autocomplete
					v-model="data.name"
					solo
					dense
					label="Room type name"
					:items="ROOMTYPES"
					validate-on-blur
					:rules="rules.required"
				></v-autocomplete>
			</v-col>
			<v-col cols="12" sm="3">
				<v-text-field
					v-model="data.size"
					type="number"
					min="1"
					solo
					dense
					label="Size in square feet"
					validate-on-blur
					:rules="[v => (!!v && v > 0) || 'Minimum 1 square feet']"
				>
				</v-text-field>
			</v-col>
			<v-col cols="12" sm="3">
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
import { RULES, ROOMTYPES } from "@/utils/helpers";

export default {
	name: "RoomTypeForm",

	data() {
		return {
			ROOMTYPES,
			rules: RULES,
			data: {
				name: "",
				size: "",
				capacity: "",
			},
		};
	},

	watch: {
		data: {
			handler() {},
			deep: true,
		},
	},

	methods: {
		async handleSubmit() {
			this.$store.dispatch("hotels/addRoomType", this.data);
			this.data = {
				name: "",
				size: "",
				capacity: "",
			};
		},
	},
};
</script>

<style></style>
