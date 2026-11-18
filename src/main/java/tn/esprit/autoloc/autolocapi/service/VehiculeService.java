package tn.esprit.autoloc.autolocapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.AgenceRepository;
import tn.esprit.autoloc.autolocapi.repository.EquipementRepository;
import tn.esprit.autoloc.autolocapi.repository.VehiculeRepository;
import tn.esprit.autoloc.autolocapi.validation.VehiculeValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final EquipementRepository equipementRepository;
    private final VehiculeValidator vehiculeValidator;
    private final JournalisationService journalisationService;

    public VehiculeService(
            VehiculeRepository vehiculeRepository,
            AgenceRepository agenceRepository,
            EquipementRepository equipementRepository,
            VehiculeValidator vehiculeValidator,
            JournalisationService journalisationService) {
        this.vehiculeRepository = vehiculeRepository;
        this.agenceRepository = agenceRepository;
        this.equipementRepository = equipementRepository;
        this.vehiculeValidator = vehiculeValidator;
        this.journalisationService = journalisationService;
    }

    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    public Optional<Vehicule> findById(Long id) {
        return vehiculeRepository.findById(id);
    }

    public Vehicule save(Vehicule vehicule) {
        vehiculeValidator.valider(vehicule);
        Vehicule vehiculeEnregistre = vehiculeRepository.save(vehicule);
        journalisationService.journaliserEnregistrementVehicule(vehiculeEnregistre.getIdVehicule());
        return vehiculeEnregistre;
    }

    public void deleteById(Long id) {
        vehiculeRepository.deleteById(id);
        journalisationService.journaliserSuppressionVehicule(id);
    }

    public boolean existsById(Long id) {
        return vehiculeRepository.existsById(id);
    }

    @Transactional
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Véhicule introuvable"));
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agence introuvable"));
        vehicule.setAgence(agence);
        return vehiculeRepository.save(vehicule);
    }

    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = findVehicule(idVehicule);
        Equipement equipement = findEquipement(idEquipement);
        if (vehicule.getEquipements() == null) {
            vehicule.setEquipements(new ArrayList<>());
        }
        boolean dejaAffecte = vehicule.getEquipements().stream()
                .anyMatch(e -> idEquipement.equals(e.getIdEquipement()));
        if (!dejaAffecte) {
            vehicule.getEquipements().add(equipement);
        }
        return vehiculeRepository.save(vehicule);
    }

    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = findVehicule(idVehicule);
        findEquipement(idEquipement);
        if (vehicule.getEquipements() != null) {
            vehicule.getEquipements().removeIf(e -> idEquipement.equals(e.getIdEquipement()));
        }
        return vehiculeRepository.save(vehicule);
    }

    private Vehicule findVehicule(Long idVehicule) {
        return vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Véhicule introuvable"));
    }

    private Equipement findEquipement(Long idEquipement) {
        return equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Équipement introuvable"));
    }
}
