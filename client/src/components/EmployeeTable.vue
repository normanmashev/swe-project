<template>
	<v-data-table
		:headers="headers"
		:items="items"
		:items-per-page="5"
		class="elevation-1"
	>
		<template v-slot:top>
			<h1 class="pa-4">Employee schedule</h1>
		</template>
	</v-data-table>
</template>

<script>
export default {
	name: "EmployeeTable",

	data() {
		return {
			headers: [
				{
					text: "Fullname",
					value: "name",
					sortable: false,
				},
				{ text: "Role", value: "role", sortable: false },
			],
		};
	},

	computed: {},
};
</script>

<style></style>
