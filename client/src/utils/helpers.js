export const RULES = {
	capacity: [v => !!v || v > 0 || "Minimum capacity is 1 person"],
	email: [
		v => !!v || "E-mail is required",
		v => /.+@.+\..+/.test(v) || "E-mail must be valid",
	],
	required: [v => !!v || "This field is required"],
	password: [
		v => !!v || "Password is required",
		v => v.length < 4 || "Enter at least 4 characters",
	],
	phone: [
		v => !!v || "Phone is required",
		v => v.length === 16 || "Enter correct phone number",
	],
};

export const ID_TYPES = [
	{ text: "Driver license", value: "DRIVING_LICENSE" },
	{ text: "Passport", value: "PASSPORT" },
];

export const CITIES = [
	"Aktau",
	"Aktobe",
	"Almaty",
	"Arkalyk",
	"Atyrau",
	"Baikonur",
	"Balkhash",
	"Zhezkazgan",
	"Karagandy",
	"Kentau",
	"Kyzylorda",
	"Kokshetau",
	"Kostanay",
	"Nur-Sultan (Astana)",
	"Zhanaozen",
	"Pavlodar",
	"Petropavl",
	"Ridder",
	"Saran",
	"Satpayev",
	"Semey",
	"Stepnogorsk",
	"Taldykorgan",
	"Taraz",
	"Temirtau",
	"Turkistan",
	"Oral",
	"Oskemen",
	"Shymkent",
	"Shakhtinsk",
	"Schuchinsk",
	"Ekibastuz",
];

export const ROOMTYPES = [
	"Single",
	"Double",
	"Triple",
	"Quad",
	"Queen",
	"King",
	"Twin",
	"Hollywood Twin Room",
	"Double-double",
	"Studio",
	"Suite / Executive Suite",
	"Mini Suite",
	"President Suite",
	"Apartments / Room for Extended Stay",
	"Connecting rooms",
	"Murphy Room",
	"Accessible Room / Disabled Room",
	"Cabana",
	"Adjoining rooms",
	"Adjacent rooms",
	"Villa",
	"Executive Floor/Floored Room",
	"Smoking / Non-Smoking Room",
];

export const DAYS = [
	"Monday",
	"Tuesday",
	"Wednesday",
	"Thursday",
	"Friday",
	"Saturday",
	"Sunday",
];

export const ROLES = [
	"Front Office Attendant",
	"Guest Services Associate",
	"Guest Services Supervisor",
	"Desk Clerk",
	"Hotel Receptionist",
	"Reservationist",
];
