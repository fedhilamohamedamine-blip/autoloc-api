package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.repository.EmployeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {

    private final EmployeRepository employeRepository;

    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    public Optional<Employe> findById(Long id) {
        return employeRepository.findById(id);
    }

    public Employe save(Employe employe) {
        return employeRepository.save(employe);
    }

    public void deleteById(Long id) {
        employeRepository.deleteById(id);
    }
}
