<template>
	<div>
		<v-data-table
			:headers="headers"
			:items="employee"
			:items-per-page="5"
			class="elevation-1"
		>
			<template v-slot:top>
				<div class="d-flex align-items-center">
					<h1 class="pa-4 mr-3">Employee schedule</h1>
				</div>
			</template>

			<template v-slot:[`item.working_hours`]="{ item }">
				<td>
					<v-btn color="primary" depressed small @click="showModal(item)">
						Show working hours
					</v-btn>
				</td>
			</template>
		</v-data-table>

		<WorkingHoursModal
			v-if="modal"
			:item="toShow"
			@close-modal="modal = false"
		/>
	</div>
</template>

<script>
import WorkingHoursModal from "@/components/WorkingHoursModal";
import { mapGetters } from "vuex";

export default {
	name: "EmployeeTable",

	components: {
		WorkingHoursModal,
	},

	data() {
		return {
			modal: false,
			toShow: null,
			headers: [
				{ text: "Username", value: "username" },
				{ text: "Name", value: "name" },
				{ text: "Surname", value: "surname" },
				{ text: "Role", value: "role" },
				{ text: "Salary", value: "salary" },
				{ text: "Hotel address", value: "hotel.address" },
				{ text: "Hotel name", value: "hotel.name" },
				{ text: "Working hours", value: "working_hours" },
			],
		};
	},

	computed: {
		...mapGetters({
			employee: "manager/getEmployee",
		}),
	},

	methods: {
		showModal(item) {
			this.toShow = item;
			this.modal = true;
		},
	},

	beforeCreate() {
		this.$store.dispatch("manager/loadEmployee");
	},

	mounted() {
		console.log(this.employee);
	},
};
</script>

<style></style>
