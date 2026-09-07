package br.com.duxusdesafio.dto.response;


import br.com.duxusdesafio.model.ComposicaoTime;


import java.time.LocalDate;
import java.util.List;


public record TimeResponseDTO(

        String nomeDoClube,
        LocalDate data,
        List<ComposicaoTime> composicaoTime
) {


}
