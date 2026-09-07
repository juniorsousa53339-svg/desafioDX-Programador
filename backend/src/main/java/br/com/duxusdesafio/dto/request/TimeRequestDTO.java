package br.com.duxusdesafio.dto.request;



import java.time.LocalDate;
import java.util.List;


public record TimeRequestDTO(
        String nomeDoClube,
        LocalDate data,
        List<Long> integrantesIds
) {
}