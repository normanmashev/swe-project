import { get, set, remove } from "js-cookie";

const TOKEN_KEY = "Authorization";

export default {
	getToken() {
		return get(TOKEN_KEY);
	},
	setToken(token) {
		return set(TOKEN_KEY, token, { expires: 3, secure: true });
	},
	removeToken() {
		return remove(TOKEN_KEY);
	},
};
