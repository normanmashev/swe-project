<template>
	<section class="home-manager">
		<RoomTypeForm />
		<RoomTypeTable />
		<EmployeeTable />
	</section>
</template>

<script>
import EmployeeTable from "@/components/EmployeeTable";
import RoomTypeForm from "@/components/RoomTypeForm";
import RoomTypeTable from "@/components/RoomTypeTable";

export default {
	name: "HomeManager",

	components: {
		EmployeeTable,
		RoomTypeForm,
		RoomTypeTable,
	},
};
</script>

<style lang="scss" scoped>
.room-type-table {
	margin-bottom: 50px;
}
</style>
