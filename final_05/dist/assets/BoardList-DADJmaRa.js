import{h as E,b as a,c,e,t as r,i as m,g as S,j as k,x as C,y as T,z as A,r as n,o as D,k as V,m as P,v as L,n as O,L as R,F as B,p as N,S as j,f as $}from"./index-BWaCr9_N.js";import{l as K}from"./board-SuPVdP9M.js";import{_ as M}from"./VSelect-OShSSaR7.js";import{_ as F}from"./PageNavigation-DaPBi4z-.js";const U={class:"border-b border-blue-gray-200"},z={class:"py-3 px-4"},Z={class:"py-3 px-4"},H={class:"py-3 px-4"},Q={class:"py-3 px-4"},q={class:"py-3 px-4"},Y={__name:"BoardListItem",props:{article:Object},setup(s){return(h,p)=>{const u=E("router-link");return a(),c("tr",U,[e("td",z,r(s.article.articleNo),1),e("td",Z,[m(u,{to:{name:"article-view",params:{articleno:s.article.articleNo}},class:"article-title link-dark"},{default:S(()=>[k(r(s.article.subject),1)]),_:1},8,["to"])]),e("td",H,r(s.article.userId),1),e("td",Q,r(s.article.hit),1),e("td",q,r(s.article.registerTime),1)])}}};var G={VITE_OPEN_API_SERVICE_KEY:"tSOC+TAwUD96Av30HSXD4Sy4Oo/6wkDzPHwtj+DQq/oH+hSIDB1fDJZ1kDvq7QccD2MNB/f0U3As6nbTex+FoQ==",VITE_KAKAO_MAP_SERVICE_KEY:"d234049397979f6f3e7cf5e308e8acea",VITE_VUE_API_URL:"http://localhost:",VITE_SEND_BIRD_APP_ID:"048C350C-CE27-4FFF-82CC-A79585913428",VITE_ARTICLE_LIST_SIZE:"9",VITE_ARTICLE_NAVIGATION_SIZE:"10",BASE_URL:"/",MODE:"production",DEV:!1,PROD:!0,SSR:!1};const J={class:"fluid-container mb-20"},W={class:"row justify-content-center"},X={class:"flex items-center justify-center"},ee={class:"mr-40 ml-auto"},te={class:"flex items-center max-w-md mx-auto bg-white rounded-lg","x-data":"{ search: '' }"},se={class:"w-full"},oe=["disabled"],ae=e("svg",{class:"w-5 h-5",fill:"none",stroke:"currentColor",viewBox:"0 0 24 24",xmlns:"http://www.w3.org/2000/svg"},[e("path",{"stroke-linecap":"round","stroke-linejoin":"round","stroke-width":"2",d:"M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"})],-1),le=[ae],re={key:0,class:"text-center text-xl my-10"},ne={key:1},ce={class:"fluid-container mx-40 my-10"},ie={class:"overflow-x-auto"},de={class:"min-w-full bg-white shadow-md rounded-xl"},ue=e("thead",null,[e("tr",{class:"bg-emerald-100 text-gray-700"},[e("th",{class:"py-3 px-4 text-left"},"글번호"),e("th",{class:"py-3 px-4 text-left"},"제목"),e("th",{class:"py-3 px-4 text-left"},"작성자"),e("th",{class:"py-3 px-4 text-left"},"조회수"),e("th",{class:"py-3 px-4 text-left"},"작성일")])],-1),_e={class:"text-blue-gray-900"},xe={__name:"BoardList",setup(s){const h=C(),{userInfo:p}=T(h),u=p.value.userId,f=A(),g=n([{text:"검색 조건",value:""},{text:"글번호",value:"article_no"},{text:"제목",value:"subject"},{text:"작성자아이디",value:"user_id"}]),_=n([]),i=n(1),x=n(0),{VITE_ARTICLE_LIST_SIZE:y}=G,o=n({pgno:i.value,spp:y,key:"",word:""});D(()=>{l()});const w=t=>{o.value.key=t},l=()=>{K(o.value,({data:t})=>{_.value=t.articles,i.value=t.currentPage,x.value=t.totalPageCount},t=>{console.log(t)})},b=t=>{i.value=t,o.value.pgno=t,l()},I=()=>{u?f.push({name:"article-write"}):(j.fire("로그인이 필요한 페이지입니다!"),f.push({name:"user-login"}))};return(t,v)=>(a(),c("div",J,[e("div",W,[e("div",X,[e("div",{class:"ml-40 mr-auto"},[e("button",{type:"button",onClick:I,class:"bg-white text-emerald-600 font-semibold py-2 px-4 border border-emerald-600 rounded-md shadow-md hover:bg-emerald-600 hover:text-white focus:outline-none focus:ring-2 focus:ring-emerald-600 focus:ring-opacity-50"}," 글쓰기 ")]),e("div",ee,[e("form",{class:"flex",onSubmit:V(l,["prevent"])},[m(M,{selectOption:g.value,onOnKeySelect:w},null,8,["selectOption"]),e("div",te,[e("div",se,[P(e("input",{type:"search",class:"w-full px-4 py-1 text-gray-800 rounded-full focus:outline-none",placeholder:"search",onKeyup:O(l,["enter"]),"x-model":"search","onUpdate:modelValue":v[0]||(v[0]=d=>o.value.word=d)},null,544),[[L,o.value.word]])]),e("div",null,[e("button",{type:"button",class:R(["flex items-center bg-emerald-500 justify-center w-12 h-12 text-white rounded-r-lg",o.value.word.length>0?"bg-emerald-500":"bg-gray-500 cursor-not-allowed"]),disabled:o.value.word.length==0,onClick:l},le,10,oe)])])],32)])]),_.value.length===0?(a(),c("div",re," 작성된 글이 없습니다. ")):(a(),c("div",ne,[e("div",ce,[e("div",ie,[e("table",de,[ue,e("tbody",_e,[(a(!0),c(B,null,N(_.value,d=>(a(),$(Y,{key:d.articleNo,article:d},null,8,["article"]))),128))])])])]),m(F,{"current-page":i.value,"total-page":x.value,onPageChange:b},null,8,["current-page","total-page"])]))])]))}};export{xe as default};