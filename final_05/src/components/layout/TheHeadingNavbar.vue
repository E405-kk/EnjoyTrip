<script setup>
import { useMenuStore } from "@/stores/menu";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";

const menuStore = useMenuStore();
const memberStore = useMemberStore();

const { menuList } = storeToRefs(menuStore);

const { userLogout } = memberStore;

const logout = () => {
  userLogout();
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

      <div class="w-full md:block md:w-auto" id="mobile-menu">
        <ul
          class="flex mt-4 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium">
          <li>
            <a
              href="#"
              class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">
              지역별 관광지
            </a>
          </li>
          <li>
            <a
              href="#"
              class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">
              여행 계획
            </a>
          </li>
          <li>
            <a
              href="#"
              class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">
              여행정보공유
            </a>
          </li>
          <li>
            <a
              href="#"
              class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">
              공지사항
            </a>
          </li>
          <li>
            <router-link :to="{ name: 'board' }">Q&A</router-link>
          </li>

          <template v-for="menu in menuList" :key="menu.routeName">
            <template v-if="menu.show">
              <template
                v-if="
                  menu.routeName === 'user-logout' ||
                  menu.routeName === 'user-join'
                ">
                <li class="nav-item">
                  <router-link
                    to="/"
                    @click.prevent="logout"
                    class="nav-link"
                    >{{ menu.name }}</router-link
                  >
                </li>
              </template>

              <template v-else>
                <li class="nav-item">
                  <router-link
                    :to="{ name: menu.routeName }"
                    class="nav-link"
                    >{{ menu.name }}</router-link
                  >
                </li>
              </template>
            </template>
          </template>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>
