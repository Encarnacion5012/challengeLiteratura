package com.example.challengeLiteratura.model;

import com.example.challengeLiteratura.dto.PersonaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString

@Entity
@Table(name = "autores")

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int anio_Nacimiento;
     private int anio_Muerte;
     private String nombre;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     List<Libro> libros;

    public Autor(PersonaDTO pDTO){
        this.nombre = pDTO.nombre();
        this.anio_Muerte = pDTO.anio_Muerte();
        this.anio_Nacimiento = pDTO.anio_Nacimiento();
    }

    public void agregarUnlibro(Libro libro){
        if (this.libros == null){
            this.libros = new ArrayList<>();
        }
        this.libros.add(libro);
        libro.setAutor(this);

    }

    public void agregarListadeLibros(List<Libro> libros){
        libros.forEach(l-> l.setAutor(this));
        this.libros = libros;
    }


}
