package com.company.books.backend.controllers;

import com.company.books.backend.request.AuthRequest;
import com.company.books.backend.response.TokenResponse;
import com.company.books.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getContrasenia())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsuario());
        final String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
