package com.example.demo.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.demo.Service.VideoService;



@RestController
@RequestMapping("/api")
public class VideoController {

     private final VideoService service;

     public VideoController(VideoService service){
          this.service= service;
     }

     @GetMapping("/videos")
     public ArrayList<String> getAllVideos() throws IOException{
          String videosPath="static/videos";
          Resource videos= new ClassPathResource(videosPath);
          
          ArrayList<String> videoList= new ArrayList<>();

          // Get all files in the directory and add them to the list
          // except the .gitkeep file
          for(String video : videos.getFile().list() ){
               if(!".gitkeep".equals(video)){
                    videoList.add(video);
               }
          }              
          
          videoList.replaceAll(video->{
               return video.replace(".mp4", "");
          });

          return videoList;
     }


      @GetMapping("/stream")     
     public StreamingResponseBody streamVideo(@RequestParam(value="name") String name) throws IOException{

        
               String videoPath="static/videos/"+name+".mp4";

               Resource video=  new ClassPathResource(videoPath);

               if(!video.exists()){
                    return null;
               }

               InputStream videoStream= video.getInputStream();
     
               return (os)->{
                    service.readAndWrite(videoStream, os);
               };
              
        
         
         
          
     }
}
