import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
	{
		path: "/",
		redirect: "/home",
	},
	{
		path: "/home",
		component: () => import("@/views/Home"),
		redirect: to => {
			const role = "";
			if (!role) return "/home/guest";
			else return "/home/manager";
		},
		children: [
			{
				path: "guest",
				name: "HomeGuest",
				component: () => import("@/views/HomeGuest"),
				meta: {
					title: "Home | Guest",
				},
			},
			{
				path: "manager",
				name: "HomeManager",
				component: () => import("@/views/HomeManager"),
				meta: {
					title: "Home | Manager",
				},
			},
		],
	},
	{
		path: "/login",
		name: "Login",
		component: () => import("@/views/Login"),
		meta: {
			title: "Login",
		},
	},
	{
		path: "/register",
		name: "Register",
		component: () => import("@/views/Register"),
		meta: {
			title: "Registration",
		},
	},
	{
		path: "/forbidden",
		name: "ErrorPage404",
		component: () => import("@/views/ErrorPage"),
		meta: {
			title: "403 | Forbidden",
			name: "Forbidden",
			status: "403",
		},
	},
	{
		path: "*",
		name: "ErrorPage404",
		component: () => import("@/views/ErrorPage"),
		meta: {
			title: "404 | Not found",
			name: "Not found",
			status: "404",
		},
	},
];

const router = new VueRouter({
	mode: "history",
	base: process.env.BASE_URL,
	routes,
});

router.beforeEach((to, from, next) => {
	document.title = to.meta.title;
	next();
});

export default router;
