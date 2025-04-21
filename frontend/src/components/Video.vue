<script lang="ts" setup>
      import { onMounted, ref } from 'vue';
      import { useRoute } from 'vue-router';

      const videoUrl= ref<string>('')
      const name=useRoute().params.name as string

      //To manage possible not found video
      const data= ref<Blob>(new Blob([]));

      const loadVideo=async(name:string)=>{
            try {
                  const fechtData= await fetch(`http://localhost:8080/api/stream?name=${name}`);
                  data.value = await fechtData.blob()

                  const blob= new Blob([data.value], { type: 'video/mp4' });

                  videoUrl.value= URL.createObjectURL(blob)

            }catch (error){
                  console.error('Error fetching video');
            }
           
      }

      onMounted(async()=>{
           await loadVideo(name)
      })
      
</script>

<template>

      <RouterLink class="back-btn" to="/">Back</RouterLink>

      <h1 style="color: #D1E7D7;">{{ $route.params.name }}</h1>

      <div v-if="videoUrl">
            <video controls autoplay v-if="data.size>0">
                  <source :src="videoUrl" type="video/mp4" >
            </video>
            <div v-else>
                  <h1 style="color: #D1E7D7;">Video not found</h1>
            </div>
      </div>
      <div v-else>
            <div class="spinner-container">
                  <div class="spinner"></div>
            </div>
      </div>
</template>

<style>
      video{
            width: 50%;
            height: 50%;
      }

      .back-btn{
            position: absolute;
            top:2rem;
            left: 2rem;
            border: 1px solid #D1E7D7;
            border-radius: 12px;
            width: 3rem;

            color:#D1E7D7;
            text-decoration: none;

            &:hover{
                  background-color: #D1E7D7;
                  color: #529fb0;
            }

      }

      .spinner-container {
            display: flex;
            height: 50vh;
            align-items: center;
            justify-content: center;
      }

    
      .spinner {
            border: 8px solid #D1E7D7;
            border-top: 8px solid #529fb0;
            border-radius: 50%;
            height: 12rem;
            width: 12rem;
            margin-right: 0.75rem;
            animation: spin 1s linear infinite;
      }

      @keyframes spin {
            0% {
                  transform: rotate(0deg);
            }
            100% {
                  transform: rotate(360deg);
            }
      }     
</style>