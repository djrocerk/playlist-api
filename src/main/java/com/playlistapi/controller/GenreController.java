package com.playlistapi.controller;

import com.playlistapi.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final SpotifyService spotifyService;

    @Autowired
    public GenreController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getGenres() {
        try {
            List<String> genres = spotifyService.getGenres();
            return ResponseEntity.ok(genres);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
