package com.example.challengeLiteratura.service;

import com.example.challengeLiteratura.dto.LibroDTO;
import com.example.challengeLiteratura.dto.ListasLibrosDTO;
import com.example.challengeLiteratura.dto.PersonaDTO;
import com.example.challengeLiteratura.model.Autor;
import com.example.challengeLiteratura.model.Libro;
import com.example.challengeLiteratura.repository.AutorRepository;
import com.example.challengeLiteratura.repository.LibroRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class LibroService {
    @Autowired
    LibroRepository libroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    ConsumidorAPI_And_Mapper consumidor;

    Scanner teclado = new Scanner(System.in);

    private static final String URLBASE ="https://gutendex.com/books/";
    private static final String URLBUSCARNOMBRES = "?search=";

    public void verificarLibroExistente( LibroDTO libroDTO, Autor autor){
         libroRepository.findBytitulo(libroDTO.titulo())
                 .stream().findFirst()
                .ifPresentOrElse(l-> System.out.println("El pibro: " + libroDTO.titulo() + " ya existe"),
                        ()->{

                            Libro libro = new Libro(libroDTO);
                            libro.setAutor(autor);
                            libroRepository.save(libro);

                        }
                        );
    }

    public void registrarLibro(LibroDTO libroDTO){
        String nombreAutor = libroDTO.autores().getFirst().nombre();
        Autor autor = autorRepository.findBynombre(nombreAutor)
                .orElseGet(() -> autorRepository.save(new Autor(libroDTO.autores().getFirst())));

        verificarLibroExistente(libroDTO, autor);

        System.out.println("Libro verficado/registrado correctamentre");
    }

    public void consultarLibrosPorNombre(){
        System.out.println("Estas en la opcion de Bucar libros por nombre");
        System.out.println("Digite el nombredel libro que desea buscar");

        String nombreLibro = teclado.nextLine();

        var librosPrueba =  consumidor.optenerDatosApi(URLBASE+URLBUSCARNOMBRES+nombreLibro.replace(" ", "+").trim(), ListasLibrosDTO.class);

        System.out.println(librosPrueba);

        Libro libro = new Libro (librosPrueba.libros().getFirst());
        System.out.println("------------------------");
        System.out.println("Titulo: " + libro.getTitulo());
        System.out.println("Autor: " +  libro.getAutor().getNombre());
        System.out.println("Idioma: " + libro.getLenguajes());
        System.out.println("Numero descargas: " + libro.getNumero_Descargas());
        System.out.println("------------------------");
    }



}
