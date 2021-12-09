import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store";

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
			const role = store.getters["user/getRole"];
			if (!role || role === "guest") return "/home/guest";
			else if (role === "manager") return "/home/manager";
			else return "/home/employee";
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
					requireAuth: true,
					perm: "manager",
				},
			},
			{
				path: "employee",
				name: "HomeEmployee",
				component: () => import("@/views/HomeEmployee"),
				meta: {
					title: "Home | Employee",
					requireAuth: true,
					perm: "Desk Clerk",
				},
			},
		],
	},
	{
		path: "/profile",
		component: () => import("@/views/Profile"),
		redirect: to => {
			const role = "";
			if (!role) return "/profile/guest";
			else return "/profile/manager";
		},
		children: [
			{
				path: "guest",
				name: "ProfileGuest",
				component: () => import("@/views/ProfileGuest"),
				meta: {
					title: "Profile | Guest",
				},
			},
			{
				path: "manager",
				name: "ProfileManager",
				component: () => import("@/views/ProfileManager"),
				meta: {
					title: "Profile | Manager",
				},
			},
		],
	},
	{
		path: "/hotel/:id",
		name: "HotelInfo",
		component: () => import("@/views/HotelInfo"),
		meta: {
			title: "Hotel info",
			requireAuth: true,
			perm: "guest",
		},
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
		path: "/logout",
		name: "Logout",
		component: () => import("@/views/Logout"),
		meta: {
			title: "Logout",
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
	const role = store.getters["user/getRole"];
	const isAuth = store.getters["user/isAuth"];
	const { requireAuth, perm } = to.meta;

	if ((requireAuth && !isAuth) || (requireAuth && isAuth && role !== perm)) {
		next("/forbidden");
	} else {
		next();
	}
});

export default router;
