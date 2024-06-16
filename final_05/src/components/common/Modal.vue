<template>
  <div class="fixed bottom-0 right-0 mb-4 mr-4">
    <button
      id="open-chat"
      class="bg-emerald-500 text-white py-2 px-4 rounded-full hover:bg-emerald-600 transition duration-300 flex items-center">
      <img src="@/assets/chatbot.png" class="w-8 h-8 object-contain" />
    </button>
  </div>
  <div id="chat-container" class="hidden fixed bottom-16 right-4 w-96">
    <div class="bg-white shadow-md rounded-lg max-w-lg w-full">
      <div
        class="p-4 border-b bg-emerald-500 text-white rounded-t-lg flex justify-between items-center">
        <p class="text-lg font-semibold">ET-Bot</p>
        <button
          id="close-chat"
          class="text-gray-300 hover:text-gray-400 focus:outline-none focus:text-gray-400">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="w-6 h-6"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor">
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M6 18L18 6M6 6l12 12"></path>
          </svg>
        </button>
      </div>
      <div id="chatbox" class="p-4 h-80 overflow-y-auto">
        <div class="mb-2">
          <p
            class="bg-gray-200 text-gray-700 rounded-lg py-2 px-4 inline-block">
            안녕하세요! ET-Bot입니다. 무엇을 도와드릴까요?
          </p>
        </div>
      </div>
      <div class="p-4 border-t flex">
        <input
          id="user-input"
          type="text"
          placeholder="Type a message"
          class="w-full px-3 py-2 border rounded-l-md focus:outline-none focus:ring-2 focus:ring-emerald-500" />
        <button
          id="send-button"
          class="bg-emerald-500 text-white px-4 py-2 rounded-r-md hover:bg-emerald-600 transition duration-300">
          Send
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { getAnswer } from "@/api/chatgpt";

var chatbox;
var chatContainer;
var userInput;
var sendButton;
var openChatButton;
var closeChatButton;

onMounted(() => {
  chatbox = document.getElementById("chatbox");
  chatContainer = document.getElementById("chat-container");
  userInput = document.getElementById("user-input");
  sendButton = document.getElementById("send-button");
  openChatButton = document.getElementById("open-chat");
  closeChatButton = document.getElementById("close-chat");

  openChatButton.addEventListener("click", toggleChatbox);
  closeChatButton.addEventListener("click", toggleChatbox);
  sendButton.addEventListener("click", handleMessageSend);
  userInput.addEventListener("keyup", handleKeyUp);

  toggleChatbox();
});

let isChatboxOpen = true;

function toggleChatbox() {
  chatContainer.classList.toggle("hidden");
  isChatboxOpen = !isChatboxOpen;
}

function handleMessageSend() {
  const userMessage = userInput.value;
  if (userMessage.trim() !== "") {
    addUserMessage(userMessage);
    respondToUser(userMessage);
    userInput.value = "";
  }
}

function handleKeyUp(event) {
  if (event.key === "Enter") {
    const userMessage = userInput.value;
    if (userMessage.trim() !== "") {
      addUserMessage(userMessage);
      respondToUser(userMessage);
      userInput.value = "";
    }
  }
}

function addUserMessage(message) {
  const messageElement = document.createElement("div");
  messageElement.classList.add("mb-2", "text-right");
  messageElement.innerHTML = `<p class="bg-emerald-500 text-white rounded-lg py-2 px-4 inline-block">${message}</p>`;
  chatbox.appendChild(messageElement);
  chatbox.scrollTop = chatbox.scrollHeight;
}

function addBotMessage(message) {
  const messageElement = document.createElement("div");
  messageElement.classList.add("mb-2");
  messageElement.innerHTML = `<p class="bg-gray-200 text-gray-700 rounded-lg py-2 px-4 inline-block">${message}</p>`;
  chatbox.appendChild(messageElement);
  chatbox.scrollTop = chatbox.scrollHeight;
}

async function respondToUser(userMessage) {
  let message = { prompt: userMessage };
  await getAnswer(
    message,
    (response) => {
      console.log(response);
      addBotMessage(response.data);
    },
    (error) => {
      console.log(error);
    }
  );
}
</script>

<style scoped></style>
