<template>
	<section class="home-manager">
		<RoomTypeForm />
		<RoomTypeTable />
		<RoomForm />
		<RoomTable />
		<v-btn depressed color="primary" class="mb-3" small @click="modal = true">
			<v-icon small>mdi-plus</v-icon>
			Add employee
		</v-btn>
		<EmployeeTable />
		<EmployeeModal v-if="modal" @close-modal="modal = false" />
	</section>
</template>

<script>
import EmployeeModal from "@/components/EmployeeModal";
import EmployeeTable from "@/components/EmployeeTable";
import RoomForm from "@/components/RoomForm";
import RoomTable from "@/components/RoomTable";
import RoomTypeForm from "@/components/RoomTypeForm";
import RoomTypeTable from "@/components/RoomTypeTable";

export default {
	name: "HomeManager",

	components: {
		EmployeeModal,
		EmployeeTable,
		RoomForm,
		RoomTable,
		RoomTypeForm,
		RoomTypeTable,
	},

	data() {
		return {
			modal: false,
		};
	},
};
</script>

<style lang="scss" scoped>
.room-type-table {
	margin-bottom: 50px;
}
</style>
