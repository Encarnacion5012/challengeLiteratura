package com.example.challengeLiteratura.repository;

import com.example.challengeLiteratura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findBytitulo(String nombre);

    List<Libro>findBylenguajes(String lenguaje);
}
