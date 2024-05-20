<script setup>
import { onMounted, ref } from "vue";
import AOS from "aos";
import "aos/dist/aos.css";
import VCard from "@/components/common/VCard.vue";
import { listMonthly } from "@/api/map";

const recommends = ref([]);
onMounted(() => {
  AOS.init();
  getRecommendList();
});
const getRecommendList = () => {
  listMonthly(
    ({ data }) => {
      recommends.value = data;
    },
    (error) => {
      console.log(err);
    }
  );
};
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
      data-aos-duration="1000"
      data-aos-once="false"
      data-aos-delay="600"
      style="bottom: 20%; left: 10%">
      <router-link
        class="flex items-center text-lg font-bold text-white md:hover:text-gray-700"
        :to="{ name: 'hotplace' }"
        >핫플레이스</router-link
      >
    </div>
  </div>
  <div id="recommend" class="mt-10 overflow-x-auto" style="width: 300px">
    <div class="white-space-nowrap">
      <ul>
        <li>
          <VCard
            v-for="recommend in recommends"
            :key="recommend.title"
            :recommend="recommend" />
        </li>
      </ul>
    </div>
  </div>
  <div id="rank"></div>
</template>

<style scoped>
.image-container {
  position: relative;
  width: 100%;
  height: 150vh;
}

.full-width-image {
  width: 100%;
  height: 150vh;
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
