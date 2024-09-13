package com.playlistapi.controller;

import com.playlistapi.entity.Playlist;
import com.playlistapi.repository.PlaylistRepository;
import com.playlistapi.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
@AllArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        if (playlist.getNombre() == null || playlist.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Playlist savedPlaylist = playlistService.createPlaylist(playlist);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlaylist);
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        List<Playlist> playlists = playlistService.getAllPlaylists();
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{listName}")
    public ResponseEntity<Playlist> getPlaylistByName(@PathVariable String listName) {
        Playlist playlist = playlistService.getPlaylistByName(listName);
        if (playlist == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(playlist);
    }

    @DeleteMapping("/{listName}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable String listName) {
        Playlist playlist = playlistService.getPlaylistByName(listName);
        if (playlist == null) {
            return ResponseEntity.notFound().build();
        }
        playlistService.deletePlaylist(listName);
        return ResponseEntity.noContent().build();
    }
}
