package com.playlistapi.consumer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpotifyApiConsumer {

    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;
    private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";
    private static final String GENRES_URL = "https://api.spotify.com/v1/recommendations/available-genre-seeds";

    public List<String> getGenres() throws Exception {
        String accessToken = getAccessToken();
        return fetchGenres(accessToken);
    }

    private String getAccessToken() throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(TOKEN_URL);

        String auth = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        httpPost.setHeader("Authorization", "Basic " + encodedAuth);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        StringEntity entity = new StringEntity("grant_type=client_credentials");
        httpPost.setEntity(entity);

        HttpResponse response = httpClient.execute(httpPost);
        String jsonResponse = EntityUtils.toString(response.getEntity());

        JSONObject jsonObject = new JSONObject(jsonResponse);
        return jsonObject.getString("access_token");
    }

    private List<String> fetchGenres(String accessToken) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(GENRES_URL);
        httpGet.setHeader("Authorization", "Bearer " + accessToken);

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String jsonResponse = EntityUtils.toString(entity);

        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray genresArray = jsonObject.getJSONArray("genres");

        return genresArray.toList().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

}
