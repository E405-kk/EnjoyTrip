<script setup>
defineProps({
  recommend: Object,
});
</script>

<template>
  <div
    class="relative flex flex-col shadow-md rounded-xl overflow-hidden hover:shadow-lg hover:-translate-y-1 transition-all duration-300 max-w-sm">
    <a
      href=""
      class="hover:text-orange-600 absolute z-30 top-2 right-0 mt-2 mr-3">
    </a>
    <a href="" class="z-20 absolute h-full w-full top-0 left-0">&nbsp;</a>
    <div class="h-auto overflow-hidden">
      <div class="h-44 overflow-hidden relative">
        <img :src="recommend.firstImage" alt="" />
      </div>
    </div>
    <div class="bg-white py-4 px-3">
      <h3 class="text-xs mb-2 font-medium">
        {{ recommend.title }}
      </h3>
      <div class="flex justify-between items-center">
        <p class="text-xs text-gray-400">
          {{ recommend.addr1 }} {{ recommend.addr2 }}
        </p>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
