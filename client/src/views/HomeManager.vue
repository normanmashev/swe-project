<template>
	<section class="home-manager">
		<EmployeeTable />
	</section>
</template>

<script>
import EmployeeTable from "@/components/EmployeeTable";

export default {
	name: "HomeManager",

	components: {
		EmployeeTable,
	},
};
</script>

<style></style>
