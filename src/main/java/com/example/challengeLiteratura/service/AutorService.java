package com.example.challengeLiteratura.service;

import com.example.challengeLiteratura.dto.PersonaDTO;
import com.example.challengeLiteratura.model.Autor;
import com.example.challengeLiteratura.repository.AutorRepository;
import com.example.challengeLiteratura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LibroRepository libroRepository;
    public void guardarAutor(PersonaDTO autor){
        Autor autorBuscado = autorRepository.findBynombre(autor.nombre().toString())
                        .orElseGet(()-> {
                            return autorRepository.save(new Autor(autor));
                        });



        System.out.println("Autor verificaco/registrado correctamente");
    }
}
