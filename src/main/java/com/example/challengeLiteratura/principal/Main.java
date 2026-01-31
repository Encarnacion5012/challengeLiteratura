package com.example.challengeLiteratura.principal;


import com.example.challengeLiteratura.service.AutorService;
import com.example.challengeLiteratura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;


@Component
public class Main {

@Autowired
    LibroService libroService;

@Autowired
    AutorService autorService;
    int opcion;
    Scanner teclado = new Scanner(System.in);
    public void Menu_principal(){



            do {

              try  {
                    System.out.println("""
                            ----------------------------
                            Seleccione una opcion: 
                            1-Consultar Libros por nombre (api)
                            2-Listar Libros en API
                            3-Listar autores en Api
                            4-Listar autores vivos en un periodo de tiempo en API
                            5-Listar libros buscados (guardados en la db)
                            6-Listar autores guadados de los libros busados
                            7-Listar autores vivos en un periodo de tiempo (DB Local)
                            8-Listar Libros por idioma DB
                            0-Salir
                            ----------------------------
                            """);

                    opcion = teclado.nextInt();
                    teclado.nextLine();

                    System.out.println();
                    if (opcion == 1) {
                        libroService.consultarLibrosPorNombreAPI();
                    } else if (opcion ==2) {
                        libroService.listarLibrosEnLaApi();
                    } else if (opcion == 3) {
                        autorService.listarAutoresEnAPI();
                    } else if (opcion == 4) {
                        autorService.listarAutoresvivosEnUnPeriodoDeTiempoAPI();
                    } else if (opcion == 5) {
                        libroService.listarLibrosGuardados();
                    } else if (opcion == 6) {
                        autorService.listarAutoresGuardados();
                    }  else if (opcion == 7) {
                        autorService.listarAutoresvivosEnUnPeriodoDeTiempoDB();
                    } else if (opcion == 8) {
                        libroService.lisarLibrosPorIdiomas();
                    } else if (opcion == 0) {
                        System.out.println("La aplicacion finalizo");
                        break;
                    } else {
                        System.out.println("Introdusca una opcion valida");
                    }
                }catch (InputMismatchException e) {
                  System.out.println("Solo se pueden ingresar numeros: " + e.getMessage());
                  teclado.nextLine();
                  opcion =-1;
              }
            } while (opcion != 0);

        }

    }



