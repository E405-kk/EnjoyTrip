<script setup>
import { localAxios } from "@/util/http-commons";
const local = localAxios();

const uploadFile = () => {
  var formData = new FormData();
  var photoFile = document.getElementById("photo");
  console.log(photoFile.files[0]);
  const data = {
    userId: "ssafy",
    articleNo: 1,
  };
  formData.append("file", photoFile.files[0]);
  const blob = new Blob([JSON.stringify(data)], {
    type: "application/json",
  });
  // formData.append("hotplaceDto", JSON.stringify(data));
  formData.append("hotplaceDto", blob);
  const config = {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  };
  local
    .post(`/hotplace/regist`, formData, config)
    .then((response) => {
      console.log(response);
      console.log("성공");
    })
    .catch((error) => {
      console.log(error);
      console.log("예외");
    });
};
</script>

<template>
  <form>
    <input type="file" name="photo" id="photo" />
    <button @click="uploadFile()">업로드</button>
  </form>
</template>

<style scoped></style>
