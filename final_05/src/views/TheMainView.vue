<script setup>
import { onMounted, ref } from "vue";
import AOS from "aos";
import "aos/dist/aos.css";
import VCard from "@/components/common/VCard.vue";
import { listMonthly, listRank } from "@/api/map";

const recommends = ref([]);
const ranks = ref([]);
onMounted(() => {
  AOS.init();
  getRecommendList();
  getRankList();
});
const getRecommendList = () => {
  listMonthly(
    ({ data }) => {
      recommends.value = data;
    },
    (err) => {
      console.log(err);
    }
  );
};
const getRankList = () => {
  listRank(
    ({ data }) => {
      ranks.value = data;
    },
    (err) => {
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
      data-aos-duration="1800"
      data-aos-once="true"
      data-aos-delay="400"
      style="top: 30%; left: 15%">
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
      data-aos-duration="1800"
      data-aos-once="true"
      data-aos-delay="800"
      style="top: 50%; right: 15%">
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
      data-aos-once="true"
      data-aos-delay="0"
      style="bottom: 20%; left: 15%">
      <router-link
        class="flex items-center text-lg font-bold text-white md:hover:text-gray-700"
        :to="{ name: 'hotplace' }"
        >핫플레이스</router-link
      >
    </div>
  </div>
  <div
    id="recommend"
    class="mx-auto my-20"
    style="width: 80%"
    data-aos="fade-up"
    data-aos-easing="ease-in-out"
    data-aos-delay="100"
    data-aos-duration="2000">
    <h2 class="text-left font-bold text-2xl ml-4">이 달의 추천 여행지</h2>
    <div class="py-5 flex overflow-x-auto scrollbar">
      <VCard
        v-for="recommend in recommends"
        :key="recommend.idx"
        :recommend="recommend"
        type="recommend"
        class="flex-none shadow-md rounded-xl overflow-hidden hover:shadow-lg hover:-translate-y-1 transition-all duration-300 max-w-sm mr-4" />
    </div>
  </div>

  <div
    id="rank"
    class="mx-auto my-20"
    style="width: 80%"
    data-aos="fade-up"
    data-aos-easing="ease-in-out"
    data-aos-delay="200"
    data-aos-duration="2000">
    <h2 class="text-left font-bold text-2xl ml-4">인기 맛집</h2>
    <div class="py-5 flex overflow-x-auto scrollbar">
      <VCard
        v-for="rank in ranks"
        :key="rank.contentId"
        :recommend="rank"
        type="rank"
        class="flex-none shadow-md rounded-xl overflow-hidden hover:shadow-lg hover:-translate-y-1 transition-all duration-300 max-w-sm mr-4" />
    </div>
  </div>
</template>

<style scoped>
.image-container {
  position: relative;
  width: 100%;
  height: 120vh;
}

.full-width-image {
  width: 100%;
  height: 120vh;
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
