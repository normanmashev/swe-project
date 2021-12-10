<template>
	<v-dialog v-model="show" persistent max-width="500">
		<v-card>
			<v-card-title class="text-h5 d-flex justify-space-between">
				<h6 class="text-h6">Editing reservation</h6>
				<v-btn icon @click="$emit('close-modal')">
					<v-icon>mdi-close</v-icon>
				</v-btn>
			</v-card-title>
			<v-card-text>
				<DatePicker :date.sync="data.check_in_date" label="Check-in date" />
				<DatePicker :date.sync="data.check_out_date" label="Check-out date" />
			</v-card-text>
			<v-card-actions>
				<v-spacer></v-spacer>
				<v-btn color="green darken-1" text @click="handleSubmit"> Save </v-btn>
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>

<script>
export default {
	name: "ClerkEditModal",

	props: {
		item: {
			type: Object,
		},
	},

	data() {
		return {
			show: true,
			data: null,
		};
	},

	methods: {
		async handleSubmit() {
			await this.$store.dispatch("clerk/editReservation", this.data);
			this.$emit("close-modal");
		},

		convertTs(x) {
			const date = new Date(x);
			const year = date.getFullYear();
			let month = date.getMonth() + 1;
			if (month < 10) month = "0" + month;
			let day = date.getDate();
			if (day < 10) day = "0" + day;
			return `${year}-${month}-${day}`;
		},
	},

	created() {
		this.data = {
			reservation_id: this.item.reservation_id,
			check_in_date: this.item.checkin_date,
			check_out_date: this.item.checkout_date,
			room_type_id: this.item.room.room_type.room_type_id,
		};
		this.data.check_in_date = this.convertTs(this.data.check_in_date);
		this.data.check_out_date = this.convertTs(this.data.check_out_date);
	},
};
</script>

<style></style>
