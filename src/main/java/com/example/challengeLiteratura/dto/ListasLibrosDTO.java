package com.example.challengeLiteratura.dto;

import com.example.challengeLiteratura.model.Libro;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ListasLibrosDTO (
        @JsonAlias("count") int id,
        @JsonAlias("results") List<LibroDTO> libros
) {

}
