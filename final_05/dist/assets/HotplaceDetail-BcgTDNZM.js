import{x as T,y as D,N as F,z as A,r as h,w as G,o as N,c as n,e,t as m,u as v,F as k,K as r,S as g,b as l}from"./index-BWaCr9_N.js";import{d as R,c as z,u as E,a as K}from"./hotplace-B_1Sabb8.js";const H={class:"container mx-auto p-5"},J=e("div",{id:"map",class:"mx-auto",style:{width:"80%",height:"300px"}},null,-1),O={class:"flex justify-center mx-auto",style:{width:"80%"}},U={style:{width:"60%",height:"auto","aspect-ratio":"3/4","max-width":"100%"}},q=["src"],P={class:"w-full text-start bg-white shadow-md rounded-lg p-5"},Z={class:"flex"},$={class:"flex-1 space-y-4"},Q=e("label",{for:"subject",class:"text-lx"},"제목:",-1),V={id:"subject",class:"mt-2 outline-none py-2 px-2 text-md border-2 rounded-md w-full"},W=e("label",{for:"addr",class:"text-lx"},"주소:",-1),X={id:"addr",class:"mt-2 outline-none py-2 px-2 text-md border-2 rounded-md w-full"},Y=e("label",{for:"visitDate",class:"text-lx"},"방문 날짜:",-1),ee={id:"visitDate",class:"mt-2 outline-none py-2 px-2 text-md border-2 rounded-md w-full"},te=e("label",{for:"content",class:"block mb-2 text-lg"},"내용:",-1),oe={id:"content",class:"w-full p-4 text-gray-600 bg-emerald-50 outline-none rounded-md",style:{height:"200px"}},se=e("div",{class:"border-t my-3"},null,-1),ae={class:"flex justify-between mt-5"},ne={class:"flex"},le=e("i",{class:"fas fa-heart text-lg leading-none"},null,-1),ie=[le],re=e("i",{class:"fas fa-heart text-lg leading-none"},null,-1),de=[re],ce={class:"flex"},ue=e("link",{rel:"stylesheet",href:"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css",integrity:"sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==",crossorigin:"anonymous"},null,-1),he={__name:"HotplaceDetail",setup(me){const w=T(),{userInfo:C}=D(w),d=C.value.userId,I=F(),x=A(),{articleno:c}=I.params,o=h({}),a=h(!1),i=h({userId:d,articleNo:c,check:""});var u,p;const b=()=>{const t=document.getElementById("map"),s={center:new kakao.maps.LatLng(36.386120817951436,128.02537185582057),level:8};u=new kakao.maps.Map(t,s),p=new kakao.maps.Marker({position:u.getCenter()}),p.setMap(u)};G(o,t=>{if(t.latitude&&t.longitude){const s=new kakao.maps.LatLng(t.latitude,t.longitude);u.setCenter(s),p.setPosition(s)}}),N(()=>{if(j(),B(),window.kakao&&window.kakao.maps)b();else{const t=document.createElement("script");t.src="//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=d234049397979f6f3e7cf5e308e8acea&libraries=services,clusterer",t.onload=()=>kakao.maps.load(()=>b()),document.head.appendChild(t)}});const j=()=>{R(c,({data:t})=>{o.value=t},t=>{console.log(t)})},B=()=>{z(i.value,({data:t})=>{a.value=t},t=>{console.log(t)})},_=()=>{a.value?i.value.check="":i.value.check="plus",console.log(i.value),E(i.value,()=>{a.value=!a.value},t=>{console.log(t)})};function y(){x.push({name:"hotplace-list"})}function L(){x.push({name:"hotplace-modify",params:{articleno:c}})}function M(){g.fire({title:"게시글을 삭제하시겠습니까?",icon:"warning",showCancelButton:!0,confirmButtonColor:"#d33",cancelButtonColor:"#848484",confirmButtonText:"삭제",cancelButtonText:"취소"}).then(t=>{t.isConfirmed&&K(c,s=>{let f=s.data;s.status==200?(f="게시글이 삭제되었습니다.",g.fire({title:"삭제",text:f,icon:"success"}),y()):g.fire({icon:"error",text:f})},s=>{console.log(s)})})}function S(){if(o.value.fileInfo==null)return null;if(o.value.fileInfo.saveFile){var t="/src/assets/upload/";return t+=o.value.fileInfo.saveFolder+"/"+o.value.fileInfo.saveFile,t}else return"/src/assets/about-bg.jpg"}return(t,s)=>(l(),n(k,null,[e("div",H,[J,e("div",O,[e("div",U,[e("img",{src:S(),class:"max-w-full h-auto p-5",style:{width:"100%",height:"100%","object-fit":"cover"}},null,8,q)]),e("div",P,[e("div",Z,[e("div",$,[e("div",null,[Q,e("div",V,m(o.value.subject),1)]),e("div",null,[W,e("div",X,m(o.value.addr),1)]),e("div",null,[Y,e("div",ee,m(o.value.visitDate),1)]),e("div",null,[te,e("div",oe,m(o.value.content),1)])])]),se,e("div",ae,[e("div",ne,[e("button",{type:"button",class:"bg-gray-100 text-gray-700 font-semibold py-2 px-4 mr-3 border border-gray-300 rounded-md shadow-sm hover:bg-gray-200 hover:text-gray-800 focus:outline-none focus:ring-2 focus:ring-gray-300 focus:ring-opacity-50",onClick:y}," 글목록 "),v(d)?(l(),n(k,{key:0},[a.value?(l(),n("button",{key:0,onClick:_,class:"middle none center mr-3 flex items-center justify-center rounded-lg bg-gradient-to-tr from-pink-600 to-pink-400 p-3 font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none","data-ripple-light":"true"},ie)):r("",!0),a.value?r("",!0):(l(),n("button",{key:1,onClick:_,class:"middle none center mr-3 flex items-center justify-center rounded-lg border border-pink-500 p-3 font-sans text-xs font-bold uppercase text-pink-500 transition-all hover:opacity-75 focus:ring focus:ring-pink-200 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none","data-ripple-dark":"true"},de))],64)):r("",!0)]),e("div",ce,[v(d)===o.value.userId?(l(),n("button",{key:0,type:"button",class:"mr-3 bg-emerald-100 text-emerald-700 font-semibold py-2 px-4 border border-emerald-300 rounded-md shadow-sm hover:bg-emerald-200 hover:text-emerald-800 focus:outline-none focus:ring-2 focus:ring-emerald-300 focus:ring-opacity-50",onClick:L}," 글수정 ")):r("",!0),v(d)===o.value.userId?(l(),n("button",{key:1,type:"button",class:"bg-red-100 text-red-700 font-semibold py-2 px-4 border border-red-300 rounded-md shadow-sm hover:bg-red-200 hover:text-red-800 focus:outline-none focus:ring-2 focus:ring-red-300 focus:ring-opacity-50",onClick:M}," 글삭제 ")):r("",!0)])])])])]),ue],64))}};export{he as default};