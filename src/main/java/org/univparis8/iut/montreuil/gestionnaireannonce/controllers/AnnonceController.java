package org.univparis8.iut.montreuil.gestionnaireannonce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.univparis8.iut.montreuil.gestionnaireannonce.entities.Annonce;
import org.univparis8.iut.montreuil.gestionnaireannonce.services.impls.AnnonceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/annonce")
public class AnnonceController {

    private final AnnonceImpl annonceImpl;

    @Autowired
    public AnnonceController(AnnonceImpl annonceImpl) {
        this.annonceImpl = annonceImpl;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<Annonce>> getListAnnonces() {
        return ResponseEntity.ok(annonceImpl.getListAnnonces());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Map<String, String>> addAnnonce(@RequestBody Annonce annonce) {
        Map<String, String> response = new HashMap<>();
        try {
            if (annonce == null) {
                response.put("message", "Annonce invalide.");
                return ResponseEntity.badRequest().body(response);
            }
            annonceImpl.addAnnonce(annonce);
            response.put("message", "Annonce créée avec succès.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("message", "Erreur lors de la création de l'annonce : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAnnonceById(@PathVariable int id) {
        Map<String, String> response = new HashMap<>();
        try {
            annonceImpl.deleteAnnonce(id);
            response.put("message", "Annonce supprimée avec succès.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("message", "Aucune annonce trouvée avec l'ID : " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("message", "Erreur lors de la suppression de l'annonce : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
