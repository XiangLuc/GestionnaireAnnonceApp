package org.univparis8.iut.montreuil.gestionnaireannonce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<Annonce>> getListAnnonces() {
        return ResponseEntity.ok(annonceImpl.getListAnnonces());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addAnnonce(@RequestBody Annonce annonce) {
        try {
            annonceImpl.addAnnonce(annonce);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Annonce créer avec succès.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAnnonceById(@PathVariable int id) {
        try {
            annonceImpl.deleteAnnonce(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Annonce supprimer avec succès.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
