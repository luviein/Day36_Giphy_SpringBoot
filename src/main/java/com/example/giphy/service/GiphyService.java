package com.example.giphy.service;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.giphy.model.Giphy;

@Service
public class GiphyService {
    @Value("${giphy.url}")
    private String giphyUrl;

    @Value("${giphy.api.key}")
    private String giphyAPI;

    public List<String> getGif(String query) throws IOException {
        String gifUrl = UriComponentsBuilder.fromUriString(giphyUrl)
                    .queryParam("api_key", giphyAPI)
                    .queryParam("q", query.replaceAll(" ", "+"))
                    .queryParam("limit","10") 
                    // .queryParam("offset","0") 
                    // .queryParam("rating", "g") 
                    // .queryParam("lang", "en") 
                    // .queryParam("bundle", "messaging_non_clips") 
                    .toUriString();

        System.out.println("GIPHY URL: " + gifUrl);

        //Rest Template makes HTTP requests to external APIs
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> res = template.getForEntity(gifUrl, String.class);

        return Giphy.getUrl(res.getBody());

}
}
