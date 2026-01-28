package com.example.challengeLiteratura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record LibroDTO (
       @JsonAlias("id") int id,
       @JsonAlias("title") String titulo,
       @JsonAlias("authors") List<PersonaDTO> autores,
       @JsonAlias("languages") List<String > lenguajes,
       @JsonAlias("download_count") int numero_Descargas,
       @JsonAlias("translators") List<PersonaDTO> traductores
) {
}
