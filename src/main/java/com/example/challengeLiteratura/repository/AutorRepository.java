package com.example.challengeLiteratura.repository;

import com.example.challengeLiteratura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long>{
        Optional<Autor> findBynombre(String nombre);
}
