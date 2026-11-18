package tn.esprit.autoloc.autolocapi.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.repository.ContratRepository;

@RestController
@RequestMapping("/contrats")
public class ContratController {

    private final ContratRepository contratRepository;

    public ContratController(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @GetMapping
    public List<Contrat> getAll() {
        return contratRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getById(@PathVariable Long id) {
        return contratRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Contrat create(@RequestBody Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrat> update(@PathVariable Long id, @RequestBody Contrat contrat) {
        if (!contratRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contrat.setIdContrat(id);
        return ResponseEntity.ok(contratRepository.save(contrat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!contratRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contratRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
