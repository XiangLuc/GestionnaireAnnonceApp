package org.univparis8.iut.montreuil.gestionnaireannonce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class GestionnaireAnnonceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionnaireAnnonceApplication.class, args);
    }

}
