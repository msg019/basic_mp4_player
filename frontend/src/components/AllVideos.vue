<script setup lang="ts">

      import { onMounted, reactive } from 'vue';
      import { RouterLink } from 'vue-router';

      const videos=reactive<string[]>([])

      const listVideos=async()=>{
            const fechtData= await fetch('http://localhost:8080/api/videos');

            const data = await fechtData.json()
            videos.push(...data)
      }

      onMounted(()=>{
            listVideos()
      })

</script>


<template>
      <h1>Videos</h1>
      <ul v-if="videos.length">
            <li v-for="video in videos">
                  <p>{{ video }}</p> 
                  <RouterLink class="btn" :to=video >Watch</RouterLink>
            </li>
      </ul>
    
</template>

<style>

      ul{
            list-style: none;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap:1rem;

            &> li{
                  display: grid;
                  grid-template-columns: repeat(2,1fr);
                  align-items: center;
            }
      }


      .btn{
            border: 1px solid #D1E7D7;
            border-radius: 12px;
            width: 5rem;
            height: 2rem;
            margin-left: 2rem;

            display: flex;
            align-items: center;
            justify-content: center;

            color:#D1E7D7;
            text-decoration: none;

            &:hover{
                  background-color: #D1E7D7;
                  color: #529fb0;
            }
      }

</style>