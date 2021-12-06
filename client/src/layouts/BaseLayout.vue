<template>
	<div class="base-layout">
		<TheNavbar />
		<v-main class="my-main py-4">
			<v-container class="my-container">
				<router-view />
			</v-container>
		</v-main>
		<TheFooter />
	</div>
</template>

<script>
import TheFooter from "@/components/TheFooter";
import TheNavbar from "@/components/TheNavbar";
export default {
	name: "BaseLayout",

	components: {
		TheNavbar,
		TheFooter,
	},
};
</script>

<style lang="scss" scoped>
.base-layout {
	height: 100%;
	display: flex;
	flex-direction: column;
}
.my-main {
	flex-grow: 1;
	margin: 50px 0 0;
}
.my-container {
	height: 100%;
}
</style>
