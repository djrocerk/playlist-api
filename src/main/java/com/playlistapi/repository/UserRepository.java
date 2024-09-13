package com.playlistapi.repository;

import com.playlistapi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, String> {
    Usuario findByUsername(String username);
}
