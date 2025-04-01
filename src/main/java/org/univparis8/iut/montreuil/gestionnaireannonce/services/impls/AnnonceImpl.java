package org.univparis8.iut.montreuil.gestionnaireannonce.services.impls;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.univparis8.iut.montreuil.gestionnaireannonce.entities.Annonce;
import org.univparis8.iut.montreuil.gestionnaireannonce.repositories.AnnonceRepository;
import org.univparis8.iut.montreuil.gestionnaireannonce.services.interfaces.IAnnonce;

import java.util.List;

@Service
public class AnnonceImpl implements IAnnonce {

    private final AnnonceRepository annonceRepository;

    @Autowired
    public AnnonceImpl(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    @Override
    public List<Annonce> getListAnnonces() {
        return annonceRepository.findAll();
    }

    @Override
    public Annonce getAnnonceById(int id) {
        return annonceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Annonce non trouvée avec l'ID: " + id));
    }

    @Transactional
    @Override
    public void addAnnonce(Annonce annonce) {
        if (annonce == null) {
            throw new IllegalArgumentException("L'annonce ne peut pas être nulle");
        }
        annonceRepository.save(annonce);
    }

    @Transactional
    @Override
    public void deleteAnnonce(int id) {
        try {
            annonceRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Aucune annonce trouvée avec l'ID: " + id, e);
        }
    }
}
