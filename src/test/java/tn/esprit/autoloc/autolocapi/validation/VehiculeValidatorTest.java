package tn.esprit.autoloc.autolocapi.validation;

import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VehiculeValidatorTest {

    private final VehiculeValidator validator = new VehiculeValidator();

    @Test
    void accepteUnTarifStrictementPositif() {
        Vehicule vehicule = new Vehicule();
        vehicule.setTarifJournalier(new BigDecimal("0.01"));

        assertDoesNotThrow(() -> validator.valider(vehicule));
    }

    @Test
    void refuseUnVehiculeNullEtUnTarifNonPositif() {
        assertThrows(IllegalArgumentException.class, () -> validator.valider(null));

        Vehicule vehicule = new Vehicule();
        assertThrows(IllegalArgumentException.class, () -> validator.valider(vehicule));

        vehicule.setTarifJournalier(BigDecimal.ZERO);
        assertThrows(IllegalArgumentException.class, () -> validator.valider(vehicule));

        vehicule.setTarifJournalier(new BigDecimal("-1"));
        assertThrows(IllegalArgumentException.class, () -> validator.valider(vehicule));
    }
}
