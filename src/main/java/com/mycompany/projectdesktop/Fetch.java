package com.mycompany.projectdesktop;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;

public final class Fetch {
    private static JsonNode fetchJson(String url) throws MalformedURLException, IOException {
        URL urlHandle = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)urlHandle.openConnection();
        
        // connection.setRequestMethod("GET");
        connection.setRequestProperty("accept", "application/json");
        
        InputStream responseStream = connection.getInputStream();
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseStream);

        return root;
    }
    
    private static ArrayList<String> countries;
    
    public static String randomCountry() {
        if (countries == null) {
            try {
                countries = new ArrayList();
                
                JsonNode root = fetchJson("https://restcountries.com/v3.1/all?fields=name");                
                for (JsonNode node : root) {
                    countries.add(node.get("name").get("common").asText());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
        int index = new Random().nextInt(countries.size());
        String country = countries.get(index);
        
        return country;
    }
    
    public static Image randomImage(String topic) {
        return new Image("https://source.unsplash.com/random/?" + topic);
    }
    
    public static String bacon() {
        try {
            JsonNode root = fetchJson("https://baconipsum.com/api/?type=meat-and-filler&paras=1");
            String bacon = root.get(0).asText();
            
            return bacon;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
