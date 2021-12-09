// ? DeepCopy Объекта
Object.defineProperty(Object.prototype, "deepCopy", {
	value: function () {
		return JSON.parse(JSON.stringify(this));
	},
	enumerable: false,
});

Object.defineProperty(Object.prototype, "toFormData", {
	value: function () {
		const formData = new FormData();
		const buildFormData = function (formData, data, parentKey) {
			if (
				data &&
				typeof data === "object" &&
				!(data instanceof Date) &&
				!(data instanceof File)
			) {
				Object.keys(data).forEach(key => {
					buildFormData(
						formData,
						data[key],
						parentKey ? `${parentKey}[${key}]` : key
					);
				});
			} else {
				const value = data == null ? "" : data;

				formData.append(parentKey, value);
			}
		};
		buildFormData(formData, this);
		return formData;
	},
	enumerable: false,
});

// ? Обнуление полей объекта
Object.defineProperty(Object.prototype, "nullify", {
	value: function () {
		for (const key in this) {
			// Если ключ является массивом
			if (Array.isArray(this[key])) {
				this[key] = [];
			} else if (typeof this[key] === "boolean") {
				this[key] = false;
			} else if (typeof this[key] === "number") {
				// Число
				this[key] = 0;
			} else if (typeof this[key] === "string") {
				this[key] = "";
			} else {
				this[key] = null;
			}
		}
		return this;
	},
	enumerable: false,
});
