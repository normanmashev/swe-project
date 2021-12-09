<template>
	<section class="logout"></section>
</template>

<script>
export default {
	name: "Logout",

	beforeCreate() {
		this.$store.dispatch("user/logout");
	},

	mounted() {
		this.$root.snackbar.show({ message: "You are logged out" });
		this.$router.push("/");
	},
};
</script>

<style></style>
