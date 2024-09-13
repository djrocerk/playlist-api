package com.playlistapi.service;

import com.playlistapi.entity.Playlist;

import java.util.List;

public interface PlaylistService {
    Playlist createPlaylist(Playlist playlist);
    List<Playlist> getAllPlaylists();
    Playlist getPlaylistByName(String name);
    void deletePlaylist(String name);
}
