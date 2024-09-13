package com.playlistapi.controller;

import com.playlistapi.dto.LoginRequest;
import com.playlistapi.dto.LoginResponse;
import com.playlistapi.entity.Usuario;
import com.playlistapi.service.AuthService;
import com.playlistapi.service.UsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsersDetailsService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody Usuario request) {
        Usuario user = authService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
