<template>
	<v-dialog v-model="show" persistent max-width="500">
		<v-card>
			<v-card-title class="text-h5 d-flex justify-space-between">
				<h6 class="text-h6">{{ item | fullname }} working hours</h6>
				<v-btn icon @click="$emit('close-modal')">
					<v-icon>mdi-close</v-icon>
				</v-btn>
			</v-card-title>
			<v-card-text>
				<v-text-field
					v-model="salary"
					class="mb-3"
					type="number"
					min="0"
					outlined
					dense
					label="Salary"
				></v-text-field>

				<ul class="d-flex flex-column">
					<li v-for="(d, i) of days" :key="d" class="text-h4 d-flex">
						<h6 class="mr-3">{{ d }}:</h6>
						<div class="d-flex">
							<v-text-field
								v-model="workingHours[i].start_time"
								class="mr-2"
								v-mask="'##:##:##'"
								outlined
								dense
								label="Start time"
							></v-text-field>
							<v-text-field
								v-model="workingHours[i].end_time"
								v-mask="'##:##:##'"
								outlined
								dense
								label="End time"
							></v-text-field>
						</div>
					</li>
				</ul>
			</v-card-text>
			<v-card-actions>
				<v-spacer></v-spacer>
				<v-btn color="green darken-1" text @click="handleSubmit">
					Save changes
				</v-btn>
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>

<script>
import { mapGetters } from "vuex";
import { DAYS } from "@/utils/helpers";

export default {
	name: "WorkingHoursModal",

	props: {
		item: {
			type: Object,
			required: true,
		},
	},

	data() {
		return {
			show: true,
			days: DAYS,
			salary: null,
			workingHours: [],
		};
	},

	methods: {
		async handleSubmit() {
			try {
				const workingHours = this.workingHours.map(e => {
					const x = {
						employee_id: this.item.employee_id,
						start: e.start_time,
						end: e.end_time,
						dayOfWeek: e.dayOfWeek,
					};
					return x;
				});

				console.log(workingHours);

				const res = await this.$store.dispatch(
					"manager/editWorkingHours",
					workingHours
				);

				const data = {
					employee_id: this.item.employee_id,
					salary: this.salary,
				};

				await this.$store.dispatch("manager/editSalary", data);

				this.alert = {
					type: "success",
					message: res.message,
				};

				setTimeout(() => this.$emit("close-modal"), 1000);
			} catch (error) {
				console.error(error);
			}
		},
	},

	created() {
		this.salary = this.item.salary;
		this.workingHours = [...this.item.workingHours];
		this.workingHours.sort((a, b) => a.id - b.id);
	},
};
</script>

<style></style>
