package com.example.challengeLiteratura.principal;

import com.example.challengeLiteratura.dto.LibroDTO;
import com.example.challengeLiteratura.dto.ListasLibrosDTO;
import com.example.challengeLiteratura.dto.PersonaDTO;
import com.example.challengeLiteratura.model.Libro;
import com.example.challengeLiteratura.service.AutorService;
import com.example.challengeLiteratura.service.ConsumidorAPI_And_Mapper;
import com.example.challengeLiteratura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Main {
@Autowired
    ConsumidorAPI_And_Mapper consumidor;

@Autowired
    AutorService autorService;

@Autowired
    LibroService libroService;
    public void ConsultarLibroApi(){
      var librosPrueba =  consumidor.optenerDatosApi("https://gutendex.com/books/?search=Romeo+and+Juliet", ListasLibrosDTO.class);

        System.out.println(librosPrueba.libros().getFirst());


        LibroDTO libroDTO = librosPrueba.libros().getFirst();
        PersonaDTO autor = libroDTO.autores().getFirst();


      autorService.guardarAutor(autor);
        autorService.guardarAutor(autor);
       libroService.registrarLibro(libroDTO);


    }


}
