package com.example.challengeLiteratura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PersonaDTO(
      @JsonAlias("birth_year") int anio_Nacimiento,
      @JsonAlias("death_year")  int anio_Muerte,
      @JsonAlias("name")  String nombre
) {
}
