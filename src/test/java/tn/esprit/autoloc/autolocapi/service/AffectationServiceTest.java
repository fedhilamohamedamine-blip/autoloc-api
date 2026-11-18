package tn.esprit.autoloc.autolocapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.AgenceRepository;
import tn.esprit.autoloc.autolocapi.repository.EmployeRepository;
import tn.esprit.autoloc.autolocapi.repository.EquipementRepository;
import tn.esprit.autoloc.autolocapi.repository.VehiculeRepository;
import tn.esprit.autoloc.autolocapi.validation.VehiculeValidator;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AffectationServiceTest {

    @Mock
    private EmployeRepository employeRepository;
    @Mock
    private AgenceRepository agenceRepository;
    @Mock
    private VehiculeRepository vehiculeRepository;
    @Mock
    private EquipementRepository equipementRepository;
    @Mock
    private VehiculeValidator vehiculeValidator;
    @Mock
    private JournalisationService journalisationService;

    private EmployeService employeService;
    private VehiculeService vehiculeService;

    @BeforeEach
    void setUp() {
        employeService = new EmployeService(employeRepository, agenceRepository);
        vehiculeService = new VehiculeService(
                vehiculeRepository,
                agenceRepository,
                equipementRepository,
                vehiculeValidator,
                journalisationService
        );
    }

    @Test
    void transfertEmployeNecessiteUneDesaffectationPrealable() {
        Agence agenceActuelle = new Agence();
        Agence nouvelleAgence = new Agence();
        nouvelleAgence.setIdAgence(2L);
        Employe employe = new Employe();
        employe.setIdEmploye(1L);
        employe.setAgence(agenceActuelle);
        when(employeRepository.findById(1L)).thenReturn(Optional.of(employe));
        when(agenceRepository.findById(2L)).thenReturn(Optional.of(nouvelleAgence));
        when(employeRepository.save(any(Employe.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertThrows(org.springframework.web.server.ResponseStatusException.class,
                () -> employeService.affecterEmploye(1L, 2L));
        verify(agenceRepository, never()).findById(2L);

        assertNull(employeService.desaffecterEmploye(1L).getAgence());
        assertEquals(nouvelleAgence, employeService.affecterEmploye(1L, 2L).getAgence());
    }

    @Test
    void affectationEquipementEstIdempotenteEtPeutEtreRetiree() {
        Vehicule vehicule = new Vehicule();
        vehicule.setIdVehicule(1L);
        vehicule.setEquipements(new ArrayList<>());
        Equipement equipement = new Equipement();
        equipement.setIdEquipement(3L);
        when(vehiculeRepository.findById(1L)).thenReturn(Optional.of(vehicule));
        when(equipementRepository.findById(3L)).thenReturn(Optional.of(equipement));
        when(vehiculeRepository.save(any(Vehicule.class))).thenAnswer(invocation -> invocation.getArgument(0));

        vehiculeService.affecterEquipement(1L, 3L);
        vehiculeService.affecterEquipement(1L, 3L);
        assertEquals(1, vehicule.getEquipements().size());

        vehiculeService.desaffecterEquipement(1L, 3L);
        assertEquals(0, vehicule.getEquipements().size());
    }
}
