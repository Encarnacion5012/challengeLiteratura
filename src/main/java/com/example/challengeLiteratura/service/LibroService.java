package com.example.challengeLiteratura.service;

import com.example.challengeLiteratura.dto.LibroDTO;
import com.example.challengeLiteratura.dto.ListasLibrosDTO;
import com.example.challengeLiteratura.model.Autor;
import com.example.challengeLiteratura.model.Libro;
import com.example.challengeLiteratura.repository.AutorRepository;
import com.example.challengeLiteratura.repository.LibroRepository;
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
                .ifPresentOrElse(l-> System.out.println("El libro: " + libroDTO.titulo() + " ya existe en la base de datos local"),
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


    public void consultarLibrosPorNombreAPI(){
        System.out.println("Estas en la opcion de Bucar libros por TITULO en el Api");
        System.out.println(" ");
        System.out.println("Digite el titulo del libro que desea buscar");

        String nombreLibro = teclado.nextLine();

        var librosPrueba =  consumidor.optenerDatosApi(URLBASE+URLBUSCARNOMBRES+nombreLibro.replace(" ", "+").trim(), ListasLibrosDTO.class);

        System.out.println(librosPrueba);

        LibroDTO libroDTO = librosPrueba.libros().getFirst();
        System.out.println("------------------------");
        System.out.println("Titulo: " + libroDTO.titulo());
        System.out.println("Autor: " +  libroDTO.autores().getFirst().nombre());
        System.out.println("Idioma: " + libroDTO.lenguajes().getFirst());
        System.out.println("Numero descargas: " + libroDTO.numero_Descargas());
        System.out.println("------------------------");


    }

    public void listarLibrosGuardados(){

        System.out.println("Libros Buscados: ");
        System.out.println(" ");
        libroRepository.findAll().forEach(
                l-> System.out.println(
                        """
                            -------------------------------
                            Titulo: %s
                            Lenguaje: %s
                            Numero_Descargas: %d
                            Autor: %s    
                            -------------------------------
                                """.formatted(l.getTitulo(), l.getLenguajes(), l.getNumero_Descargas(), l.getAutor().getNombre())
                )

        );
    }

    public void listarLibrosEnLaApi(){
        ListasLibrosDTO listasLibrosApi = consumidor.optenerDatosApi(URLBASE, ListasLibrosDTO.class);
        System.out.println(listasLibrosApi);
    }

    public void lisarLibrosPorIdiomas(){
        System.out.println("Ingrese el Idiomas que desea encontrar, ejem: en");
        String idioma = teclado.nextLine();
        libroRepository.findBylenguajes(idioma).forEach(l->{
            System.out.println("""
                     -------------------------------
                            Titulo: %s
                            Lenguaje: %s
                            Numero_Descargas: %d
                            Autor: %s    
                            -------------------------------
                    """.formatted(l.getTitulo(), l.getLenguajes(), l.getNumero_Descargas(), l.getAutor().getNombre()));
                }
        );
    }







}
