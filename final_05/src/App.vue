<script setup>
import { RouterView } from "vue-router";
import TheHeadingNavbar from "@/components/layout/TheHeadingNavbar.vue";
</script>

<template>
  <div id="app">
    <TheHeadingNavbar></TheHeadingNavbar>
    <RouterView></RouterView>
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
}
</style>
