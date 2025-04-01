package org.univparis8.iut.montreuil.gestionnaireannonce.services.impls;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.univparis8.iut.montreuil.gestionnaireannonce.entities.Annonce;
import org.univparis8.iut.montreuil.gestionnaireannonce.repositories.AnnonceRepository;
import org.univparis8.iut.montreuil.gestionnaireannonce.services.interfaces.IAnnonce;

import java.util.List;
import java.util.Optional;

@Service
public class AnnonceImpl implements IAnnonce {

    private final AnnonceRepository annonceRepository;

    @Autowired
    public AnnonceImpl(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    @PreAuthorize("hasRole('ADMIN')or('USER')")
    @Override
    public List<Annonce> getListAnnonces() {
        return annonceRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')or('USER')")
    @Override
    public Optional<Annonce> getAnnonceById(int id) {
        return annonceRepository.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public void addAnnonce(Annonce annonce) {
        annonceRepository.save(annonce);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAnnonce(int id) {
        annonceRepository.deleteById(id);
    }
}
