package tn.esprit.autoloc.autolocapi.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.repository.EmployeRepository;
import tn.esprit.autoloc.autolocapi.service.EmployeService;

@RestController
@RequestMapping("/employes")
public class EmployeController {

    private final EmployeRepository employeRepository;
    private final EmployeService employeService;

    public EmployeController(EmployeRepository employeRepository, EmployeService employeService) {
        this.employeRepository = employeRepository;
        this.employeService = employeService;
    }

    @GetMapping
    public List<Employe> getAll() {
        return employeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getById(@PathVariable Long id) {
        return employeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Employe create(@RequestBody Employe employe) {
        return employeRepository.save(employe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> update(@PathVariable Long id, @RequestBody Employe employe) {
        if (!employeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        employe.setIdEmploye(id);
        return ResponseEntity.ok(employeRepository.save(employe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!employeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        employeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/desaffecter")
    public Employe desaffecter(@PathVariable("id") Long idEmploye) {
        return employeService.desaffecterEmploye(idEmploye);
    }

    @PutMapping("/{id}/affecter/{idAgence}")
    public Employe affecter(
            @PathVariable("id") Long idEmploye,
            @PathVariable Long idAgence) {
        return employeService.affecterEmploye(idEmploye, idAgence);
    }
}
