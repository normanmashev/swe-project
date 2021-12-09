import Vue from "vue";

Vue.filter("fullname", function (x) {
	return x.name + " " + x.surname;
});

Vue.filter("timestamp", function (x) {
  const ts = new Date(x);
  const date = ts.getDate()+"/"+(ts.getMonth()+1)+"/"+ts.getFullYear();
  return date;
});
