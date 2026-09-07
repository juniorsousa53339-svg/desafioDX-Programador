package br.com.duxusdesafio.dto;

import java.time.LocalDate;
import java.util.List;

public record TimeDaDataResponseDTO(

        LocalDate data,
        String clube,
        List<String> integrantes
) {
}
