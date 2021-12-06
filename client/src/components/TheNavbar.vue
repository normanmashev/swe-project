<template>
	<v-app-bar app hide-on-scroll color="primary">
		<v-toolbar-title class="font-weight-bold white--text">
			<h3>Title</h3>
		</v-toolbar-title>

		<v-spacer></v-spacer>

		<v-toolbar-items>
			<v-btn class="white--text" link text to="/"> Home </v-btn>
			<v-btn
				v-for="e of menuItems"
				:to="e.to"
				:key="e.path"
				:color="e.color"
				class="white--text"
				link
				text
			>
				{{ e.title }}
			</v-btn>
		</v-toolbar-items>
	</v-app-bar>
</template>

<script>
export default {
	name: "TheNavbar",

	data() {
		return {
			appTitle: "World hotels",

			menuItems: [],

			guestItems: [{ title: "Sign In", to: "/login" }],

			userItems: [{ title: "Profile", to: "/profile" }],

			managerItems: [{ title: "My hotel", to: "/hotel" }],

			employeeItems: [{}],
		};
	},

	created() {
		const role = "";
		if (!role) this.menuItems = this.guestItems;
	},
};
</script>

<style></style>
