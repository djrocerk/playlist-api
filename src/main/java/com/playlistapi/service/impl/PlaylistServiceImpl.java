package com.playlistapi.service.impl;

import com.playlistapi.entity.Playlist;
import com.playlistapi.repository.PlaylistRepository;
import com.playlistapi.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistRepository playlistRepository;

    @Override
    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    public Playlist getPlaylistByName(String name) {
        return playlistRepository.findByNombre(name)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));
    }

    @Override
    public void deletePlaylist(String name) {
        Playlist playlist = getPlaylistByName(name);
        if (playlist != null) {
            playlistRepository.delete(playlist);
        }
    }
}
