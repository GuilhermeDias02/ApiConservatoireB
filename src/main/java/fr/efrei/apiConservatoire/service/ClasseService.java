package fr.efrei.apiConservatoire.service;

import fr.efrei.apiConservatoire.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClasseService {
    private final ClasseRepository repository;

    @Autowired
    public ClasseService(ClasseRepository repository){
        this.repository = repository;
    }
}
