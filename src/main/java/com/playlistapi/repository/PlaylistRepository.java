package com.playlistapi.repository;

import com.playlistapi.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findByNombre(String nombre);
    void deleteByNombre(String nombre);
}
