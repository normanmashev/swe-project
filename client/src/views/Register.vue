<template>
	<section class="register">
		<SignUpForm />
	</section>
</template>

<script>
import SignUpForm from "@/components/SignUpForm";

export default {
	name: "Register",

	components: {
		SignUpForm,
	},
};
</script>

<style scoped lang="scss">
.login {
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
