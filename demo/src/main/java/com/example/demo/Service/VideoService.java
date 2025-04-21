package com.example.demo.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Service;

@Service
public class VideoService {

      public void readAndWrite(InputStream is, OutputStream os) throws IOException{
          try (os; is) {
              byte [] data = new byte[1024];
              int read;
              
              while((read= is.read(data))>0){
                  os.write(data,0,read);
                  os.flush();
              }
          }
      }
}
