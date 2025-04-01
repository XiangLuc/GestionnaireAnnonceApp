package org.univparis8.iut.montreuil.gestionnaireannonce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.univparis8.iut.montreuil.gestionnaireannonce.entities.Annonce;

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {
}
