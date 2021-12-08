<template>
	<v-card max-width="500px" width="300px">
		<v-card-title>
			<h5 class="text-h5">Registration</h5>
		</v-card-title>
		<v-card-text>
			<p class="subtitle-1 text-center">I want to register as</p>
			<div class="d-flex flex-column">
				<v-btn
					class="primary mb-2"
					depressed
					@click="$store.commit('register/SET_TYPE', 1)"
					>Guest</v-btn
				>
				<v-btn
					class="blue white--text"
					depressed
					@click="$store.commit('register/SET_TYPE', 2)"
					>Hotel manager</v-btn
				>
			</div>
		</v-card-text>
	</v-card>
</template>

<script>
export default {
	name: "RegisterType",
};
</script>

<style></style>
