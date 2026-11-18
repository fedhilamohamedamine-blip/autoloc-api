package tn.esprit.autoloc.autolocapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JournalisationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JournalisationService.class);

    public void journaliserEnregistrementVehicule(Long idVehicule) {
        LOGGER.info("Véhicule enregistré : id={}", idVehicule);
    }

    public void journaliserSuppressionVehicule(Long idVehicule) {
        LOGGER.info("Véhicule supprimé : id={}", idVehicule);
    }
}
