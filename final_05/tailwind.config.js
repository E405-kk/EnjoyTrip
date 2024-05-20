/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  theme: {
    extend: {},
  },
  plugins: [
    require("tailwindcss-scrollbar"),
    function ({ addUtilities }) {
      addUtilities({
        ".scrollbar::-webkit-scrollbar": {
          width: "8px", // 스크롤바 너비 설정
        },
        ".scrollbar::-webkit-scrollbar-thumb": {
          backgroundColor: "white",
          boxShadow: "inset 0 0 3px rgba(0, 0, 0, 0.2)", // 스크롤바의 thumb(막대) 색상 설정
        },
      });
    },
  ],
};
