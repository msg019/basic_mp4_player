import { createRouter, createWebHistory } from "vue-router";
import Video from "../components/Video.vue";
import AllVideos from "../components/AllVideos.vue";


const routes=[
      {path:"/", component: AllVideos},
      {path: "/:name", component: Video},
]

const router= createRouter({
      history: createWebHistory(),
      routes,
})

export default router

