package com.playlistapi.service;

import com.playlistapi.consumer.SpotifyApiConsumer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotifyService {
    private final SpotifyApiConsumer spotifyApiConsumer;

    public SpotifyService(SpotifyApiConsumer spotifyApiConsumer) {
        this.spotifyApiConsumer = spotifyApiConsumer;
    }

    public List<String> getGenres() throws Exception {
        return spotifyApiConsumer.getGenres();
    }
}
