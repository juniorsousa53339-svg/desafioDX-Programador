package br.com.duxusdesafio.mapper;

import br.com.duxusdesafio.dto.request.IntegranteRequestDTO;
import br.com.duxusdesafio.dto.response.IntegranteResponseDTO;
import br.com.duxusdesafio.model.Integrante;
import org.springframework.stereotype.Component;

@Component
public class IntegranteMapper {

    public Integrante toEntity(IntegranteRequestDTO request) {

        Integrante integrante = new Integrante();

        integrante.setNome(request.nome());
        integrante.setFuncao(request.funcao());

        return integrante;
    }

    public IntegranteResponseDTO toResponse(Integrante integrante) {

        return new IntegranteResponseDTO
                (integrante.getNome(), integrante.getFuncao());
    }
}
