<template>
	<v-snackbar
		:color="color"
		:timeout="timer"
		v-model="showSnackbar"
		center
		top
		rounded
	>
		<div class="d-flex justify-space-between">
			<span class="d-flex align-items-center">{{ message }}</span>
			<v-btn icon @click="showSnackbar = false">
				<v-icon>{{ icon }}</v-icon>
			</v-btn>
		</div>
	</v-snackbar>
</template>

<script>
export default {
	name: "Snackbar",

	data() {
		return {
			showSnackbar: false,
			message: "",
			color: "success",
			icon: "mdi-close",
			timer: 2000,
		};
	},

	methods: {
		show(data) {
			this.showSnackbar = true;
			this.message = data.message || 'missing "message".';
			this.color = data.color || "success";
			this.timer = data.timer || 2000;
			this.icon = data.icon || "mdi-close";
		},
	},
};
</script>

<style></style>
