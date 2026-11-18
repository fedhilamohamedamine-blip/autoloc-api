package tn.esprit.autoloc.autolocapi.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.repository.AgenceRepository;

@RestController
@RequestMapping("/agences")
public class AgenceController {

    @Autowired
    private AgenceRepository agenceRepository;

    @GetMapping
    public List<Agence> getAll() {
        return agenceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> getById(@PathVariable Long id) {
        return agenceRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Agence create(@RequestBody Agence agence) {
        return agenceRepository.save(agence);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agence> update(@PathVariable Long id, @RequestBody Agence agence) {
        if (!agenceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        agence.setIdAgence(id);        return ResponseEntity.ok(agenceRepository.save(agence));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!agenceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        agenceRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}