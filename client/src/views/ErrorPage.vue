<template>
	<section class="error-page">
		<h1 class="text-h1">{{ $route.meta.status }}</h1>
		<hr class="my-4" />
		<h5 class="text-h5">{{ $route.meta.name }}</h5>
		<v-btn class="mt-4" color="primary" text depressed link to="/">
			<span>Home page</span>
			<v-icon small>mdi-arrow-right</v-icon>
		</v-btn>
	</section>
</template>

<script>
export default {
	name: "ErrorPage",
};
</script>

<style lang="scss" scoped>
.error-page {
	height: 100%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	hr {
		border: 0;
		outline: 0;
		width: 100px;
		height: 2px;
		background: #000;
		color: #000;
	}
}
</style>
