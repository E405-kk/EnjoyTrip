<script setup>
import { onMounted } from "vue";
import AOS from "aos";
import "aos/dist/aos.css";

onMounted(() => {
  AOS.init();
});
</script>

<template>
  <div class="image-container">
    <img class="full-width-image" src="@/assets/jeju-img.jpeg" alt="main img" />
    <div
      class="overlay-content border-2 bg-white bg-opacity-20"
      data-aos="fade-right"
      data-aos-easing="ease-in-out"
      data-aos-offset="100"
      data-aos-duration="1500"
      data-aos-once="false"
      data-aos-delay="400"
      style="top: 30%; left: 10%">
      <router-link
        class="flex items-center text-lg font-bold text-white md:hover:text-gray-700"
        :to="{ name: 'trip-search' }"
        >지역별 관광지 검색</router-link
      >
    </div>
    <div
      class="overlay-content border-2 bg-white bg-opacity-20"
      data-aos="fade-left"
      data-aos-easing="ease-in-out"
      data-aos-offset="100"
      data-aos-duration="1500"
      data-aos-once="false"
      data-aos-delay="800"
      style="top: 50%; right: 10%">
      <router-link
        class="flex items-center text-lg font-bold text-white md:hover:text-gray-700"
        :to="{ name: 'trip-plan' }"
        >여행 계획</router-link
      >
    </div>
    <div
      class="overlay-content border-2 bg-white bg-opacity-20"
      data-aos="fade-right"
      data-aos-easing="ease-in-out"
      data-aos-offset="100"
      data-aos-duration="1500"
      data-aos-once="false"
      data-aos-delay="1200"
      style="bottom: 10%; left: 10%">
      <router-link
        class="flex items-center text-lg font-bold text-white md:hover:text-gray-700"
        :to="{ name: 'hotplace' }"
        >핫플레이스</router-link
      >
    </div>
  </div>
  <div id="recommend"></div>
  <div id="rank"></div>
</template>

<style scoped>
.image-container {
  position: relative;
  width: 100%;
}

.full-width-image {
  width: 100%;
  height: auto;
  display: block;
}

.overlay-content {
  position: absolute;
  width: 250px;
  height: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
