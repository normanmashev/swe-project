export const rules = {
	email: [
		v => !!v || "E-mail is required",
		v => /.+@.+\..+/.test(v) || "E-mail must be valid",
	],
	required: [v => !!v || "This field is required"],
	password: [
		v => !!v || "Password is required",
		v => v.length < 4 || "Enter at least 4 characters",
	],
};
