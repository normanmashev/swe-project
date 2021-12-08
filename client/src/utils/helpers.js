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
	{ text: "Driver license", value: "DRIVER LICENSE" },
	{ text: "Passport", value: "PASSPORT" },
];
