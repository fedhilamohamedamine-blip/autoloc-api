package tn.esprit.autoloc.autolocapi.validation;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;

import java.math.BigDecimal;

@Component
public class VehiculeValidator {

    public void valider(Vehicule vehicule) {
        if (vehicule == null) {
            throw new IllegalArgumentException("Le véhicule ne peut pas être null.");
        }

        BigDecimal tarifJournalier = vehicule.getTarifJournalier();
        if (tarifJournalier == null || tarifJournalier.signum() <= 0) {
            throw new IllegalArgumentException("Le tarif journalier doit être strictement positif.");
        }
    }
}
