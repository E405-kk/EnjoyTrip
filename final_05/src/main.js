import "../styles.css";

import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

const app = createApp(App);

// import { useKakao } from "vue3-kakao-maps/@utils";
// useKakao("d234049397979f6f3e7cf5e308e8acea");

app.use(createPinia());
app.use(router);

app.mount("#app");
