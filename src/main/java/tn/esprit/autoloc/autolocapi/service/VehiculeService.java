package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.VehiculeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    public VehiculeService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    public Optional<Vehicule> findById(Long id) {
        return vehiculeRepository.findById(id);
    }

    public Vehicule save(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public void deleteById(Long id) {
        vehiculeRepository.deleteById(id);
    }
}
