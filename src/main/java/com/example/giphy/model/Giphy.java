package com.example.giphy.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Giphy implements Serializable {


    public static List<String> getUrl (String json) throws IOException {
        List<String> urls = new ArrayList<>();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            // create a json reader to read bytes from json string
            JsonReader r = Json.createReader(is);
            // puts read data from JsonReader into JsonObject
            JsonObject o = r.readObject();
            JsonArray dataArray = o.getJsonArray("data");
            if (dataArray != null) {
            dataArray.getValuesAs(JsonObject.class)
                .forEach(obj -> {
                    JsonObject fixedHeight = obj
                        .getJsonObject("images")
                        .getJsonObject("fixed_height");
                        
                    String url = fixedHeight.getString("url");
                    urls.add(url);

                });
        }
        } catch (Exception e) {
            // 400 - 500
            e.printStackTrace();
        }
        
        return urls;
    } 


}
