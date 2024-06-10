<script setup>
import { ref } from "vue";
import HotplaceFormItem from "./item/HotplaceFormItem.vue";
import VHotplaceMap from "@/components/common/VHotplaceMap.vue";
const curLatlng = ref({
  lat: 0,
  lng: 0,
});
const curDetailAddr = ref("");
const handlePickPlace = function (latlng, detailAddr) {
  curLatlng.value.lat = latlng.getLat();
  curLatlng.value.lng = latlng.getLng();
  curDetailAddr.value = detailAddr;
};
</script>
<template>
  <div class="container mx-auto">
    <div class="flex justify-center">
      <div class="w-full lg:w-10/12 text-left">
        <div class="flex flex-row space-x-4">
          <div class="w-1/2 my-3">
            <VHotplaceMap @pick-place="handlePickPlace" />
          </div>
          <div class="w-1/2">
            <HotplaceFormItem
              type="regist"
              :latlng="curLatlng"
              :detailAddr="curDetailAddr" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
