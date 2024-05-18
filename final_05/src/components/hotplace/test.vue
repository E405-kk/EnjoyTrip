<script setup>
import { localAxios } from "@/util/http-commons";
const local = localAxios();

const uploadFile = () => {
  var formData = new FormData();
  var photoFile = document.getElementById("photo");
  console.log(photoFile.files[0]);
  const data = {
    userId: "ssafy",
    articleNo: 4,
  };
  formData.append("file", photoFile.files[0]);
  const blob = new Blob([JSON.stringify(data)], {
    type: "application/json",
  });
  // formData.append("hotplaceDto", JSON.stringify(data));
  formData.append("hotplaceDto", blob);
  console.log(formData);
  console.log(blob);
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
    <button @click="uploadFile">업로드</button>
  </form>
  <img
    src="C:/SSAFY/workspace/final_buk04_11_05/final_buk04_11_05/src/main/webapp/upload/240518/1a108ff6-b13a-4040-8ada-2f0aaa8260b4.jpg" />
</template>

<style scoped></style>
