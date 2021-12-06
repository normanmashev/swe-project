<template>
	<section class="login">
		<LoginForm />
	</section>
</template>

<script>
import LoginForm from "@/components/LoginForm";

export default {
	name: "Login",

	components: {
		LoginForm,
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
