<script setup>
import { RouterView } from "vue-router";
import TheHeadingNavbar from "@/components/layout/TheHeadingNavbar.vue";
import TheFooter from "@/components/layout/TheFooter.vue";
import { ref } from "vue";
import Modal from "@/components/common/Modal.vue";
const showModal = ref(false);
</script>

<template>
  <div id="app">
    <TheHeadingNavbar></TheHeadingNavbar>
    <RouterView></RouterView>
    <TheFooter></TheFooter>
    <Modal />
  </div>
</template>

<style scoped>
@font-face {
  font-family: "NEXON Lv1 Gothic OTF";
  src: url("https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

body {
  font-family: "NEXON Lv1 Gothic OTF", sans-serif;
  min-height: 100vh; /* 화면 최소 높이 설정 */
  margin: 0; /* 바디 마진 제거 */
  position: relative; /* 포지션 상대적으로 설정 */
}

#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}
</style>
