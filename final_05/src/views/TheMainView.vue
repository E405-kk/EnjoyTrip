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
  <div id="recommend" class="mx-auto my-20" style="width: 80%">
    <h2 class="text-left font-bold text-xl mb-4 ml-4">이 달의 추천 여행지</h2>
    <div class="flex overflow-x-auto scrollbar">
      <VCard
        v-for="recommend in recommends"
        :key="recommend.title"
        :recommend="recommend"
        class="flex-none shadow-md rounded-xl overflow-hidden hover:shadow-lg hover:-translate-y-1 transition-all duration-300 max-w-sm mr-4" />
    </div>
  </div>

  <div id="rank" class="mx-auto my-20" style="width: 80%">
    <h2 class="text-left font-bold text-xl mb-4 ml-4">인기 맛집</h2>
    <div class="flex overflow-x-auto scrollbar">
      <div
        v-for="rank in ranks"
        :key="rank.title"
        :rank="rank"
        class="flex-none shadow-md rounded-xl overflow-hidden hover:shadow-lg hover:-translate-y-1 transition-all duration-300 max-w-sm mr-4">
        <a
          href=""
          class="hover:text-orange-600 absolute z-30 top-2 right-0 mt-2 mr-3"></a>
        <a href="" class="z-20 absolute h-full w-full top-0 left-0">&nbsp;</a>
        <div class="h-auto overflow-hidden">
          <div class="h-44 overflow-hidden relative">
            <img :src="rank.firstImage" alt="" />
          </div>
        </div>
        <div class="bg-white py-4 px-3">
          <h3 class="text-xs mb-2 font-medium">{{ rank.title }}</h3>
          <div class="flex justify-between items-center">
            <p class="text-xs text-gray-400">
              {{ rank.addr1 }} {{ rank.addr2 }}
            </p>
          </div>
        </div>
      </div>
      <!-- <VCard
        v-for="rank in ranks"
        :key="rank.title"
        :rank="rank"
        class="flex-none shadow-md rounded-xl overflow-hidden hover:shadow-lg hover:-translate-y-1 transition-all duration-300 max-w-sm mr-4" /> -->
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
