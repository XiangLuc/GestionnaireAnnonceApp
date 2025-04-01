package org.univparis8.iut.montreuil.gestionnaireannonce.configurations;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Before("execution(* org.univparis8.iut.montreuil.gestionnaireannonce.services.*.*(..))")
    public void logBeforeMethod() {
        logger.info("Une méthode du package services est appelée.");
    }

    @AfterReturning("execution(* org.univparis8.iut.montreuil.gestionnaireannonce.services.impls.AnnonceImpl.getListAnnonces(..))")
    public void logAfterMethod() {
        logger.info("La méthode getConges a été exécutée avec succès.");
    }

    @Around("execution(* org.univparis8.iut.montreuil.gestionnaireannonce.controllers.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long duration = System.currentTimeMillis() - start;
        logger.info("Méthode {} exécutée en {} ms", joinPoint.getSignature(), duration);

        return result;
    }
}
