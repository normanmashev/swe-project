<template>
	<v-app>
		<component :is="`${layout}-layout`">
			<router-view />
		</component>
	</v-app>
</template>

<script>
import BaseLayout from "@/layouts/BaseLayout";
import EmptyLayout from "@/layouts/EmptyLayout";

export default {
	name: "App",

	components: { BaseLayout, EmptyLayout },

	computed: {
		layout() {
			return this.$route.meta.layout || "base";
		},
	},
};
</script>
