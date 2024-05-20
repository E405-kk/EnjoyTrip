<script setup>
import { ref } from "vue";
import { useMenuStore } from "@/stores/menu";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";

const menuStore = useMenuStore();
const memberStore = useMemberStore();

const { menuList, userInfo } = storeToRefs(menuStore);

const { userLogout } = memberStore;

const logout = () => {
  userLogout();
};
if (sessionStorage.getItem("userId")) {
  menuList.value[0].show = false;
  menuList.value[1].show = false;
  menuList.value[2].show = true;
  menuList.value[3].show = true;
}
const showDropdown = ref(false); // 드롭다운 메뉴의 표시 여부를 관리하는 ref 변수

// 드롭다운 메뉴를 열거나 닫는 함수
const toggleDropdown = () => {
  showDropdown.value = !showDropdown.value;
};
</script>

<template>
  <!-- component -->
  <nav
    class="bg-white border border-gray-200 dark:border-gray-700 px-2 sm:px-4 py-2.5 rounded dark:bg-gray-800 shadow">
    <div class="container flex justify-between items-center mx-auto">
      <span
        class="self-center text-xl font-semibold whitespace-nowrap dark:text-white">
        <router-link :to="{ name: 'main' }"> EnjoyTrip </router-link>
      </span>

      <div class="w-full md:block md:w-auto">
        <ul
          class="flex mt-4 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium items-center">
          <li>
            <router-link
              class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700"
              :to="{ name: 'trip-search' }"
              >지역별 관광지</router-link
            >
          </li>
          <li>
            <router-link
              class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700"
              :to="{ name: 'trip-plan' }"
              >여행 계획</router-link
            >
          </li>
          <li>
            <router-link
              class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700"
              :to="{ name: 'hotplace' }"
              >핫플레이스</router-link
            >
          </li>
          <li>
            <router-link
              class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700"
              :to="{ name: 'notice' }"
              >공지사항</router-link
            >
          </li>
          <li>
            <router-link
              class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700"
              :to="{ name: 'board' }"
              >Q&A</router-link
            >
          </li>
          <li class="text-center">
            <!-- Dropdown toggle button -->
            <button
              @click="toggleDropdown"
              class="relative z-10 flex items-center p-2 text-sm text-gray-600 bg-white border border-transparent rounded-md focus:border-blue-500 focus:ring-opacity-40 dark:focus:ring-opacity-40 focus:ring-blue-300 dark:focus:ring-blue-400 focus:ring dark:text-white dark:bg-gray-800 focus:outline-none">
              <span class="mx-1">회원 정보</span>
              <svg
                class="w-5 h-5 mx-1"
                viewBox="0 0 24 24"
                fill="none"
                xmlns="http://www.w3.org/2000/svg">
                <path
                  d="M12 15.713L18.01 9.70299L16.597 8.28799L12 12.888L7.40399 8.28799L5.98999 9.70199L12 15.713Z"
                  fill="currentColor"></path>
              </svg>
            </button>

            <!-- Dropdown menu -->
            <div
              v-if="showDropdown"
              class="absolute right-0 z-20 w-56 py-2 mt-2 overflow-hidden bg-white rounded-md shadow-xl dark:bg-gray-800">
              <a
                href="#"
                class="flex items-center p-3 -mt-2 text-sm text-gray-600 transition-colors duration-200 transform dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 dark:hover:text-white">
                <img
                  class="flex-shrink-0 object-cover mx-1 rounded-full w-9 h-9"
                  src="@/assets/user.png" />
                <div class="mx-1">
                  <h1
                    v-if="userInfo"
                    class="text-sm font-semibold text-gray-700 dark:text-gray-200">
                    {{ userInfo.userName }}
                  </h1>
                  <p
                    v-if="userInfo"
                    class="text-sm text-gray-500 dark:text-gray-400">
                    {{ userInfo.userEmail }}
                  </p>
                </div>
              </a>

              <hr class="border-gray-200 dark:border-gray-700" />
              <template v-for="menu in menuList" :key="menu.routeName">
                <template v-if="menu.show">
                  <template v-if="menu.routeName === 'user-logout'">
                    <li class="nav-item">
                      <router-link
                        to="/"
                        @click.prevent="logout"
                        class="nav-link block py-2 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700"
                        >{{ menu.name }}</router-link
                      >
                    </li>
                  </template>
                  <template v-else>
                    <li class="nav-item">
                      <router-link
                        class="nav-link block py-2 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700"
                        :to="{ name: menu.routeName }"
                        >{{ menu.name }}</router-link
                      >
                    </li>
                  </template>
                </template>
              </template>

              <hr class="border-gray-200 dark:border-gray-700" />

              <router-link
                class="block py-2 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700"
                :to="{ name: 'trip-userPlan' }"
                >나의 여행 계획</router-link
              >
            </div>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>
