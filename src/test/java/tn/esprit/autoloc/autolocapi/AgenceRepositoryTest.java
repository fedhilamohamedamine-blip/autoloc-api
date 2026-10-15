package tn.esprit.autoloc.autolocapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.repository.AgenceRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AgenceRepositoryTest {

    @Autowired
    private AgenceRepository agenceRepository;

    @Test
    void testSaveAgence() {

        Agence agence = new Agence();

        agence.setNom("Agence Tunis");
        agence.setVille("Tunis");
        agence.setAdresse("Centre Ville");
        agence.setTelephone("71123456");

        Agence agenceSaved = agenceRepository.save(agence);

        System.out.println("ID : " + agenceSaved.getIdAgence());
        System.out.println("Nom : " + agenceSaved.getNom());
        System.out.println("Ville : " + agenceSaved.getVille());

        assertNotNull(agenceSaved.getIdAgence());
    }
}
