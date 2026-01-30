package com.example.challengeLiteratura.repository;

import com.example.challengeLiteratura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long>{
        Optional<Autor> findBynombre(String nombre);


    @Query("SELECT a FROM Autor a WHERE a.anio_Nacimiento <=:anio_Final AND a.anio_Muerte >=:anio_Inicio ")
        List<Autor>listarAutoresVivosEnUnPeriodoDeTiempo(int anio_Inicio, int anio_Final);


}
