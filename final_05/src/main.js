import "../styles.css";
import "aos/dist/aos.css";
import "@/assets/fonts.css";

import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import AOS from "aos";
import Swal from "sweetalert2";

const app = createApp(App);
app.use(createPinia());

app.use(router).mount("#app", () => {
  AOS.init();
});
