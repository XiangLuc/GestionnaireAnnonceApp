package org.univparis8.iut.montreuil.gestionnaireannonce.services.interfaces;

import org.springframework.stereotype.Repository;
import org.univparis8.iut.montreuil.gestionnaireannonce.entities.Annonce;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAnnonce {

    List<Annonce> getListAnnonces();
    Optional<Annonce> getAnnonceById(int id);
    void addAnnonce(Annonce annonce);
    void deleteAnnonce(int id);

}
