package com.example.giphy.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.giphy.service.GiphyService;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin()
public class GiphyController {

    @Autowired
    private GiphyService svc;
    
    @GetMapping(path="/gif")
    public ResponseEntity<List<String>> getGif(@RequestParam(required=true) String q) throws IOException{
        


        //.get() retrieves value from Optional
        return ResponseEntity.ok(svc.getGif(q));
        
    }
}
