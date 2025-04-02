package org.univparis8.iut.montreuil.gestionnaireannonce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getUserInfo(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        if (authentication != null && authentication.isAuthenticated()) {
            response.put("username", authentication.getName());
            response.put("role", authentication.getAuthorities());
        } else {
            response.put("message", "Utilisateur non authentifié");
        }
        return ResponseEntity.ok(response);
    }
}