package tn.esprit.autoloc.autolocapi.service.tarification;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TarificationServiceTest {

    private final TarificationService tarificationService = new TarificationService(Map.of(
            "standard", new TarificationStandard(),
            "weekend", new TarificationWeekend(),
            "longueduree", new TarificationLongueDuree(),
            "entreprise", new TarificationEntreprise()
    ));

    @Test
    void calculeLeMontantPourChaqueStrategie() {
        assertEquals(300.0, tarificationService.calculer("standard", 100.0, 3), 0.0001);
        assertEquals(270.0, tarificationService.calculer("weekend", 100.0, 3), 0.0001);
        assertEquals(240.0, tarificationService.calculer("longueDuree", 100.0, 3), 0.0001);
        assertEquals(255.0, tarificationService.calculer("entreprise", 100.0, 3), 0.0001);
    }

    @Test
    void normaliseLaCleDeLaPolitique() {
        assertEquals(255.0, tarificationService.calculer(" Entreprise ", 100.0, 3), 0.0001);
    }

    @Test
    void refuseLesEntreesInvalidesEtLesPolitiquesInconnues() {
        assertThrows(IllegalArgumentException.class,
                () -> tarificationService.calculer("standard", 0.0, 3));
        assertThrows(IllegalArgumentException.class,
                () -> tarificationService.calculer("standard", 100.0, 0));
        assertThrows(IllegalArgumentException.class,
                () -> tarificationService.calculer("inconnue", 100.0, 3));
    }
}
