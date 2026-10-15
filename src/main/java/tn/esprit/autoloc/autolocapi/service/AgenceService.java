package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.repository.AgenceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AgenceService {

    private final AgenceRepository agenceRepository;

    public AgenceService(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    public Optional<Agence> findById(Long id) {
        return agenceRepository.findById(id);
    }

    public Agence save(Agence agence) {
        return agenceRepository.save(agence);
    }

    public void deleteById(Long id) {
        agenceRepository.deleteById(id);
    }
}
