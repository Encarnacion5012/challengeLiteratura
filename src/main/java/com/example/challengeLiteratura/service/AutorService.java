package com.example.challengeLiteratura.service;

import com.example.challengeLiteratura.dto.LibroDTO;
import com.example.challengeLiteratura.dto.ListasLibrosDTO;
import com.example.challengeLiteratura.dto.PersonaDTO;
import com.example.challengeLiteratura.model.Autor;
import com.example.challengeLiteratura.model.Libro;
import com.example.challengeLiteratura.repository.AutorRepository;
import com.example.challengeLiteratura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
     private AutorRepository autorRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    ConsumidorAPI_And_Mapper consumidor;

    private Scanner teclado = new Scanner(System.in);
    private static final String URLBASE ="https://gutendex.com/books/";
    public static final String URLINICIOPERIODO = "?author_year_start=";//NECESITA PONER EL ANIO MEDIANTE UNA VARIABLE DESPES DEL IGUAL
    public static final String URLFINALOPERIODO ="&author_year_end=" ; //IGUAL AQUI



    public void guardarAutor(PersonaDTO autor){
        Autor autorBuscado = autorRepository.findBynombre(autor.nombre().toString())
                        .orElseGet(()-> {
                            return autorRepository.save(new Autor(autor));
                        });



        System.out.println("Autor verificaco/registrado correctamente");
    }

    private void imprimirAutores(List<Autor> autorres, String mensaje){
        if (autorres.isEmpty()){
            System.out.println(mensaje);
        }else {
            autorres.forEach(a -> {
                String titulosLibros = a.getLibros().stream().map(Libro::getTitulo)
                        .collect(Collectors.joining(", "));

                System.out.println(String.format("""
                      -----------------------------
                      Nombre: %s
                      Anio_Nacimiento: %d
                      Anio_Muerte: %d
                      Libros: [%s]
                      -----------------------------
                      """, a.getNombre(), a.getAnio_Nacimiento(), a.getAnio_Muerte(), titulosLibros));
            });
        }

    }


    public void listarAutoresGuardados(){

      List<Autor> autoresListados =  autorRepository.findAll();
      imprimirAutores(autoresListados, "No hay auores guardados");


    }


    public void listarAutoresvivosEnUnPeriodoDeTiempoDB() {
        int inicioPeriodo;
        int finalPeriodo;
        try{
            System.out.println("--------------------------");
            System.out.println("Introdusca el anio donde inicia el periodo de tiempo");
             inicioPeriodo = teclado.nextInt();
            teclado.nextLine();
            System.out.println("Introdusca el anio de finalizacion del periodo");
             finalPeriodo = teclado.nextInt();
            teclado.nextLine();

            List<Autor> autoreVivosEnUnPeriodo = autorRepository.listarAutoresVivosEnUnPeriodoDeTiempo(inicioPeriodo, finalPeriodo);

            imprimirAutores(autoreVivosEnUnPeriodo, "No tenemos autores vivmos en ese periodo de tiempo");
        } catch (InputMismatchException e) {
            System.out.println("Solo se perimite ingresar numeros, intenete nuevol: " + e.getMessage());
            teclado.nextLine();
        }
    }

    public void listarAutoresvivosEnUnPeriodoDeTiempoAPI(){

        int inicioPeriodo;
        int finalPeriodo;
        try{
            System.out.println("--------------------------");
            System.out.println("Introdusca el anio donde inicia el periodo de tiempo");
            inicioPeriodo = teclado.nextInt();
            teclado.nextLine();
            System.out.println("Introdusca el anio de finalizacion del periodo");
            finalPeriodo = teclado.nextInt();
            teclado.nextLine();

            ListasLibrosDTO  listaLibrosConUtores = consumidor.optenerDatosApi(URLBASE+URLINICIOPERIODO+inicioPeriodo+URLFINALOPERIODO+finalPeriodo, ListasLibrosDTO.class);
             listaLibrosConUtores.libros()
            .forEach( l-> {
                l.autores().forEach(a-> {
                    System.out.println("""
                            ----------------------------
                            nombre: %s
                            anio_Nacimiento: %d
                            anio_Muerte: %d
                            """.formatted(a.nombre(), a.anio_Nacimiento(), a.anio_Muerte()));
                        }
                );
            }

          );
        }catch (InputMismatchException e) {
            System.out.println("Solo se perimite ingresar numeros, intenete nuevol: " + e.getMessage());
            teclado.nextLine();
        }


    }

    public void listarAutoresEnAPI(){

    }
}
