package com.example.challengeLiteratura.model;

import com.example.challengeLiteratura.dto.LibroDTO;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
//@ToString(exclude = "autor")
@Setter

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
     private String titulo;


     private String lenguajes;
     private int numero_Descargas;

     @ManyToOne( fetch = FetchType.EAGER)
     private Autor autor;

     public Libro (LibroDTO lDTO){
         this.titulo = lDTO.titulo();
         this.lenguajes = lDTO.lenguajes().stream().findFirst().toString();
         this.numero_Descargas = lDTO.numero_Descargas();
     }
}
