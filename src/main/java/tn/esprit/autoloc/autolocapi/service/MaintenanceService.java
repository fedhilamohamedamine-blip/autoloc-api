package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.repository.MaintenanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    public Optional<Maintenance> findById(Long id) {
        return maintenanceRepository.findById(id);
    }

    public Maintenance save(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    public void deleteById(Long id) {
        maintenanceRepository.deleteById(id);
    }
}
