<script setup>
import { computed } from "vue";

const props = defineProps({ currentPage: Number, totalPage: Number });
const emit = defineEmits(["pageChange"]);

const navigationSize = parseInt(import.meta.env.VITE_ARTICLE_NAVIGATION_SIZE);

const startPage = computed(() => {
  return (
    parseInt((props.currentPage - 1) / navigationSize) * navigationSize + 1
  );
});

const endPage = computed(() => {
  let lastPage =
    parseInt((props.currentPage - 1) / navigationSize) * navigationSize +
    navigationSize;
  return props.totalPage < lastPage ? props.totalPage : lastPage;
});

const endRange = computed(() => {
  return (
    parseInt((props.totalPage - 1) / navigationSize) * navigationSize <
    props.currentPage
  );
});

function range(start, end) {
  const list = [];
  for (let i = start; i <= end; i++) list.push(i);
  return list;
  //   return Array(end - start + 1)
  //     .fill()
  //     .map((val, i) => start + i);
}

function onPageChange(pg) {
  console.log(pg + "로 이동!!!");
  emit("pageChange", pg);
}
</script>

<template>
  <nav>
    <ul class="flex justify-center mt-6">
      <li>
        <a
          class="mx-1 flex h-11 w-11 items-center justify-center rounded-full border border-blue-gray-100 bg-transparent p-0 text-sm text-blue-gray-500 transition duration-150 ease-in-out hover:bg-light-300"
          @click="onPageChange(1)"
          href="#">
          최신
        </a>
      </li>
      <li>
        <a
          class="mx-1 flex h-11 w-11 items-center justify-center rounded-full border border-blue-gray-100 bg-transparent p-0 text-sm text-blue-gray-500 transition duration-150 ease-in-out hover:bg-light-300"
          @click="onPageChange(startPage == 1 ? 1 : startPage - 1)"
          aria-label="Previous"
          href="#">
          <span class="material-icons text-sm">keyboard_arrow_left</span>
        </a>
      </li>
      <template v-for="pg in range(startPage, endPage)" :key="pg">
        <li
          :class="
            currentPage === pg
              ? 'mx-1 flex h-11 w-11 items-center justify-center rounded-full bg-emerald-500 p-0 text-sm text-white shadow-md transition duration-150 ease-in-out'
              : 'mx-1 flex h-11 w-11 items-center justify-center rounded-full border border-blue-gray-100 bg-transparent p-0 text-sm text-blue-gray-500 transition duration-150 ease-in-out hover:bg-light-300'
          ">
          <a @click="onPageChange(pg)" href="#">{{ pg }}</a>
        </li>
      </template>
      <li>
        <a
          class="mx-1 flex h-11 w-11 items-center justify-center rounded-full border border-blue-gray-100 bg-transparent p-0 text-sm text-blue-gray-500 transition duration-150 ease-in-out hover:bg-light-300"
          @click="onPageChange(endRange ? totalPage : endPage + 1)"
          aria-label="Next"
          href="#">
          <span class="material-icons text-sm">keyboard_arrow_right</span>
        </a>
      </li>
      <li>
        <a
          class="mx-1 flex h-11 w-11 items-center justify-center rounded-full border border-blue-gray-100 bg-transparent p-0 text-sm text-blue-gray-500 transition duration-150 ease-in-out hover:bg-light-300"
          @click="onPageChange(totalPage)"
          href="#">
          마지막
        </a>
      </li>
    </ul>
  </nav>

  <link
    href="https://fonts.googleapis.com/icon?family=Material+Icons"
    rel="stylesheet" />
</template>

<style scoped></style>
