package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.repository.ContratRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContratService {

    private final ContratRepository contratRepository;

    public ContratService(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    public Optional<Contrat> findById(Long id) {
        return contratRepository.findById(id);
    }

    public Contrat save(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    public void deleteById(Long id) {
        contratRepository.deleteById(id);
    }
}
